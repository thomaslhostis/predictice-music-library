package com.predictice.musiclibrary.infrastructure.albums;

import com.predictice.musiclibrary.domain.albums.AlbumsPort;
import com.predictice.musiclibrary.domain.albums.model.Album;
import com.predictice.musiclibrary.domain.albums.model.AlbumSearchResult;
import com.predictice.musiclibrary.domain.albums.model.ReleaseYear;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.client.elc.NativeQueryBuilder;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

@Component
public class AlbumsAdapter implements AlbumsPort {
    private final ElasticsearchAlbumsRepository elasticsearchAlbumsRepository;
    private final ElasticsearchOperations elasticsearchOperations;

    public AlbumsAdapter(
            ElasticsearchAlbumsRepository elasticsearchAlbumsRepository,
            ElasticsearchOperations elasticsearchOperations
    ) {
        this.elasticsearchAlbumsRepository = elasticsearchAlbumsRepository;
        this.elasticsearchOperations = elasticsearchOperations;
    }

    @Override
    public void saveAlbums(List<Album> albums) {
        List<AlbumDocument> albumDocuments = albums.stream().map(AlbumDocument::new).toList();
        elasticsearchAlbumsRepository.deleteAll();
        elasticsearchAlbumsRepository.saveAll(albumDocuments);
    }

    @Override
    public AlbumSearchResult findAlbums(
            String keyword,
            String releaseYear,
            int pageNumber
    ) {
        Query query = buildCriteriaQuery(keyword, releaseYear);

        SearchHits<AlbumDocument> searchHits = findPageAlbumHits(pageNumber, query);
        List<Album> paginatedAlbums = searchHits.get()
                .map(SearchHit::getContent)
                .map(AlbumDocument::toAlbum)
                .toList();

        List<ReleaseYear> releaseYears = findReleaseYears(query);

        return new AlbumSearchResult(
                (int) searchHits.getTotalHits(),
                paginatedAlbums,
                releaseYears
        );
    }

    /**
     * En temps normal, j'aurais utilisé une requête Elasticsearch pour récupérer directement le nombre
     * d'albums pour chaque année de sortie, pour la recherche en cours. Par manque de temps, je propose
     * cette implémentation en Java.
     */
    private List<ReleaseYear> findReleaseYears(Query query) {
        NativeQuery nativeQuery = new NativeQueryBuilder()
                .withQuery(query)
                .build();

        return elasticsearchOperations.search(nativeQuery, AlbumDocument.class)
                .get()
                .map(SearchHit::getContent)
                .collect(groupingBy(AlbumDocument::getReleaseYear, counting()))
                .entrySet()
                .stream()
                .map(entry -> new ReleaseYear(entry.getKey(), entry.getValue().intValue()))
                .toList();
    }

    private SearchHits<AlbumDocument> findPageAlbumHits(int pageNumber, Query query) {
        Pageable twelveResultsPage = PageRequest.of(pageNumber - 1, 12);
        NativeQuery nativeQuery = new NativeQueryBuilder()
                .withQuery(query)
                .withPageable(twelveResultsPage)
                .build();

        return elasticsearchOperations.search(nativeQuery, AlbumDocument.class);
    }

    private static Query buildCriteriaQuery(String keyword, String releaseYear) {
        Criteria criteria = new Criteria("title").expression(keyword).or(
                new Criteria("artist").expression(keyword)
        );

        if (releaseYear != null) {
            criteria.and(new Criteria("releaseYear").expression(releaseYear));
        }

        return new CriteriaQuery(criteria);
    }
}

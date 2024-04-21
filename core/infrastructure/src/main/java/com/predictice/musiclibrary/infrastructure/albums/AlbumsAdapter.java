package com.predictice.musiclibrary.infrastructure.albums;

import com.predictice.musiclibrary.domain.albums.AlbumsPort;
import com.predictice.musiclibrary.domain.albums.model.Album;
import com.predictice.musiclibrary.domain.albums.model.AlbumSearchResult;
import com.predictice.musiclibrary.domain.albums.model.ReleaseYear;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.StreamSupport.stream;

@Component
public class AlbumsAdapter implements AlbumsPort {
    private final ElasticsearchAlbumsRepository elasticsearchAlbumsRepository;

    public AlbumsAdapter(ElasticsearchAlbumsRepository elasticsearchAlbumsRepository) {
        this.elasticsearchAlbumsRepository = elasticsearchAlbumsRepository;
    }

    @Override
    public void saveAlbums(List<Album> albums) {
        List<AlbumDocument> albumDocuments = albums.stream().map(AlbumDocument::new).toList();
        elasticsearchAlbumsRepository.deleteAll();
        elasticsearchAlbumsRepository.saveAll(albumDocuments);
    }

    @Override
    public AlbumSearchResult searchAlbums(String query, String year, String page) {
        List<Album> albums = stream(elasticsearchAlbumsRepository.findAll().spliterator(), false)
                .map(AlbumDocument::toAlbum)
                .toList();

        List<ReleaseYear> releaseYears = albums.stream()
                .collect(groupingBy(Album::releaseYear, counting()))
                .entrySet()
                .stream()
                .map(entry -> new ReleaseYear(entry.getKey(), entry.getValue().intValue()))
                .toList();

        return new AlbumSearchResult(
                albums.size(),
                albums,
                releaseYears
        );
    }
}

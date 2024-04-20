package com.predictice.musiclibrary.presentation;

import com.predictice.musiclibrary.application.albums.AlbumsUseCases;
import com.predictice.musiclibrary.domain.albums.Album;
import com.predictice.musiclibrary.presentation.model.AlbumResource;
import com.predictice.musiclibrary.presentation.model.AlbumsSearchResult;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AlbumsController implements AlbumsApi {
    private final AlbumsUseCases albumsUseCases;

    public AlbumsController(AlbumsUseCases albumsUseCases) {
        this.albumsUseCases = albumsUseCases;
    }

    @Override
    public AlbumsSearchResult searchAlbums(
            String query,
            String year,
            String page
    ) {
        List<Album> albums = albumsUseCases.searchAlbums(query, year, page);

        List<AlbumResource> albumResources = albums
                .stream()
                .map(AlbumsMapper::toPresentationAlbum)
                .toList();

        return new AlbumsSearchResult()
                .resultCount(12)
                .albums(albumResources);
    }
}

package com.predictice.musiclibrary.presentation;

import com.predictice.musiclibrary.application.albums.AlbumsUseCases;
import com.predictice.musiclibrary.domain.albums.model.Album;
import com.predictice.musiclibrary.domain.albums.model.AlbumSearchResult;
import com.predictice.musiclibrary.presentation.model.AlbumResource;
import com.predictice.musiclibrary.presentation.model.AlbumsSearchResultResource;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AlbumsController implements AlbumsApi {
    private final AlbumsUseCases albumsUseCases;

    public AlbumsController(AlbumsUseCases albumsUseCases) {
        this.albumsUseCases = albumsUseCases;
    }

    @Override
    public void saveAlbums(List<AlbumResource> albumResources) {
        List<Album> albums = albumResources
                .stream()
                .map(AlbumResourceMapper::toAlbum)
                .toList();

        albumsUseCases.saveAlbums(albums);
    }

    @Override
    public AlbumsSearchResultResource searchAlbums(
            String query,
            String year,
            String page
    ) {
        AlbumSearchResult albumSearchResult = albumsUseCases.searchAlbums(query, year, page);
        return AlbumsSearchResultResourceMapper.toAlbumsSearchResultResource(albumSearchResult);
    }
}

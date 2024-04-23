package com.predictice.musiclibrary.application.albums;

import com.predictice.musiclibrary.domain.albums.AlbumsPort;
import com.predictice.musiclibrary.domain.albums.model.Album;
import com.predictice.musiclibrary.domain.albums.model.AlbumSearchResult;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AlbumsUseCases {
    private final AlbumsPort albumsPort;

    public AlbumsUseCases(AlbumsPort albumsPort) {
        this.albumsPort = albumsPort;
    }

    public void saveAlbums(List<Album> albums) {
        albumsPort.saveAlbums(albums);
    }

    public AlbumSearchResult searchAlbums(
            String query,
            String releaseYear,
            Integer pageNumber
    ) {
        return albumsPort.findAlbums(query, releaseYear, pageNumber);
    }
}

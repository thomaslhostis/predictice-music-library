package com.predictice.musiclibrary.application.albums;

import com.predictice.musiclibrary.domain.albums.Album;
import com.predictice.musiclibrary.domain.albums.AlbumsPort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AlbumsUseCases {
    private final AlbumsPort albumsPort;

    public AlbumsUseCases(AlbumsPort albumsPort) {
        this.albumsPort = albumsPort;
    }

    public List<Album> searchAlbums(
            String query,
            String year,
            String page
    ) {
        return albumsPort.searchAlbums(query, year, page);
    }

    public void saveAlbums(List<Album> albums) {
        albumsPort.saveAlbums(albums);
    }
}

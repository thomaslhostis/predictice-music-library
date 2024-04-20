package com.predictice.musiclibrary.infrastructure.albums;

import com.predictice.musiclibrary.domain.albums.Album;
import com.predictice.musiclibrary.domain.albums.AlbumsPort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AlbumsAdapter implements AlbumsPort {
    @Override
    public List<Album> searchAlbums(String query, String year, String page) {
        return List.of(
                new Album("id", "title", "artist", "releaseYear", "coverUrl")
        );
    }

    @Override
    public void saveAlbums(List<Album> albums) {

    }
}

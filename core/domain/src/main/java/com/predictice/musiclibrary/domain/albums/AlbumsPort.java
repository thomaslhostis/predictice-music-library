package com.predictice.musiclibrary.domain.albums;

import java.util.List;

public interface AlbumsPort {

    List<Album> searchAlbums(
            String query,
            String year,
            String page
    );

    void saveAlbums(List<Album> albums);
}

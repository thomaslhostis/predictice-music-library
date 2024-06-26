package com.predictice.musiclibrary.domain.albums;

import com.predictice.musiclibrary.domain.albums.model.Album;
import com.predictice.musiclibrary.domain.albums.model.AlbumSearchResult;

import java.util.List;

public interface AlbumsPort {

    void saveAlbums(List<Album> albums);

    AlbumSearchResult findAlbums(
            String keyword,
            String releaseYear,
            int pageNumber
    );
}

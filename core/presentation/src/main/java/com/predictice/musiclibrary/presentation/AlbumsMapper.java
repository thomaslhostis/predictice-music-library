package com.predictice.musiclibrary.presentation;

import com.predictice.musiclibrary.domain.albums.Album;
import com.predictice.musiclibrary.presentation.model.AlbumResource;

public class AlbumsMapper {
    public static AlbumResource toPresentationAlbum(Album album) {
        return new AlbumResource()
                .id(album.id())
                .title(album.title())
                .artist(album.artist())
                .releaseYear(album.releaseYear())
                .coverUrl(album.coverUrl());
    }
}

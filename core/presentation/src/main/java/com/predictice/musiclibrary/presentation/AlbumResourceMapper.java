package com.predictice.musiclibrary.presentation;

import com.predictice.musiclibrary.domain.albums.model.Album;
import com.predictice.musiclibrary.presentation.model.AlbumResource;

public class AlbumResourceMapper {
    public static AlbumResource toAlbumResource(Album album) {
        return new AlbumResource()
                .id(album.id())
                .title(album.title())
                .artist(album.artist())
                .releaseYear(album.releaseYear())
                .coverUrl(album.coverUrl());
    }

    public static Album toAlbum(AlbumResource albumResource) {
        return new Album(
                albumResource.getId(),
                albumResource.getTitle(),
                albumResource.getArtist(),
                albumResource.getReleaseYear(),
                albumResource.getCoverUrl()
        );
    }
}

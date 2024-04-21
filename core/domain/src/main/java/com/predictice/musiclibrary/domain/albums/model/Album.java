package com.predictice.musiclibrary.domain.albums.model;

public record Album(
        String id,
        String title,
        String artist,
        String releaseYear,
        String coverUrl
) {
}

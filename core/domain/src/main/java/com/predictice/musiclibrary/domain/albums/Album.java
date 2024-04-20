package com.predictice.musiclibrary.domain.albums;

public record Album(
        String id,
        String title,
        String artist,
        String releaseYear,
        String coverUrl
) {

}

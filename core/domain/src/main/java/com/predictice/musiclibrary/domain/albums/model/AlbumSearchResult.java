package com.predictice.musiclibrary.domain.albums.model;

import java.util.List;

public record AlbumSearchResult(
        Integer resultCount,
        List<Album> albums,
        List<ReleaseYear> releaseYears
) {
}

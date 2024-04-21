package com.predictice.musiclibrary.presentation;

import com.predictice.musiclibrary.domain.albums.model.AlbumSearchResult;
import com.predictice.musiclibrary.presentation.model.AlbumResource;
import com.predictice.musiclibrary.presentation.model.AlbumsSearchResultResource;
import com.predictice.musiclibrary.presentation.model.ReleaseYearResource;

import java.util.List;

public class AlbumsSearchResultResourceMapper {
    public static AlbumsSearchResultResource toAlbumsSearchResultResource(
            AlbumSearchResult albumSearchResult
    ) {
        List<AlbumResource> albumResources = albumSearchResult
                .albums()
                .stream()
                .map(AlbumResourceMapper::toAlbumResource)
                .toList();

        List<ReleaseYearResource> releaseYearResources = albumSearchResult
                .releaseYears()
                .stream()
                .map(releaseYear -> new ReleaseYearResource()
                        .year(releaseYear.year())
                        .albumCount(releaseYear.albumCount())
                ).toList();

        return new AlbumsSearchResultResource()
                .resultCount(albumSearchResult.resultCount())
                .albums(albumResources)
                .releaseYears(releaseYearResources);
    }
}

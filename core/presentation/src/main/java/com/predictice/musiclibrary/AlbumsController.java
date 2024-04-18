package com.predictice.musiclibrary;

import com.predictice.musiclibrary.presentation.AlbumsApi;
import com.predictice.musiclibrary.presentation.model.AlbumsSearchResult;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AlbumsController implements AlbumsApi {
    @Override
    public AlbumsSearchResult searchAlbums(
            String query,
            String year,
            String page
    ) {
        return new AlbumsSearchResult().resultCount(12);
    }
}

package com.predictice.musiclibrary.infrastructure.albums;

import com.predictice.musiclibrary.domain.albums.model.Album;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "album")
public class AlbumDocument {
    @Id
    private String id;
    private String title;
    private String artist;
    private String releaseYear;
    private String coverUrl;

    public AlbumDocument() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(String releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public AlbumDocument(Album album) {
        id = album.id();
        title = album.title();
        artist = album.artist();
        releaseYear = album.releaseYear();
        coverUrl = album.coverUrl();
    }

    public Album toAlbum() {
        return new Album(
                id,
                title,
                artist,
                releaseYear,
                coverUrl
        );
    }
}

package com.predictice.musiclibrary.infrastructure.albums;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ElasticsearchAlbumsRepository extends ElasticsearchRepository<AlbumDocument, String> {
}

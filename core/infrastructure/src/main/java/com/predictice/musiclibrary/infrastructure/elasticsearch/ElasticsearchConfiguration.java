package com.predictice.musiclibrary.infrastructure.elasticsearch;

import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.TrustAllStrategy;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;

import javax.net.ssl.SSLContext;

@Configuration
public class ElasticsearchConfiguration extends org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration {
    @Value("${spring.data.elasticsearch.host}")
    private String host;

    @Override
    public ClientConfiguration clientConfiguration() {
        return ClientConfiguration.builder()
                .connectedTo(host + ":9200")
                .usingSsl(buildSSLContext(), NoopHostnameVerifier.INSTANCE)
                .withBasicAuth("elastic", "changeme")
                .build();
    }

    private static SSLContext buildSSLContext() {
        try {
            return new SSLContextBuilder()
                    .loadTrustMaterial(null, TrustAllStrategy.INSTANCE)
                    .build();

        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }
}

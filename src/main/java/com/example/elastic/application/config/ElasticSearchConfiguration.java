package com.example.elastic.application.config;

import org.elasticsearch.client.node.NodeClient;
import org.elasticsearch.common.settings.Settings;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import java.util.UUID;

import static org.elasticsearch.node.NodeBuilder.nodeBuilder;

@Configuration
@EnableElasticsearchRepositories
public class ElasticSearchConfiguration {

	@Bean
	public ElasticsearchTemplate elasticsearchTemplate() {
		return new ElasticsearchTemplate(getNodeClient());
	}

	private static NodeClient getNodeClient() {
		return (NodeClient) nodeBuilder()
				.settings(Settings.builder().put("path.home", "localhost:9300"))
				.clusterName(UUID.randomUUID().toString()).local(true).node()
				.client();
	}
}

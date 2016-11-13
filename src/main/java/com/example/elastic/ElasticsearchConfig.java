package com.example.elastic;

import static org.elasticsearch.node.NodeBuilder.nodeBuilder;

import org.elasticsearch.client.node.NodeClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import java.util.UUID;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.example.elastic.application")
public class ElasticsearchConfig {

	@Autowired
	private ElasticsearchTemplate elasticsearchTemplate;

	@Bean
	public ElasticsearchTemplate elasticsearchTemplate() {
		return new ElasticsearchTemplate(getNodeClient());
	}

	private static NodeClient getNodeClient() {
		return (NodeClient) nodeBuilder().clusterName(UUID.randomUUID().toString()).local(true).node()
				.client();
	}

}

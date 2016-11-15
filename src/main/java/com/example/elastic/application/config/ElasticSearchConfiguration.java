package com.example.elastic.application.config;

import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import static org.elasticsearch.node.NodeBuilder.nodeBuilder;

@Configuration
@EnableElasticsearchRepositories
public class ElasticSearchConfiguration {

	@Bean
	public Client client() {
		final String pathHome = "locahost:9300";

		return  nodeBuilder().settings(Settings.builder()
				.put("path.home", pathHome))
				.client(true)
				.data(false)
				.node().client();
	}

	@Bean
	public ElasticsearchTemplate elasticsearchTemplate() {
		return new ElasticsearchTemplate(client());
	}
}

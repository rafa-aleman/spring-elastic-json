package com.example.elastic.application.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.EntityMapper;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import java.io.IOException;

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
		return new ElasticsearchTemplate(client(), new EntityMapperImpl());
	}

	@Bean
	public ObjectMapper objectMapper() {
		final ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.LOWER_CASE);
		return objectMapper;
	}

	public class EntityMapperImpl implements EntityMapper {

		private final ObjectMapper objectMapper = objectMapper();

		public String mapToString(Object object) throws IOException {
			return objectMapper.writeValueAsString(object);
		}

		public <T> T mapToObject(String source, Class<T> clazz) throws IOException {
			return objectMapper.readValue(source, clazz);
		}
	}
}

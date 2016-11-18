package com.example.elastic.application.domain;

import com.example.elastic.application.config.MyPropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "athlete", type = "athlete", shards = 1, replicas = 0, refreshInterval = "-1")
@JsonNaming(MyPropertyNamingStrategy.class)
@Data
public class Athlete {

	@Id
	private String id;

	private String firstName;

	private String lastName;

	public Athlete() {}

	public Athlete(final String firstName, final String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

}

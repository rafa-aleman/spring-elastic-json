package com.example.elastic.application.domain;

import com.example.elastic.application.config.MyPropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "person", type = "person", shards = 1, replicas = 0, refreshInterval = "-1")
@JsonNaming(MyPropertyNamingStrategy.class)
@Data
public class Person {

	@Id
	private String id;

	private String firstName;

	private String lastName;

	public Person() {}

	public Person(final String firstName, final String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

}

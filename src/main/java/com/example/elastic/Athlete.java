package com.example.elastic;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "athlete", type = "athlete", shards = 1, replicas = 0, refreshInterval = "-1")
public class Athlete {

	@Id
	private String id;

	@JsonProperty("nombre")
	private String firstName;

	@JsonProperty("apellido")
	private String lastName;

	public Athlete() {}

	public Athlete(final String firstName, final String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return String.format("Athlete[id=%s, firstName='%s', lastName='%s']", this.id,
				this.firstName, this.lastName);
	}
}

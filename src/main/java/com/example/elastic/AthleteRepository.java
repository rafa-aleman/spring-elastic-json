package com.example.elastic;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface AthleteRepository extends ElasticsearchRepository<Athlete, String> {

	Athlete findByFirstName(String firstName);

	List<Athlete> findByLastName(String lastName);

}

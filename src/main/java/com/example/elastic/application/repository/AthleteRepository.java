package com.example.elastic.application.repository;

import com.example.elastic.application.domain.Athlete;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface AthleteRepository extends ElasticsearchRepository<Athlete, String> {

	Athlete findByFirstName(String firstName);

	List<Athlete> findByLastName(String lastName);

}

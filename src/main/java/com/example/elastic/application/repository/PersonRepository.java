package com.example.elastic.application.repository;

import com.example.elastic.application.domain.Person;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface PersonRepository extends ElasticsearchRepository<Person, String> {

	Person findByFirstName(String firstName);

	List<Person> findByLastName(String lastName);

}

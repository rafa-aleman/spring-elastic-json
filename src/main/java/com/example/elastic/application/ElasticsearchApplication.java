package com.example.elastic.application;

import com.example.elastic.application.domain.Person;
import com.example.elastic.application.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
@EnableAutoConfiguration
public class ElasticsearchApplication implements CommandLineRunner {

	@Autowired
	private PersonRepository personRepository;

	public void run(String... args) throws Exception {
		this.personRepository.deleteAll();
		savePersons();
		fetchAllPersons();
		fetchIndividualPersons();
	}

	private void savePersons() {
		this.personRepository.save(new Person("Bar", "Foo"));
		this.personRepository.save(new Person("Par", "Foo"));
	}

	private void fetchAllPersons() {
		System.out.println("Persons found with findAll():");
		System.out.println("-------------------------------");
		for (Person person : this.personRepository.findAll()) {
			System.out.println(person);
		}
		System.out.println();
	}

	private void fetchIndividualPersons() {
		System.out.println("Persons found with findByFirstName('Bar'):");
		System.out.println("--------------------------------");
		System.out.println(this.personRepository.findByFirstName("Bar"));

		System.out.println("Persons found with findByLastName('Foo'):");
		System.out.println("--------------------------------");
		for (Person person : this.personRepository.findByLastName("Foo")) {
			System.out.println(person);
		}
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(ElasticsearchApplication.class, "").close();
	}
}
package com.example.elastic.application;

import com.example.elastic.application.domain.Athlete;
import com.example.elastic.application.repository.AthleteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ElasticsearchApplication implements CommandLineRunner {

	@Autowired
	private AthleteRepository athleteRepository;

	public void run(String... args) throws Exception {
		this.athleteRepository.deleteAll();
		saveAthletes();
		fetchAllAthletes();
		fetchIndividualAthletes();
	}

	private void saveAthletes() {
		this.athleteRepository.save(new Athlete("Bar", "Foo"));
		this.athleteRepository.save(new Athlete("Par", "Foo"));
	}

	private void fetchAllAthletes() {
		System.out.println("Athletes found with findAll():");
		System.out.println("-------------------------------");
		for (Athlete athlete : this.athleteRepository.findAll()) {
			System.out.println(athlete);
		}
		System.out.println();
	}

	private void fetchIndividualAthletes() {
		System.out.println("Athlete found with findByFirstName('Bar'):");
		System.out.println("--------------------------------");
		System.out.println(this.athleteRepository.findByFirstName("Bar"));

		System.out.println("Athletes found with findByLastName('Foo'):");
		System.out.println("--------------------------------");
		for (Athlete athlete : this.athleteRepository.findByLastName("Foo")) {
			System.out.println(athlete);
		}
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(ElasticsearchApplication.class, "").close();
	}
}
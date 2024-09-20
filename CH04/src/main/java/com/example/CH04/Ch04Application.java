package com.example.CH04;

import com.example.CH04.domain.User;
import com.example.CH04.repo.UserRepo;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.Month;

@SpringBootApplication
public class Ch04Application {

	public static void main(String[] args) {
		SpringApplication.run(Ch04Application.class, args);
	}

	@Bean
	public ApplicationRunner init(UserRepo repo) {
		return env -> {
			repo.save(new User("ali", LocalDate.of(2020, Month.JANUARY,18)));
			repo.save(new User("omar", LocalDate.of(2020, Month.AUGUST,3)));
			repo.findAll().forEach(System.out::println);
		};
	}

}

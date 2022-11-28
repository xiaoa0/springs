package com.nighthawk.spring_portfolio.mvc;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import com.nighthawk.spring_portfolio.mvc.jokes.Jokes;
import com.nighthawk.spring_portfolio.mvc.jokes.JokesJpaRepository;

import java.util.List;

@Component // Scans Application for ModelInit Bean, this detects CommandLineRunner
public class ModelInit {  
    @Autowired JokesJpaRepository repository;

    @Bean
    CommandLineRunner run() {  // The run() method will be executed after the application starts
        return args -> {

            // Joke database is populated with starting jokes
            String[] jokesArray = Jokes.init();
            for (String joke : jokesArray) {
                List<Jokes> test = repository.findByJokeIgnoreCase(joke);  // JPA lookup
                if (test.size() == 0)
                    repository.save(new Jokes(null, joke, 0, 0)); //JPA save
            }

        };
    }
}


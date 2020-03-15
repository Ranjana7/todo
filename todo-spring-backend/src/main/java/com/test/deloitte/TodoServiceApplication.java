package com.test.deloitte;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;

import com.test.deloitte.dao.TodoRepository;
import com.test.deloitte.dao.UserRepository;
import com.test.deloitte.enums.Status;
import com.test.deloitte.model.Todo;
import com.test.deloitte.model.User;

@SpringBootApplication
@EntityScan("com.test.deloitte.model")
public class TodoServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(TodoServiceApplication.class, args);
  }

  // Bootstrap some test data into the in-memory database
  @Bean
  CommandLineRunner setUpData(TodoRepository todoRepo, UserRepository userRepo) {

    return args -> {
      String status = Status.CREATED.getStatus();
      LocalDate date =  LocalDate.now();
      User user = userRepo.save(new User("TestUser1"));
      User user1 = userRepo.save(new User("TestUser2"));
      User user3 = userRepo.save(new User("David"));
      User user4 = userRepo.save(new User("Ranjana"));      
      User user5 = userRepo.save(new User("Guest"));
      User user6 = userRepo.save(new User("Amit"));

      // create and save new todos
      todoRepo.save(new Todo("GET GROCERY", status, date, user5));
      todoRepo.save(new Todo("REVIEW JAVA 12 CONTENTS", status, date, user5));
      todoRepo.save(new Todo("DOCUMENT THE TODO SERVICE", status, date, user));
      todoRepo.save(new Todo("REVIEW JAVA 8 CONTENTS", status, date, user));
      todoRepo.save(new Todo("TEST ANOTHER_TITLE", status, date, user1));
      todoRepo.save(new Todo("BUY MILK", status, date, user1));
    };
  }

}

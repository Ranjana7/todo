package com.test.deloitte.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.test.deloitte.model.Todo;
import com.test.deloitte.model.User;

@Repository
@Transactional
public interface TodoRepository extends JpaRepository<Todo, Long> {
  List<Todo> findByUser(User user);
  Optional<Todo> findByTitle(String Title);
}
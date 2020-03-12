package com.test.deloitte.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.deloitte.model.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long> {
}
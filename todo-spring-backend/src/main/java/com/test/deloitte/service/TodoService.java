package com.test.deloitte.service;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.test.deloitte.api.model.TodoRequest;
import com.test.deloitte.model.Todo;

public interface TodoService {
	List<Todo> getAllTodosForUser(String userName);

	Todo retriveTodoByTitle(String title);

	List<Todo> createTodos(@Valid TodoRequest todoRequest);

	List<Todo> updateTodos(@Valid TodoRequest todoRequest);

	List<Todo> deleteTodos(@Valid TodoRequest todoRequest);

	Todo deleteTodo(@NotNull @Valid String userName, @NotNull @Valid Long id);

	Todo getTodoById(@NotNull @Valid Long id);
	
}

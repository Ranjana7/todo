package com.test.deloitte.service;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.deloitte.dao.TodoRepository;
import com.test.deloitte.enums.Status;
import com.test.deloitte.model.Todo;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__({ @Autowired }))
public class TodoService {

	@NonNull
	private final TodoRepository todoRepository;

	public List<Todo> findAll() {
		return todoRepository.findAll();
	}

	public List<Todo> createTodos(List<Todo> todos) {
		return todoRepository.saveAll(todos);
	}

	public Todo createTodo(@Valid Todo todo) {
		todo.setCreatedAt(LocalDate.now());
		return todoRepository.save(todo);
	}

	public Todo findById(Long id) {
		return todoRepository.findById(id)
				.orElseThrow(TodoServiceException::new);
	}
	
	public Todo updateTodoById(Todo todo) {
		Todo todoToUpdate = findById(todo.getId());
		todoToUpdate.setTitle(todo.getTitle());
		todoToUpdate.setStatus(Status.UPDATED.getStatus());
		todoToUpdate.setUpdatedDate(LocalDate.now());
		return createTodo(todoToUpdate);
	}

	public Todo deleteTodoById(Long id) {
		Todo todoToDelete = findById(id);
		todoRepository.deleteById(id);
		return todoToDelete;

	}
}
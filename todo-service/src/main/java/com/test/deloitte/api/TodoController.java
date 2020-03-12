package com.test.deloitte.api;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.deloitte.enums.Status;
import com.test.deloitte.model.Todo;
import com.test.deloitte.service.TodoService;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor(onConstructor = @__({ @Autowired }))
@CrossOrigin("*")
public class TodoController {

	@NonNull
	private final TodoService todoService;

	@GetMapping("/todos")
	public List<Todo> getAllTodos() {
		return todoService.findAll();
	}

	@PostMapping("/todos")
	public Todo createTodo(@Valid @RequestBody Todo todo) {
		todo.setCreatedAt(LocalDate.now());
		todo.setStatus(Status.CREATED.getStatus());
		return todoService.createTodo(todo);
	}

	@GetMapping(value = "/todos/{id}")
	public ResponseEntity<Todo> getTodoById(@PathVariable("id") Long id) {
		return ResponseEntity.ok().body(todoService.findById(id));
	}

	@PutMapping(value = "/todos/{id}")
	public ResponseEntity<Todo> updateTodo(@Valid @RequestBody Todo todo) {
		return ResponseEntity.ok().body(todoService.updateTodoById(todo));
	}

	@DeleteMapping(value = "/todos/{id}")
	public ResponseEntity<?> deleteTodo(@PathVariable("id") Long id) {
		return ResponseEntity.ok().body(todoService.deleteTodoById(id));
	}
}
package com.test.deloitte.api;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

import com.test.deloitte.api.model.TodoRequest;
import com.test.deloitte.api.model.TodoRequest;
import com.test.deloitte.model.Todo;
import com.test.deloitte.model.User;
import com.test.deloitte.service.TodoServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/todos")
@RequiredArgsConstructor(onConstructor = @__({ @Autowired }))
@CrossOrigin("*")
@Api(tags = "TodoService")
public class TodoController {

	
	private final @NonNull TodoServiceImpl todoService;

	@ApiOperation(value = "Get a list of all Todos")
	@GetMapping(path = "/{userName}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Todo>> listAllTodosForUser(@NotNull @Valid @PathVariable String userName) {
		return ResponseEntity.ok().body(todoService.getAllTodosForUser(userName));
	}
	
	@ApiOperation(value = "Get a Todo by id")
	@GetMapping(path = "/todo/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Todo> listAllTodosForUser(@NotNull @Valid @PathVariable Long id) {
		return ResponseEntity.ok().body(todoService.getTodoById(id));
	}


	@ApiOperation(value = "Creates a Todo")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Todo>> createTodosForUser(@Valid @RequestBody TodoRequest todoRequest) {
		return ResponseEntity.status(HttpStatus.CREATED).body(todoService.createTodos(todoRequest));
	}

	@ApiOperation(value = "Update existing Todo")
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Todo>> updateTodosForUser(@Valid @RequestBody TodoRequest todoRequest) {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(todoService.updateTodos(todoRequest));
	}

	@ApiOperation(value = "Deletes a list of Todos")
	@DeleteMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Todo>> deleteTodo(@Valid @RequestBody TodoRequest todoRequest) {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(todoService.deleteTodos(todoRequest));
	}
	@ApiOperation(value = "Delete a Todo")
	@DeleteMapping(path = "/{userName}/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Todo> deleteTodo(@NotNull @Valid @PathVariable String userName,@NotNull @Valid @PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(todoService.deleteTodo(userName, id));
	}
}
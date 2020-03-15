package com.test.deloitte.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.deloitte.api.model.TodoRequest;
import com.test.deloitte.api.model.TodoRequest;
import com.test.deloitte.dao.TodoRepository;
import com.test.deloitte.dao.UserRepository;
import com.test.deloitte.enums.Status;
import com.test.deloitte.handler.exception.CustomResponseEntityExceptionHandler;
import com.test.deloitte.handler.exception.TodoServiceException;
import com.test.deloitte.model.Todo;
import com.test.deloitte.model.User;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;

@CommonsLog
@Service
@RequiredArgsConstructor(onConstructor = @__({ @Autowired }))
public class TodoService {

	@NonNull
	private final TodoRepository todoRepository;
	@NonNull
	private final UserRepository userRepository;

	public List<Todo> getAllTodosForUser(String userName) {
		log.info(String.format("Retrieving all Todos for user : [%s]", userName));
		return todoRepository.findByUser(getPersistedUser(userName));
	}

	public Todo retriveTodoByTitle(String title) {
		return todoRepository.findByTitle(title.toUpperCase()).orElseThrow(
				() -> new TodoServiceException(String.format("Failed to retrieve the todo for title: [%s]", title)));
	}

	public List<Todo> createTodos(@Valid TodoRequest todoRequest) {
		log.info(String.format("Creating new Todos : [%s]", todoRequest));
		User user = getPersistedUser(todoRequest.getUserName());
		for (Todo todo : todoRequest.getTodos()) {
			setTodoProps(todo, user);
		}
		return saveTodos(todoRequest.getTodos());
	}

	public List<Todo> updateTodos(@Valid TodoRequest todoRequest) {
		log.info(String.format("Updating existing Todos : [%s]", todoRequest));
		return updateOrDeleteTodo(todoRequest, true);
	}

	public List<Todo> deleteTodos(@Valid TodoRequest todoRequest) {
		log.info(String.format("Delete Todos if exist for user: [%s]", todoRequest));
		return updateOrDeleteTodo(todoRequest, false);
	}

	private User getPersistedUser(String userName) {
		return userRepository.findUserByName(userName);
	}

	private List<Todo> saveTodos(List<Todo> todos) {
		return todoRepository.saveAll(todos);
	}

	private Todo setTodoProps(Todo todo, User user) {
		todo.setCreatedOn(LocalDate.now());
		todo.setStatus(Status.CREATED.getStatus());
		todo.setUser(user);
		todo.setTitle(todo.getTitle().toUpperCase());
		return todo;
	}
	private List<Todo> updateOrDeleteTodo(TodoRequest todoRequest, boolean isUpdate) {
		List<Todo> result = new ArrayList<Todo>();
		List<Todo> todosForUser = getAllTodosForUser(todoRequest.getUserName());
		for (Todo todo : todoRequest.getTodos()) {
			Todo resultTodo = retriveTodoByTitle(todo.getTitle());
			for (Todo userTodo : todosForUser) {
				log.info(String.format("userTodo : [%s] doesnot exist for user", userTodo));
				log.error(String.format("Todo : [%s] doesnot exist for user", todo));
				log.error(String.format("resultTodo : [%s] doesnot exist for user", resultTodo));
				if (resultTodo.getTitle().equalsIgnoreCase(userTodo.getTitle())) {
					if (isUpdate) {
						resultTodo.setStatus(Status.UPDATED.getStatus());
						resultTodo.setUpdatedDate(LocalDate.now());
						todoRepository.save(resultTodo);
						result.add(resultTodo);
					} else {
						todoRepository.delete(resultTodo);
						result.add(resultTodo);
					}
					break;
				} else {
					log.error(String.format("Todo : [%s] doesnot exist for user", todo));
					if (!isUpdate) {
						throw new TodoServiceException(String.format("Todo doesnot exist for User: [%s]", resultTodo));
					}
				}
			}
			result.add(resultTodo);
		}
		return result;
	}

}
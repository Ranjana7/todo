package com.test.deloitte.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.swing.text.html.Option;

import org.assertj.core.util.Arrays;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.test.deloitte.api.model.TodoRequest;
import com.test.deloitte.dao.TodoRepository;
import com.test.deloitte.dao.UserRepository;
import com.test.deloitte.enums.Status;
import com.test.deloitte.model.Todo;
import com.test.deloitte.model.User;

@ExtendWith(MockitoExtension.class)
@Tag("unit")
class TodoServiceTest {

  private TodoService service;

  @Mock
  private TodoRepository todoRepositoryMock;
  @Mock
  private UserRepository userRepositoryMock;

  private TodoRequest request;
  private List<Todo> todos;
  private Todo todo;
  private Todo todo1;
  private User user;

  @BeforeEach
  void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    service = new TodoService(todoRepositoryMock, userRepositoryMock);
    setUpData();
  }

  private void setUpData() {
    user = new User(TestData.USERNAME);
    user.setTodos(todos);
    todo = new Todo(TestData.ANOTHER_TITLE, Status.CREATED.getStatus(), TestData.CURRENT_DATE);
    todo.setUser(user);
    todo.setId(1l);
    todo1 = new Todo("Test", Status.CREATED.getStatus(), TestData.CURRENT_DATE);
    todos = new ArrayList<>();
    todos.add(todo);

    request = new TodoRequest();
    request.setTodos(todos);
    request.setUserName(TestData.USERNAME);

    when(userRepositoryMock.findUserByName(anyString())).thenReturn(user);
  }

  @Test
  void getAllTodosForUser_todosFound_returnsListOfTodos() {
    setUpTodo();

    assertNotNull(service.getAllTodosForUser(user.getName()));
    verify(todoRepositoryMock).findByUser(user);
  }

  @Test
  void createTodo_validTodo_savedToDB() {
    when(todoRepositoryMock.saveAll(any())).thenReturn(todos);

    assertNotNull(service.createTodos(request));
    verify(todoRepositoryMock).saveAll(todos);

  }

  @Test
  void createTodos_inValidTodo_dbThrowsException() {
    when(todoRepositoryMock.saveAll(any())).thenThrow(new ConstraintViolationException(null, null, null));

    Assertions.assertThrows(ConstraintViolationException.class, () -> {
      service.createTodos(request);
    });
    verify(todoRepositoryMock).saveAll(todos);

  }

  @Test
  void createTodos_InValidTodoWithNullTitle_ThrowsException() {
    todo.setTitle(null);

    Assertions.assertThrows(NullPointerException.class, () -> {
      service.createTodos(request);
    });
    verify(todoRepositoryMock, times(0)).saveAll(todos);
  }
  
  @Test
  void updateTodos_todoFoundForUser_updated() {
    setUpTodo();
    
    List<Todo> updateTodos = service.updateTodos(request);
    
    verify(todoRepositoryMock).saveAll(todos);
    for(Todo todo : updateTodos) {
      assertEquals(todo.getStatus(), Status.UPDATED.getStatus());
    }
  }
  
  @Test
  void updateTodos_todoNotFoundForUser_created() {
    setUpTodo();
    request.getTodos().remove(todo);
    request.getTodos().add(todo1);
    List<Todo> updateTodos = service.updateTodos(request);
    
    for(Todo todo : updateTodos) {
      assertEquals(todo.getStatus(), Status.CREATED.getStatus());
    }
  }

  @Test
  void deleteTodos_todoFoundForUser_deleted() {
    setUpTodo();
    when(todoRepositoryMock.findByTitle(anyString())).thenReturn(Optional.of(todo));
    doAnswer((todo) -> {
      return null;
    }).when(todoRepositoryMock).deleteById(anyLong());
    
    assertNotNull(service.deleteTodos(request));
    verify(todoRepositoryMock).deleteById(todo.getId());
  }
  
  @Test
  void deleteTodos_todoNotFoundForUser_ThrowException() {
    setUpTodo();
    when(todoRepositoryMock.findByTitle(anyString())).thenReturn(Optional.of(todo1));
    
    Assertions.assertThrows(TodoServiceException.class, () -> {
      service.deleteTodos(request);
    });
    verify(todoRepositoryMock,times(0)).deleteById(todo1.getId());
  }
  
  private void setUpTodo() {
    when(todoRepositoryMock.findByUser(any())).thenReturn(todos);
  }
}

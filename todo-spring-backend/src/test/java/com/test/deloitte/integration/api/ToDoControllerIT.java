package com.test.deloitte.integration.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.deloitte.TodoServiceApplication;
import com.test.deloitte.api.model.TodoRequest;
import com.test.deloitte.enums.Status;
import com.test.deloitte.model.Todo;
import com.test.deloitte.model.User;
import com.test.deloitte.service.TestData;
import com.test.deloitte.service.TodoService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = TodoServiceApplication.class)
@AutoConfigureMockMvc
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
@Tag("integration")
class ToDoControllerIT {

  @Autowired
  private MockMvc mockMvc;
  @Autowired
  private ObjectMapper objectMapper;
  @Autowired
  private TodoService service;
  
  private TodoRequest todoRequest;
  private Todo todo;
  private List<Todo> todos;
  private User user;

  @BeforeEach
  void setUp() throws Exception {
    setUpData();
  }

  private void setUpData() {
    user = new User(TestData.USERNAME);
    user.setId(1L);
    todo = new Todo(TestData.TITLE, Status.CREATED.getStatus(), TestData.CURRENT_DATE);
    todos = new ArrayList<>();
    todos.add(todo);
    todoRequest = new TodoRequest();
    todoRequest.setTodos(todos);
    todoRequest.setUserName(TestData.USERNAME);
  }

  @Test
  void getAllTodosForUser_returnsListOfExistingTodos_Success() throws Exception {
    MvcResult response = mockMvc.perform(get("/v1/todos/"+TestData.USERNAME).contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(user)))
        .andExpect(status().isOk()).andReturn();

    assertEquals(service.getAllTodosForUser(TestData.USERNAME).size(),
        objectMapper.readValue(response.getResponse().getContentAsString(), new TypeReference<List<Todo>>() {
        }).size());
  }

  @Test
  void createTodosForUser_NoErrorFromDBAndService_Success() throws JsonProcessingException, Exception {
    int originalNumberOfTodos = service.getAllTodosForUser(TestData.USERNAME).size();
    todo.setTitle("test create");
    mockMvc.perform(post("/v1/todos").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(todoRequest)))
        .andExpect(status().isCreated()).andReturn();
    assertEquals(originalNumberOfTodos + todoRequest.getTodos().size(), service.getAllTodosForUser(TestData.USERNAME).size());
  }

  @Test
  void createTodosForUser_ValidationFailForNullTitle_BadRequestError() throws JsonProcessingException, Exception {
    int originalNumberOfTodos = service.getAllTodosForUser(TestData.USERNAME).size();
    todo.setTitle("");
    mockMvc.perform(post("/v1/todos").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(todoRequest)))
        .andExpect(status().isInternalServerError()).andReturn();
    assertEquals(originalNumberOfTodos, service.getAllTodosForUser(TestData.USERNAME).size());
  }

  @Test
  void updateTodosForUser_todoFoundForUser_updated() throws Exception {
    Todo originalTodo = service.retriveTodoByTitle(TestData.TITLE);
    int orifinalNumberOfTodos = service.getAllTodosForUser(TestData.USERNAME).size();
    MvcResult response = mockMvc
        .perform(put("/v1/todos").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(todoRequest)))
        .andExpect(status().isAccepted()).andReturn();
    assertNotEquals(originalTodo, objectMapper.readValue(response.getResponse().getContentAsString(), new TypeReference<List<Todo>>() {
    }).get(0));
    assertEquals(orifinalNumberOfTodos, service.getAllTodosForUser(TestData.USERNAME).size());
  }
  
  @Test
  void updateTodosForUser_todoNotFoundForUser_created() throws Exception {
    Todo originalTodo = service.retriveTodoByTitle(TestData.TITLE);
    todoRequest.setUserName(TestData.ANOTHER_USERNAME);
    user.setName(TestData.ANOTHER_USERNAME);
    int orifinalNumberOfTodos = service.getAllTodosForUser(TestData.USERNAME).size();
    MvcResult response = mockMvc
        .perform(put("/v1/todos").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(todoRequest)))
        .andExpect(status().isAccepted()).andReturn();
    assertNotEquals(originalTodo, objectMapper.readValue(response.getResponse().getContentAsString(), new TypeReference<List<Todo>>() {
    }).get(0));
    assertEquals(orifinalNumberOfTodos, service.getAllTodosForUser(TestData.USERNAME).size());
  }

  @Test
  void deleteTodo_todoFoundForUser_deleted() throws JsonProcessingException, Exception {
    int orifinalNumberOfTodos = service.getAllTodosForUser(TestData.USERNAME).size();
    mockMvc.perform(delete("/v1/todos").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(todoRequest)))
        .andExpect(status().isAccepted()).andReturn();
    assertTrue(orifinalNumberOfTodos > service.getAllTodosForUser(TestData.USERNAME).size());
  }


  @Test
  void deleteTodo_todoNotFoundForUser_ThrowException() throws JsonProcessingException, Exception {
    todoRequest.setUserName(TestData.ANOTHER_USERNAME);
    user.setName(TestData.ANOTHER_USERNAME);
    mockMvc.perform(delete("/v1/todos").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(todoRequest)))
        .andExpect(status().isServiceUnavailable()).andReturn();
  }
}

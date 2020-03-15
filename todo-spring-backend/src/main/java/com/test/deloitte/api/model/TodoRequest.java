package com.test.deloitte.api.model;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.test.deloitte.model.Todo;

import lombok.Data;

@Data
public class TodoRequest {
  
  @Valid
  @NotNull(message = "List of Todos can't be null")
  @NotEmpty(message = "Please specify at least 1 todo")
  @JsonProperty("todos")
  private List<Todo> todos;
  
  @NotBlank(message = "Username is mandatory")
  @JsonProperty("userName")
  private String userName;

}

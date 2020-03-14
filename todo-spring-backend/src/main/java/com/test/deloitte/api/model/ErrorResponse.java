package com.test.deloitte.api.model;

import java.time.ZonedDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ErrorResponse {

  @Getter
  @Setter
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
  ZonedDateTime timestamp = ZonedDateTime.now();

  @Getter
  @Setter
  private String code;

  @Getter
  @Setter
  private String message;

  @Getter
  @Setter
  @JsonProperty("path")
  private String path;
}
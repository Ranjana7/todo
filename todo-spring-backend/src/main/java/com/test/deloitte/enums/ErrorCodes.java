package com.test.deloitte.enums;

import lombok.Getter;

public enum ErrorCodes {
  INTERNAL_ERROR(1, "An Internal Error has occurred"), DATABASE_ERROR(2, "A database error has occurred"), INVALID_INPUT(3,
      "Invalid Input"), INVALID_STATE(4, "Invalid State"), SERVICE_UNAVAILABLE(5, "Service Unavailable"), UNSUPPORTED_TYPE(6, "Unsupported Type");

  @Getter
  private Integer code;

  @Getter
  private String message;

  ErrorCodes(Integer code, String message) {
    this.code = code;
    this.message = message;
  }

}

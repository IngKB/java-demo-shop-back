package com.pruebaclean.demoCl.auth.domain.useCases.signup;

public class SignupResponseModel {
  private String message;
  private Integer code;
  public SignupResponseModel(Integer code, String message) {
    this.message = message;
    this.code = code;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Integer getCode() {
    return code;
  }

  public void setCode(Integer code) {
    this.code = code;
  }
}

package nology.io.mitch.Exceptions;

import org.springframework.http.HttpStatus;

public class EmployeeNotFoundException extends Exception {

  private HttpStatus code;

  public EmployeeNotFoundException(String message, HttpStatus code) {
    super(message);
    this.code = code;
  }

  public HttpStatus getCode() {
    return code;
  }
}

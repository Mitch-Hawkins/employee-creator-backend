package nology.io.mitch.Exceptions;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public Map<String, String> handleInvalidArgument(
    MethodArgumentNotValidException ex
  ) {
    Map<String, String> errorMap = new HashMap<>();
    ex
      .getBindingResult()
      .getFieldErrors()
      .forEach(error -> {
        errorMap.put(error.getField(), error.getDefaultMessage());
      });
    return errorMap;
  }

  @ExceptionHandler(EmployeeNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public Map<String, String> handleNotFoundException(
    EmployeeNotFoundException ex
  ) {
    Map<String, String> errorMap = new HashMap<>();
    errorMap.put("errorMessage", ex.getMessage());
    errorMap.put("errorCode", ex.getCode().toString());
    return errorMap;
  }
}

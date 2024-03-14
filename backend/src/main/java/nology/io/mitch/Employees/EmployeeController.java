package nology.io.mitch.Employees;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import nology.io.mitch.Exceptions.EmployeeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

  @Autowired
  private EmployeeService employeeService;

  @GetMapping
  public ResponseEntity<List<Employee>> getAllEmployees() {
    List<Employee> allEmployees = this.employeeService.getAll();
    return new ResponseEntity<>(allEmployees, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id)
    throws EmployeeNotFoundException {
    Optional<Employee> maybeEmployee = this.employeeService.findById(id);
    if (maybeEmployee.isPresent()) {
      Employee foundEmployee = maybeEmployee.get();
      return new ResponseEntity<>(foundEmployee, HttpStatus.FOUND);
    } else {
      throw new EmployeeNotFoundException(
        "Employee with ID " + id + " not found.",
        HttpStatus.NOT_FOUND
      );
    }
  }

  @PostMapping
  public ResponseEntity<Employee> createEmployee(
    @Valid @RequestBody CreateEmployeeDTO data
  ) {
    Employee createdEmployee = this.employeeService.createEmployee(data);
    return new ResponseEntity<>(createdEmployee, HttpStatus.CREATED);
  }

  @PatchMapping("/{id}")
  public ResponseEntity<Employee> updateEmployeeById(
    @Valid @RequestBody UpdateEmployeeDTO data,
    @PathVariable Long id
  ) throws EmployeeNotFoundException {
    Optional<Employee> maybeUpdatedEmployee =
      this.employeeService.updateById(data, id);
    if (maybeUpdatedEmployee.isPresent()) {
      Employee foundUpdatedEmployee = maybeUpdatedEmployee.get();
      return new ResponseEntity<>(foundUpdatedEmployee, HttpStatus.OK);
    } else {
      throw new EmployeeNotFoundException(
        "Employee with ID " + id + " not found.",
        HttpStatus.NOT_FOUND
      );
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Employee> deleteEmployeeById(@PathVariable Long id)
    throws EmployeeNotFoundException {
    boolean deleted = this.employeeService.deleteById(id);
    if (!deleted) {
      throw new EmployeeNotFoundException(
        "Employee with ID " + id + " not found.",
        HttpStatus.NOT_FOUND
      );
    }
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}

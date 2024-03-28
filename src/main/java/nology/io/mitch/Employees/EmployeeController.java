package nology.io.mitch.Employees;

import jakarta.validation.Valid;
// import java.util.List;
import java.util.Optional;
import nology.io.mitch.Exceptions.EmployeeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

  @Autowired
  private EmployeeService employeeService;

  @GetMapping("/")
  public ResponseEntity<String> hello() {
    return new ResponseEntity<>(
      "This is to test that the app is running successfully",
      HttpStatus.OK
    );
  }

  @GetMapping
  public ResponseEntity<Page<Employee>> getAllEmployees(
    @RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = "8") int size
  ) {
    Page<Employee> allEmployees = this.employeeService.getAll(page, size);
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

  // @GetMapping("/{name}")
  // public ResponseEntity<Page<Employee>> findEmployeesByName(
  //   @PathVariable String name,
  //   @RequestParam(defaultValue = "0") int page,
  //   @RequestParam(defaultValue = "8") int size
  // ) throws EmployeeNotFoundException {
  //   Pageable pageable = PageRequest.of(page, size);
  //   Page<Employee> employees = employeeService.findByName(name, pageable);
  //   return new ResponseEntity<>(employees, HttpStatus.OK);
  // }

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

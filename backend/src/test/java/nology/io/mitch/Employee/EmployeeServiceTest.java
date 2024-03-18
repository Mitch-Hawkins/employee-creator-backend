package nology.io.mitch.Employee;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import nology.io.mitch.Employees.CreateEmployeeDTO;
import nology.io.mitch.Employees.Employee;
import nology.io.mitch.Employees.EmployeeRepository;
import nology.io.mitch.Employees.EmployeeService;
import nology.io.mitch.Employees.UpdateEmployeeDTO;
import nology.io.mitch.Exceptions.EmployeeNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.bind.MethodArgumentNotValidException;

public class EmployeeServiceTest {

  @Mock
  private EmployeeRepository mockEmployeeRepository;

  @InjectMocks
  private EmployeeService employeeService;

  @BeforeEach
  public void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void testGetAllEmployees() {
    Employee employee1 = new Employee(
      1L,
      "Mitch",
      "Hawkins",
      "mitch@mail.com",
      "1234567890"
    );
    Employee employee2 = new Employee(
      2L,
      "Kevin",
      "DeBruyne",
      "KDB@example.com",
      "1717171717"
    );
    List<Employee> expectedEmployees = Arrays.asList(employee1, employee2);

    when(mockEmployeeRepository.findAll()).thenReturn(expectedEmployees);

    List<Employee> actualEmployees = employeeService.getAll();

    assertEquals(expectedEmployees.size(), actualEmployees.size());
    assertEquals(expectedEmployees, actualEmployees);
  }

  @Test
  public void testGetEmployeeById() throws EmployeeNotFoundException {
    Employee employee1 = new Employee(
      1L,
      "Mitch",
      "Hawkins",
      "mitch@mail.com",
      "1234567890"
    );
    Employee employee2 = new Employee(
      2L,
      "Kevin",
      "DeBruyne",
      "KDB@example.com",
      "1717171717"
    );
    when(mockEmployeeRepository.findById(1L))
      .thenReturn(Optional.of(employee1));
    when(mockEmployeeRepository.findById(2L))
      .thenReturn(Optional.of(employee2));

    Optional<Employee> acutalEmployee1 = employeeService.findById(1l);
    assertEquals(employee1, acutalEmployee1.orElseThrow());
    Optional<Employee> acutalEmployee2 = employeeService.findById(2l);
    assertEquals(employee2, acutalEmployee2.orElseThrow());
  }

  @Test
  public void testCreateEmployee() throws MethodArgumentNotValidException {
    CreateEmployeeDTO employeeData = new CreateEmployeeDTO(
      "Mitch",
      "Hawkins",
      "mitch@mail.com",
      "1234567890"
    );
    Employee savedEmployee = new Employee(
      1L,
      "Mitch",
      "Hawkins",
      "mitch@mail.com",
      "1234567890"
    );
    when(mockEmployeeRepository.save(any(Employee.class)))
      .thenReturn(savedEmployee);

    Employee actualEmployee = employeeService.createEmployee(employeeData);
    assertEquals(savedEmployee, actualEmployee);
  }

  @Test
  public void testUpdateEmployee() throws EmployeeNotFoundException {
    UpdateEmployeeDTO employeeData = new UpdateEmployeeDTO(
      "Mitch",
      "Hawkins",
      "mitch@mail.com",
      "1234567890"
    );
    Employee savedEmployee = new Employee(
      1L,
      "Kevin",
      "DeBruyne",
      "KDB@example.com",
      "1717171717"
    );
    Employee updatedEmployee = new Employee(
      1L,
      "Mitch",
      "Hawkins",
      "mitch@mail.com",
      "1234567890"
    );
    when(mockEmployeeRepository.findById(1L))
      .thenReturn(Optional.of(savedEmployee));
    when(mockEmployeeRepository.save(savedEmployee))
      .thenReturn(updatedEmployee);

    Optional<Employee> actualEmployee = employeeService.updateById(
      employeeData,
      1L
    );
    assertEquals(updatedEmployee, actualEmployee.orElse(null));
  }
}

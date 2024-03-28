package nology.io.mitch.Employee;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import nology.io.mitch.Employees.CreateEmployeeDTO;
import nology.io.mitch.Employees.Employee;
import nology.io.mitch.Employees.EmployeeRepository;
import nology.io.mitch.Employees.EmployeeService;
import nology.io.mitch.Employees.EmploymentType;
import nology.io.mitch.Employees.UpdateEmployeeDTO;
import nology.io.mitch.Exceptions.EmployeeNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
      "Thomas",
      "Hawkins",
      "mitch@mail.com",
      "1234567890",
      EmploymentType.Casual,
      LocalDateTime.parse("2023-06-11T21:00:00"),
      12
    );
    Employee employee2 = new Employee(
      2L,
      "Kevin",
      "De",
      "Bruyne",
      "KDB@example.com",
      "1717171717",
      EmploymentType.FullTime,
      LocalDateTime.now(),
      17
    );
    List<Employee> expectedEmployees = Arrays.asList(employee1, employee2);

    Pageable pageable = PageRequest.of(0, 2);

    when(mockEmployeeRepository.findAll(pageable))
      .thenReturn(
        new PageImpl<>(expectedEmployees, pageable, expectedEmployees.size())
      );

    Page<Employee> actualEmployees = employeeService.getAll(0, 2);

    assertEquals(expectedEmployees.size(), actualEmployees.getSize());
    assertEquals(expectedEmployees, actualEmployees.getContent());
  }

  @Test
  public void testGetEmployeeById() throws EmployeeNotFoundException {
    Employee employee1 = new Employee(
      1L,
      "Mitch",
      "Thomas",
      "Hawkins",
      "mitch@mail.com",
      "1234567890",
      EmploymentType.Casual,
      LocalDateTime.parse("2023-06-11T21:00:00"),
      12
    );
    Employee employee2 = new Employee(
      2L,
      "Kevin",
      "De",
      "Bruyne",
      "KDB@example.com",
      "1717171717",
      EmploymentType.FullTime,
      LocalDateTime.now(),
      17
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
      "Thomas",
      "Hawkins",
      "mitch@mail.com",
      "1234567890",
      EmploymentType.Casual,
      "2023-06-11T21:00:00",
      12
    );
    Employee savedEmployee = new Employee(
      1L,
      "Mitch",
      "Thomas",
      "Hawkins",
      "mitch@mail.com",
      "1234567890",
      EmploymentType.Casual,
      LocalDateTime.parse("2023-06-11T21:00:00"),
      12
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
      "Thomas",
      "Hawkins",
      "mitch@mail.com",
      "1234567890",
      EmploymentType.Casual,
      "2023-06-11T21:00:00",
      12
    );
    Employee savedEmployee = new Employee(
      1L,
      "Kevin",
      "De",
      "Bruyne",
      "KDB@example.com",
      "1717171717",
      EmploymentType.FullTime,
      LocalDateTime.now(),
      17
    );
    Employee updatedEmployee = new Employee(
      1L,
      "Mitch",
      "Thomas",
      "Hawkins",
      "mitch@mail.com",
      "1234567890",
      EmploymentType.Casual,
      LocalDateTime.parse("2023-06-11T21:00:00"),
      12
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

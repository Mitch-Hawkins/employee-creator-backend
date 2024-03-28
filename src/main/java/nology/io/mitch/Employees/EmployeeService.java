package nology.io.mitch.Employees;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
// import java.util.List;
import java.util.Optional;
import nology.io.mitch.Exceptions.EmployeeNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class EmployeeService {

  @Autowired
  private EmployeeRepository repo;

  public Page<Employee> getAll(int page, int size) {
    PageRequest pageRequest = PageRequest.of(page, size);
    return this.repo.findAll(pageRequest);
  }

  public Employee createEmployee(@Valid CreateEmployeeDTO data) {
    // Employee newEmployee = new Employee(
    //   null,
    //   data.getFirstName(),
    //   data.getLastName(),
    //   data.getEmail(),
    //   data.getPhoneNumber()
    // );
    ModelMapper modelMapper = new ModelMapper();
    Employee newEmployee = modelMapper.map(data, Employee.class);
    return this.repo.save(newEmployee);
  }

  public Optional<Employee> findById(Long id) throws EmployeeNotFoundException {
    Optional<Employee> maybeEmployee = this.repo.findById(id);
    if (maybeEmployee.isPresent()) {
      return maybeEmployee;
    } else {
      throw new EmployeeNotFoundException(
        "Employee with ID " + id + " not found.",
        HttpStatus.NOT_FOUND
      );
    }
  }

  // public Page<Employee> findByName(String name, Pageable pageable)
  //   throws EmployeeNotFoundException {
  //   Page<Employee> employees = repo.findByName(name, pageable);
  //   if (!employees.isEmpty()) {
  //     return employees;
  //   } else {
  //     throw new EmployeeNotFoundException(
  //       "No Employees Found!",
  //       HttpStatus.NOT_FOUND
  //     );
  //   }
  // }

  public Optional<Employee> updateById(@Valid UpdateEmployeeDTO data, Long id)
    throws EmployeeNotFoundException {
    Optional<Employee> maybeEmployee = this.findById(id);
    if (maybeEmployee.isEmpty()) {
      return Optional.empty();
    }
    Employee foundEmployee = maybeEmployee.get();
    ModelMapper modelMapper = new ModelMapper();
    modelMapper.map(data, foundEmployee);
    return Optional.of(repo.save(foundEmployee));
  }

  public boolean deleteById(Long id) throws EmployeeNotFoundException {
    Optional<Employee> maybeEmployee = this.repo.findById(id);
    Employee employee = maybeEmployee.orElseThrow(() ->
      new EmployeeNotFoundException(
        "Employee with ID " + id + " not found.",
        HttpStatus.NOT_FOUND
      )
    );
    this.repo.delete(employee);
    return true;
  }
}

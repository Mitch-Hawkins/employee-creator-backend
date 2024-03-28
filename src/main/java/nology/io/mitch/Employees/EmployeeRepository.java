package nology.io.mitch.Employees;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
  //   Page<Employee> findByName(String firstName, Pageable pageable);
}

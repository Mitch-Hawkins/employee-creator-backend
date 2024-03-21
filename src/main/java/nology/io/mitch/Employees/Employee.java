package nology.io.mitch.Employees;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
// import lombok.AccessLevel;
// import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "employees")
@Data
// @AllArgsConstructor(access = AccessLevel.PUBLIC, staticName = "build")
@NoArgsConstructor
public class Employee {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column
  private Long id;

  @Column
  private String firstName;

  @Column
  private String middleName; //New

  @Column
  private String lastName;

  @Column
  private String email;

  @Column
  private String phoneNumber;

  @Column
  private EmploymentType employmentType; //New

  @Column
  private LocalDateTime startDate; //New

  @Column
  private int hoursPerWeek; //New

  public Employee(
    Long id,
    String firstName,
    String middleName,
    String lastName,
    String email,
    String phoneNumber,
    EmploymentType employmentType,
    LocalDateTime startDate,
    int hoursPerWeek
  ) {
    this.id = id;
    this.firstName = firstName;
    this.middleName = middleName;
    this.lastName = lastName;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.employmentType = employmentType;
    this.startDate = startDate;
    this.hoursPerWeek = hoursPerWeek;
  }
  // public Employee(
  //   Long id,
  //   String firstName,
  //   String lastName,
  //   String email,
  //   String phoneNumber
  // ) {
  //   this.id = id;
  //   this.firstName = firstName;
  //   this.lastName = lastName;
  //   this.email = email;
  //   this.phoneNumber = phoneNumber;
  // }
}

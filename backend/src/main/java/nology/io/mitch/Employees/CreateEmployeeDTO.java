package nology.io.mitch.Employees;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
// import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
// import lombok.AccessLevel;
// import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
// @AllArgsConstructor(access = AccessLevel.PUBLIC, staticName = "build")
@NoArgsConstructor
public class CreateEmployeeDTO {

  private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
    "yyyy-MM-dd'T'HH:mm:ss"
  );

  @NotBlank(message = "First name must not be left blank")
  private String firstName;

  private String middleName;

  @NotBlank(message = "Last name must not be left blank")
  private String lastName;

  @NotBlank(message = "Email must not be left blank")
  @Email(message = "Invalid Email Address")
  private String email;

  @Pattern(
    regexp = "^\\d{10}$",
    message = "Phone number must be a valid Australian Phone Number"
  )
  private String phoneNumber;

  @NotNull(message = "Employee must have an employment type selected")
  private EmployementType employementType;

  @NotBlank(message = "Employee must have a start date")
  @Pattern(
    regexp = "^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}$",
    // regexp = "^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}$",
    message = "Start Date must use correct formatting"
  )
  private String startDate;

  @Min(value = 1, message = "Employee must work a minimum of one hour per week")
  @Max(
    value = 168,
    message = "Employee work hours can not exceed 168 hours per week"
  )
  private int hoursPerWeek;

  public CreateEmployeeDTO(
    @NotBlank(message = "First name must not be left blank") String firstName,
    String middleName,
    @NotBlank(message = "Last name must not be left blank") String lastName,
    @NotBlank(message = "Email must not be left blank") @Email(
      message = "Invalid Email Address"
    ) String email,
    @Pattern(
      regexp = "^\\d{10}$",
      message = "Phone number must be a valid Australian Phone Number"
    ) String phoneNumber,
    @NotBlank(
      message = "Employee must have an employment type selected"
    ) EmployementType employementType,
    @NotBlank(message = "Employee must have a start date") @Pattern(
      regexp = "^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}$",
      message = "Start Date must use correct formatting"
    ) String startDate,
    @Min(
      value = 1,
      message = "Employee must work a minimum of one hour per week"
    ) @Max(
      value = 168,
      message = "Employee work hours can not exceed 168 hours per week"
    ) @NotBlank(
      message = "Please enter Employees weekly work hours"
    ) int hoursPerWeek
  ) {
    this.firstName = firstName;
    this.middleName = middleName;
    this.lastName = lastName;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.employementType = employementType;
    this.startDate = startDate;
    this.hoursPerWeek = hoursPerWeek;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getMiddleName() {
    return middleName;
  }

  public void setMiddleName(String middleName) {
    this.middleName = middleName;
  }

  public EmployementType getEmployementType() {
    return employementType;
  }

  public void setEmploymentType(EmployementType employmentType) {
    this.employementType = employmentType;
  }

  public LocalDateTime getStartDate() {
    return startDate != null ? LocalDateTime.parse(startDate, formatter) : null;
  }

  public void setStartDate(LocalDateTime startDate) {
    this.startDate = startDate != null ? startDate.format(formatter) : null;
  }

  public int getHoursPerWeek() {
    return hoursPerWeek;
  }

  public void setHoursPerWeek(int hoursPerWeek) {
    this.hoursPerWeek = hoursPerWeek;
  }
}

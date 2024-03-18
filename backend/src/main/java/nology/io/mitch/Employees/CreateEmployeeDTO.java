package nology.io.mitch.Employees;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
// import lombok.AccessLevel;
// import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
// @AllArgsConstructor(access = AccessLevel.PUBLIC, staticName = "build")
@NoArgsConstructor
public class CreateEmployeeDTO {

  @NotBlank(message = "First name must not be left blank")
  private String firstName;

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

  public CreateEmployeeDTO(
    @NotBlank(message = "First name must not be left blank") String firstName,
    @NotBlank(message = "Last name must not be left blank") String lastName,
    @NotBlank(message = "Email must not be left blank") @Email(
      message = "Invalid Email Address"
    ) String email,
    @Pattern(
      regexp = "^\\d{10}$",
      message = "Phone number must be a valid Australian Phone Number"
    ) String phoneNumber
  ) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.phoneNumber = phoneNumber;
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
}

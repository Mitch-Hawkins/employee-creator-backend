package nology.io.mitch.Employees;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UpdateEmployeeDTO {

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

  public UpdateEmployeeDTO(
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
}

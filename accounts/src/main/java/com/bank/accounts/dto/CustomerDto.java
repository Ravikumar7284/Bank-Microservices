package com.bank.accounts.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDto {

  @NotEmpty(message = "Name should not be empty")
  @Size(min = 5, max = 30, message = "Name should be between 5 and 30 characters")
  private String name;

  @NotEmpty(message = "Email should not be empty")
  @Email(message = "Email should be valid")
  private String email;

  @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number should be 10 digits")
  private String mobileNumber;
  private AccountsDto accountsDto;
}

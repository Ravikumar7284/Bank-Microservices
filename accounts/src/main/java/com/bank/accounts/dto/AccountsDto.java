package com.bank.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(
    name = "Accounts",
    description = "Accounts details"
)
public class AccountsDto {

  @Schema(
      description = "Account number", example = "1234567890"
  )
  @NotEmpty(message = "Account number should not be empty")
  @Pattern(regexp = "(^$|[0-9]{10})", message = "Account number should be 10 digits")
  private Long accountNumber;

  @Schema(
      description = "Account type", example = "Savings"
  )
  @NotEmpty(message = "Account type should not be empty")
  private String accountType;

  @Schema(
      description = "Branch address", example = "Mumbai, India"
  )
  @NotEmpty(message = "Branch address should not be empty")
  private String branchAddress;
}

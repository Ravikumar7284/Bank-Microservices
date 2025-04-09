package com.bank.accounts.controller;

import com.bank.accounts.constants.AccountConstants;
import com.bank.accounts.dto.CustomerDto;
import com.bank.accounts.dto.ErrorResponseDto;
import com.bank.accounts.dto.ResponseDto;
import com.bank.accounts.service.AccountsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/accounts", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Validated
@Tag(name = "Accounts CRUD API", description = "Accounts APIs for CREATE,UPDATE,FETCH,DELETE")
public class AccountsController {

  private AccountsService accountsService;

  @Operation(
      summary = "Create a new account"
  )
  @ApiResponse(
      responseCode = "201", description = "Account created successfully"
  )
  @PostMapping("/create")
  ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto) {
    accountsService.createAccount(customerDto);
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(new ResponseDto(AccountConstants.STATUS_201, AccountConstants.MESSAGE_201));
  }

  @Operation(
      summary = "Fetch account details by mobile number"
  )
  @ApiResponse(
      responseCode = "200", description = "Account details fetched successfully"
  )
  @GetMapping("/fetch")
  ResponseEntity<CustomerDto> fetchCustomerByMobileNumber(@RequestParam
  @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number should be 10 digits")
  String mobileNumber) {
    CustomerDto customerDto = accountsService.fetchCustomerByMobileNumber(mobileNumber);
    return ResponseEntity.status(HttpStatus.OK).body(customerDto);
  }

  @Operation(
      summary = "Update account details"
  )
  @ApiResponses(
      value = {
          @ApiResponse(responseCode = "200", description = "Account details updated successfully"),
          @ApiResponse(responseCode = "417", description = "Exception failed to update account details"),
          @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(
              schema = @Schema(implementation = ErrorResponseDto.class)
          ))
      }
  )
  @PutMapping("/update")
  ResponseEntity<ResponseDto> updateAccountDetails(@Valid @RequestBody CustomerDto customerDto) {
    boolean isUpdated = accountsService.updateAccount(customerDto);
    if (isUpdated) {
      return ResponseEntity.status(HttpStatus.OK)
          .body(new ResponseDto(AccountConstants.STATUS_200, AccountConstants.MESSAGE_200));
    } else {
      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
          .body(new ResponseDto(AccountConstants.STATUS_417, AccountConstants.MESSAGE_417_UPDATE));
    }
  }

  @Operation(
      summary = "Delete account by mobile number"
  )
  @ApiResponses(
      value = {
          @ApiResponse(responseCode = "200", description = "Account deleted successfully"),
          @ApiResponse(responseCode = "417", description = "Exception failed to delete account"),
          @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content(
              schema = @Schema(implementation = ErrorResponseDto.class)
          ))
      }
  )
  @DeleteMapping("/delete")
  ResponseEntity<ResponseDto> deleteAccount(@RequestParam
  @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number should be 10 digits")
  String mobileNumber) {
    boolean isDeleted = accountsService.deleteAccount(mobileNumber);
    if (isDeleted) {
      return ResponseEntity.status(HttpStatus.OK)
          .body(new ResponseDto(AccountConstants.STATUS_200, AccountConstants.MESSAGE_200));
    } else {
      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
          .body(new ResponseDto(AccountConstants.MESSAGE_417_DELETE, AccountConstants.MESSAGE_417_DELETE));
    }
  }
}

package com.bank.loans.controller;

import com.bank.loans.constants.LoansConstants;
import com.bank.loans.dto.ErrorResponseDto;
import com.bank.loans.dto.LoansDto;
import com.bank.loans.dto.ResponseDto;
import com.bank.loans.service.LoansService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
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


@Tag(
    name = "Loans CRUD APIs",
    description = "CRUD REST APIs to CREATE, UPDATE, FETCH AND DELETE loans"
)
@RestController
@RequestMapping(path = "api/loans", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class LoansController {

  private final LoansService loansService;

  public LoansController(LoansService loansService) {
    this.loansService = loansService;
  }

  @Value("${build.version}")
  private String buildVersion;

  @Operation(
      summary = "Create a new loan"
  )
  @ApiResponses(
      value = {
          @ApiResponse(
              responseCode = "201",
              description = "loan created successfully"
          ),
          @ApiResponse(
              responseCode = "500",
              description = "An error occurred. Please try again or contact us",
              content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
          )
      }
  )
  @PostMapping("/create")
  public ResponseEntity<ResponseDto> createLoan(@RequestParam
  @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
  String mobileNumber) {
    loansService.createLoan(mobileNumber);
    return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(new ResponseDto(LoansConstants.STATUS_201, LoansConstants.MESSAGE_201));
  }

  @Operation(
      summary = "Fetch card details by mobile number"
  )
  @ApiResponses(
      value = {
          @ApiResponse(
              responseCode = "200",
              description = "Loan details fetched successfully"
          ),
          @ApiResponse(
              responseCode = "500",
              description = "An error occurred. Please try again or contact us",
              content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
          )
      }
  )
  @GetMapping("/fetch")
  public ResponseEntity<LoansDto> fetchLoanDetails(@RequestParam
  @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
  String mobileNumber) {
    LoansDto loansDto = loansService.fetchLoanDetails(mobileNumber);
    return ResponseEntity.status(HttpStatus.OK).body(loansDto);
  }

  @Operation(
      summary = "Update loan details"
  )
  @ApiResponses(
      value = {
          @ApiResponse(
              responseCode = "200",
              description = "Loan details updated successfully"
          ),
          @ApiResponse(
              responseCode = "417",
              description = "Exception failed to update loan details. Please try again or contact us",
              content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
          ),
          @ApiResponse(
              responseCode = "500",
              description = "An error occurred. Please try again or contact us",
              content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
          )
      }
  )
  @PutMapping("/update")
  public ResponseEntity<ResponseDto> updateLoanDetails(@Valid @RequestBody LoansDto loansDto) {
    boolean isUpdated = loansService.updateLoansDetails(loansDto);
    if (isUpdated) {
      return ResponseEntity
          .status(HttpStatus.OK)
          .body(new ResponseDto(LoansConstants.STATUS_200, LoansConstants.MESSAGE_200));
    } else {
      return ResponseEntity
          .status(HttpStatus.EXPECTATION_FAILED)
          .body(new ResponseDto(LoansConstants.STATUS_417, LoansConstants.MESSAGE_417_UPDATE));
    }
  }

  @Operation(
      summary = "Delete loan details by mobile number"
  )
  @ApiResponses(
      value = {
          @ApiResponse(
              responseCode = "200",
              description = "loan details deleted successfully"
          ),
          @ApiResponse(
              responseCode = "417",
              description = "Exception failed to delete loan details. Please try again or contact us",
              content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
          ),
          @ApiResponse(
              responseCode = "500",
              description = "An error occurred. Please try again or contact us",
              content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
          )
      }
  )
  @DeleteMapping("/delete")
  public ResponseEntity<ResponseDto> deleteLoanDetails(@RequestParam
  @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
  String mobileNumber) {
    boolean isDeleted = loansService.deleteLoan(mobileNumber);
    if (isDeleted) {
      return ResponseEntity
          .status(HttpStatus.OK)
          .body(new ResponseDto(LoansConstants.STATUS_200, LoansConstants.MESSAGE_200));
    } else {
      return ResponseEntity
          .status(HttpStatus.EXPECTATION_FAILED)
          .body(new ResponseDto(LoansConstants.STATUS_417, LoansConstants.MESSAGE_417_DELETE));
    }
  }

  @Operation(
      summary = "Get build version information"
  )
  @ApiResponses(
      value = {
          @ApiResponse(
              responseCode = "200",
              description = "Build version fetch successfully"
          ),
          @ApiResponse(
              responseCode = "500",
              description = "An error occurred. Please try again or contact us",
              content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
          )
      }
  )
  @GetMapping("/build-version")
  public ResponseEntity<String> getBuildInfo() {
    return ResponseEntity.status(HttpStatus.OK).body(buildVersion);
  }

}

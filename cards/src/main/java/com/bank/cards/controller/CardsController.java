package com.bank.cards.controller;

import com.bank.cards.constants.CardsConstants;
import com.bank.cards.dto.CardsDto;
import com.bank.cards.dto.ErrorResponseDto;
import com.bank.cards.dto.ResponseDto;
import com.bank.cards.service.CardsService;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(
    name = "Cards CRUD API",
    description = "Cards APIs for CREATE,FETCH,UPDATE,DELETE"
)
@RestController
@RequestMapping(path = "/api/cards", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class CardsController {

  private CardsService cardsService;

  @Value("${build.version}")
  private String buildVersion;

  @Operation(
      summary = "Create a new card"
  )
  @ApiResponses(
      value = {
          @ApiResponse(
              responseCode = "201",
              description = "Card created successfully"
          ),
          @ApiResponse(
              responseCode = "500",
              description = "An error occurred. Please try again or contact us",
              content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
          )
      }
  )
  @PostMapping("/create")
  public ResponseEntity<ResponseDto> createCard(
      @Valid @RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number should be 10 digits")
      String mobileNumber) {
    cardsService.createCard(mobileNumber);
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(new ResponseDto(CardsConstants.STATUS_201, CardsConstants.MESSAGE_201));
  }

  @Operation(
      summary = "Fetch card details by mobile number"
  )
  @ApiResponses(
      value = {
          @ApiResponse(
              responseCode = "200",
              description = "Card details fetched successfully"
          ),
          @ApiResponse(
              responseCode = "500",
              description = "An error occurred. Please try again or contact us",
              content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
          )
      }
  )
  @GetMapping("/fetch")
  public ResponseEntity<CardsDto> fetchCardDetails(
      @RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number should be 10 digits") String mobileNumber) {
    CardsDto cardsDto = cardsService.fetchCardDetails(mobileNumber);
    return ResponseEntity.status(HttpStatus.OK).body(cardsDto);
  }

  @Operation(
      summary = "Update card details"
  )
  @ApiResponses(
      value = {
          @ApiResponse(
              responseCode = "200",
              description = "Card details updated successfully"
          ),
          @ApiResponse(
              responseCode = "417",
              description = "Exception failed to update card details. Please try again or contact us",
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
  public ResponseEntity<ResponseDto> updateCardDetails(@Valid @RequestBody CardsDto cardsDto) {
    boolean isUpdated = cardsService.updateCardDetails(cardsDto);
    if (isUpdated) {
      return ResponseEntity.status(HttpStatus.OK)
          .body(new ResponseDto(CardsConstants.STATUS_200, CardsConstants.MESSAGE_200));
    } else {
      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
          .body(new ResponseDto(CardsConstants.STATUS_417, CardsConstants.MESSAGE_417_UPDATE));
    }
  }

  @Operation(
      summary = "Delete card details by mobile number"
  )
  @ApiResponses(
      value = {
          @ApiResponse(
              responseCode = "200",
              description = "Card details deleted successfully"
          ),
          @ApiResponse(
              responseCode = "417",
              description = "Exception failed to delete card details. Please try again or contact us",
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
  public ResponseEntity<ResponseDto> deleteCardDetails(
      @Valid @RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number should be 10 digits") String mobileNumber) {
    boolean isDeleted = cardsService.deleteCardDetails(mobileNumber);
    if (isDeleted) {
      return ResponseEntity.status(HttpStatus.OK)
          .body(new ResponseDto(CardsConstants.STATUS_200, CardsConstants.MESSAGE_200));
    } else {
      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
          .body(new ResponseDto(CardsConstants.STATUS_417, CardsConstants.MESSAGE_417_DELETE));
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
  public ResponseEntity<String> getBuildVersion() {
    return ResponseEntity.status(HttpStatus.OK).body(buildVersion);
  }

}

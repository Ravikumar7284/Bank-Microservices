package com.bank.cards.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@Schema(
    name = "ErrorResponse",
    description = "Error response information"
)
public class ErrorResponseDto {

  @Schema(
      description = "API URL of the request", example = "http://localhost:8080/accounts"
  )
  private String apiUrl;

  @Schema(
      description = "HTTP status code of the response", example = "500"
  )
  private HttpStatus errorCode;

  @Schema(
      description = "Error message of the response", example = "Internal server error"
  )
  private String errorMessage;

  @Schema(
      description = "Timestamp of the error", example = "2023-01-01T00:00:00"
  )
  private LocalDateTime errorTime;
}

package com.bank.accounts.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class ErrorResponseDto {

  private String apiUrl;
  private HttpStatus errorCode;
  private String errorMessage;
  private LocalDateTime errorTime;
}

package com.bank.cards.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

  public ResourceNotFoundException(String card, String mobileNumber, String mobileNumber1) {
    super(String.format("%s not found with %s : '%s'", card, mobileNumber, mobileNumber1));
  }
}

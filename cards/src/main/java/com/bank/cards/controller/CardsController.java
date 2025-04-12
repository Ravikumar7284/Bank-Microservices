package com.bank.cards.controller;

import com.bank.cards.constants.CardsConstands;
import com.bank.cards.dto.ResponseDto;
import com.bank.cards.service.CardsService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(
    name = "Cards CRUD API",
    description = "Cards APIs for CREATE,FETCH,UPDATE,DELETE"
)
@RestController
@RequestMapping(path = "/api/cards", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Validated
public class CardsController {

  private CardsService cardsService;

  public ResponseEntity<ResponseDto> createCard(
      @Valid @RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number should be 10 digits")
      String mobileNumber) {
    cardsService.createCard(mobileNumber);
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(new ResponseDto(CardsConstands.STATUS_201, CardsConstands.MESSAGE_201));
  }
}

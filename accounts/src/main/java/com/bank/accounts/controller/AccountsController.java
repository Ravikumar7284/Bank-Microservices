package com.bank.accounts.controller;

import com.bank.accounts.constants.AccountConstants;
import com.bank.accounts.dto.AccountsDto;
import com.bank.accounts.dto.CustomerDto;
import com.bank.accounts.dto.ResponseDto;
import com.bank.accounts.service.AccountsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/accounts", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class AccountsController {

  private AccountsService accountsService;

  @PostMapping("/create")
  ResponseEntity<ResponseDto> createAccount(@RequestBody CustomerDto customerDto) {
    accountsService.createAccount(customerDto);
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(new ResponseDto(AccountConstants.STATUS_201, AccountConstants.MESSAGE_201));
  }
}

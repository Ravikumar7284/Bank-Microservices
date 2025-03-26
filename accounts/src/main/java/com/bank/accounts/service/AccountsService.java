package com.bank.accounts.service;

import com.bank.accounts.constants.AccountConstants;
import com.bank.accounts.dto.CustomerDto;
import com.bank.accounts.entity.Accounts;
import com.bank.accounts.entity.Customer;
import com.bank.accounts.exception.CustomerAlreadyExistsException;
import com.bank.accounts.mapper.AccountsMapper;
import com.bank.accounts.mapper.CustomerMapper;
import com.bank.accounts.repository.AccountsRepository;
import com.bank.accounts.repository.CustomerRepository;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;
import org.springframework.stereotype.Service;

@Service
public class AccountsService {

  private final AccountsRepository accountsRepository;
  private final CustomerRepository customerRepository;
  private final CustomerMapper customerMapper;
  private final AccountsMapper accountsMapper;

  public AccountsService(AccountsRepository accountsRepository,
      CustomerRepository customerRepository, CustomerMapper customerMapper,
      AccountsMapper accountsMapper) {
    this.accountsRepository = accountsRepository;
    this.customerRepository = customerRepository;
    this.accountsMapper = accountsMapper;
    this.customerMapper = customerMapper;
  }

  public void createAccount(CustomerDto customerDto) {
    Customer customer = customerMapper.mapToCustomer(customerDto);
    Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(
        customer.getMobileNumber());
    if (optionalCustomer.isPresent()) {
      throw new CustomerAlreadyExistsException(
          "Customer already registered with given mobile number: " + customerDto.getMobileNumber());
    }
    customer.setCreatedAt(LocalDateTime.now());
    customer.setCreatedBy("Admin");
    Customer savedCustomer = customerRepository.save(customer);
    accountsRepository.save(createAccount(savedCustomer));
  }

  private Accounts createAccount(Customer customer) {
    Accounts account = new Accounts();
    account.setCustomerId(customer.getCustomerId());
    Long accountNumber = 10000L + new Random().nextInt(90000000);
    account.setAccountNumber(accountNumber);
    account.setAccountType(AccountConstants.SAVINGS);
    account.setBranchAddress(AccountConstants.ADDRESS);
    account.setCreatedAt(LocalDateTime.now());
    account.setCreatedBy("Admin");
    return account;
  }
}

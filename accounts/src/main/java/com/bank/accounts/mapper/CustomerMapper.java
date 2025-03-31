package com.bank.accounts.mapper;

import com.bank.accounts.dto.CustomerDto;
import com.bank.accounts.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

  Customer mapToCustomer(CustomerDto customerDto);

  CustomerDto mapToCustomerDto(Customer customer);

  @Mapping(target = "customerId", ignore = true)
  Customer updateToCustomer(CustomerDto customerDto, @MappingTarget Customer customer);
}

package com.bank.loans.mapper;

import com.bank.loans.dto.LoansDto;
import com.bank.loans.entity.Loans;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LoansMapper {

  Loans mapToLoans(LoansDto loansDto);

  LoansDto mapToLoansDo(Loans loans);

  @Mapping(target = "loanId", ignore = true)
  Loans updateToLoans(LoansDto loansDto, Loans loans);

}

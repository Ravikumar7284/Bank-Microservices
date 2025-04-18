package com.bank.loans.service;

import com.bank.loans.constants.LoansConstants;
import com.bank.loans.dto.LoansDto;
import com.bank.loans.entity.Loans;
import com.bank.loans.exception.LoansAlreadyExistsException;
import com.bank.loans.exception.ResourceNotFoundException;
import com.bank.loans.mapper.LoansMapper;
import com.bank.loans.repository.LoansRepository;
import java.util.Optional;
import java.util.Random;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LoansService {

  private LoansRepository loansRepository;
  private LoansMapper loansMapper;

  public void createLoan(String mobileNumber) {
    Optional<Loans> optionalLoans = loansRepository.findByMobileNumber(mobileNumber);
    if (optionalLoans.isPresent()) {
      throw new LoansAlreadyExistsException(
          "Loan already exists with mobile number " + mobileNumber);
    }
    loansRepository.save(createNewLoan(mobileNumber));
  }

  private Loans createNewLoan(String mobileNumber) {
    Loans loans = new Loans();
    Long randomLoanNumber = 10000L + new Random().nextInt(90000000);
    loans.setLoanNumber(String.valueOf(randomLoanNumber));
    loans.setMobileNumber(mobileNumber);
    loans.setLoanType(LoansConstants.HOME_LOAN);
    loans.setTotalLoan(LoansConstants.NEW_LOAN_LIMIT);
    loans.setAmountPaid(0);
    loans.setOutstandingAmount(LoansConstants.NEW_LOAN_LIMIT);
    return loans;
  }

  public LoansDto fetchLoanDetails(String mobileNumber) {
    Loans loans = loansRepository.findByMobileNumber(mobileNumber).orElseThrow(
        () -> new ResourceNotFoundException("Loan", "mobileNumber", mobileNumber));
    return loansMapper.mapToLoansDo(loans);
  }

  public boolean updateLoansDetails(LoansDto loansDto) {
    Loans loans = loansRepository.findByLoanNumber(loansDto.getLoanNumber()).orElseThrow(
        () -> new ResourceNotFoundException("Loan", "loanNumber", loansDto.getLoanNumber()));
    loansMapper.updateToLoans(loansDto, loans);
    loansRepository.save(loans);
    return true;
  }

  public boolean deleteLoan(String mobileNumber) {
    Loans loans = loansRepository.findByMobileNumber(mobileNumber).orElseThrow(
        () -> new ResourceNotFoundException("Loan", "mobileNumber", mobileNumber));
    loansRepository.deleteById(loans.getLoadId());
    return true;
  }

}

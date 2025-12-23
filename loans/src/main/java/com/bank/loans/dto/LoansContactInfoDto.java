package com.bank.loans.dto;

import java.util.List;
import java.util.Map;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "loans-contact-info")
@Data
public class LoansContactInfoDto {
  private String message;
  private Map<String, String> contactDetails;
  private List<String> contactSupport;
}

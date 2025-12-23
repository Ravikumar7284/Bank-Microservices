package com.bank.accounts.dto;

import java.util.List;
import java.util.Map;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "accounts-contact-info")
@Data
public class AccountsContactInfoDto {
  private String message;
  private Map<String, String> contactDetails;
  private List<String> contactSupport;
}

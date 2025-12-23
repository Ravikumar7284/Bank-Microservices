package com.bank.cards.dto;

import java.util.List;
import java.util.Map;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "cards-contact-info")
@Data
public class CardsContactInfoDto {
  private String message;
  private Map<String, String> contactDetails;
  private List<String> contactSupport;
}

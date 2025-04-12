package com.bank.cards.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "cards")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Cards extends Metadata {

  @Id
  @Column(name = "card_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long cardId;

  @Column(name = "mobile_number")
  private String mobileNumber;

  @Column(name = "card_number")
  private String cardNumber;

  @Column(name = "card_type")
  private String cardType;

  @Column(name = "total_limit")
  private Integer totalLimit;

  @Column(name = "amount_used")
  private Integer amountUsed;

  @Column(name = "available_amount")
  private Integer availableAmount;

}

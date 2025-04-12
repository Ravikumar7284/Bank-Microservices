package com.bank.cards.service;

import com.bank.cards.constants.CardsConstants;
import com.bank.cards.dto.CardsDto;
import com.bank.cards.entity.Cards;
import com.bank.cards.exception.CardAlreadyExistsException;
import com.bank.cards.mapper.CardsMapper;
import com.bank.cards.repository.CardsRepositroy;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import java.util.Optional;
import java.util.Random;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CardsService {

  private CardsRepositroy cardsRepositroy;
  private CardsMapper cardsMapper;

  public void createCard(String mobileNumber) {
    Optional<Cards> optionalCards = cardsRepositroy.findByMobileNumber(mobileNumber);
    if (optionalCards.isPresent()) {
      throw new CardAlreadyExistsException(
          "Card already exists with mobile number " + mobileNumber);
    }
    cardsRepositroy.save(createNewCard(mobileNumber));
  }

  private Cards createNewCard(String mobileNumber) {
    Cards newCards = new Cards();
    Long cardNumber = 10000L + new Random().nextInt(90000000);
    newCards.setCardNumber(String.valueOf(cardNumber));
    newCards.setMobileNumber(mobileNumber);
    newCards.setCardType(CardsConstants.CREDIT_CARD);
    newCards.setTotalLimit(CardsConstants.NEW_CARD_LIMIT);
    newCards.setAmountUsed(0);
    newCards.setAvailableAmount(CardsConstants.NEW_CARD_LIMIT);
    return newCards;
  }

  public CardsDto fetchCardDetails(String mobileNumber) {
    return null;
  }

  public boolean deleteCardDetails(String mobileNumber) {
    return false;
  }

  public boolean updateCardDetails(CardsDto cardsDto) {
    return false;
  }
}

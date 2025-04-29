package com.bank.cards.service;

import com.bank.cards.constants.CardsConstants;
import com.bank.cards.dto.CardsDto;
import com.bank.cards.entity.Cards;
import com.bank.cards.exception.CardAlreadyExistsException;
import com.bank.cards.exception.ResourceNotFoundException;
import com.bank.cards.mapper.CardsMapper;
import com.bank.cards.repository.CardsRepository;
import java.util.Optional;
import java.util.Random;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CardsService {

  private CardsRepository cardsRepository;
  private CardsMapper cardsMapper;

  public void createCard(String mobileNumber) {
    Optional<Cards> optionalCards = cardsRepository.findByMobileNumber(mobileNumber);
    if (optionalCards.isPresent()) {
      throw new CardAlreadyExistsException(
          "Card already exists with mobile number " + mobileNumber);
    }
    cardsRepository.save(createNewCard(mobileNumber));
  }

  private Cards createNewCard(String mobileNumber) {
    Cards newCards = new Cards();
    Long cardNumber = 100000000000L + new Random().nextLong(899999999999L);
    newCards.setCardNumber(String.valueOf(cardNumber));
    newCards.setMobileNumber(mobileNumber);
    newCards.setCardType(CardsConstants.CREDIT_CARD);
    newCards.setTotalLimit(CardsConstants.NEW_CARD_LIMIT);
    newCards.setAmountUsed(0);
    newCards.setAvailableAmount(CardsConstants.NEW_CARD_LIMIT);
    return newCards;
  }

  public CardsDto fetchCardDetails(String mobileNumber) {
    Cards cards = cardsRepository.findByMobileNumber(mobileNumber)
        .orElseThrow(() -> new ResourceNotFoundException("Card", "mobileNumber", mobileNumber));
    return cardsMapper.mapToCardsDto(cards);
  }

  public boolean deleteCardDetails(String mobileNumber) {
    Cards cards = cardsRepository.findByMobileNumber(mobileNumber).orElseThrow(
        () -> new ResourceNotFoundException("Card", "mobileNumber", mobileNumber));
    cardsRepository.deleteById(cards.getCardId());
    return true;
  }

  public boolean updateCardDetails(CardsDto cardsDto) {
    Cards cards = cardsRepository.findByCardNumber(cardsDto.getCardNumber()).orElseThrow(
        () -> new ResourceNotFoundException("Card", "cardNumber", cardsDto.getCardNumber()));
    cardsMapper.updateToCards(cardsDto, cards);
    cardsRepository.save(cards);
    return true;
  }

}


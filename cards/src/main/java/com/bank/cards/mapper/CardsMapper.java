package com.bank.cards.mapper;

import com.bank.cards.dto.CardsDto;
import com.bank.cards.entity.Cards;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CardsMapper {

  CardsDto mapToCardsDto(CardsDto cardsDto);

  Cards mapToCards(CardsDto cardsDto);

  @Mapping(target = "cardId", ignore = true)
  Cards updateToCards(CardsDto cardsDto, @MappingTarget Cards cards);
}

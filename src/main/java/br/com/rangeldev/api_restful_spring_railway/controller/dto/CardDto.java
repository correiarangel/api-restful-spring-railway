package br.com.rangeldev.api_restful_spring_railway.controller.dto;

import java.math.BigDecimal;

import br.com.rangeldev.api_restful_spring_railway.domain.entitties.Card;

public record CardDto(Long id, String number, BigDecimal limit) {
    public CardDto(Card entity) {
        this(entity.getId(), entity.getNumber(), entity.getLimit());
    }
    public Card toEntity() {
        Card _entity = new Card();
        _entity.setId(this.id);
        _entity.setNumber(this.number);
        _entity.setLimit(this.limit);
        return _entity;
    }
}

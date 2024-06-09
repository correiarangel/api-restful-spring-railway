package br.com.rangeldev.api_restful_spring_railway.controller.dto;

import java.math.BigDecimal;

import br.com.rangeldev.api_restful_spring_railway.domain.entitties.Account;

public record AccountDto(Long id, String number, String agency, BigDecimal balance, BigDecimal limit) {
    public AccountDto(Account entity){
        this(entity.getId(), entity.getNumber(), entity.getAgency(), entity.getBalance(), entity.getLimit());
    }
    public Account toEntity() {
        Account _entity = new Account();
        _entity.setId(this.id);
        _entity.setNumber(this.number);
        _entity.setAgency(this.agency);
        _entity.setBalance(this.balance);
        _entity.setLimit(this.limit);
        return _entity;
    }
}

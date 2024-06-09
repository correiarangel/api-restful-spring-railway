package br.com.rangeldev.api_restful_spring_railway.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.rangeldev.api_restful_spring_railway.domain.entitties.User;

public interface IUserRepository extends JpaRepository<User,Long>{
    boolean existsByAccountNumber(String number);
    boolean existsByCardNumber(String number);
}

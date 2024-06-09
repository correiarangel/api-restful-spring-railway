package br.com.rangeldev.api_restful_spring_railway.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.Optional.ofNullable;
import br.com.rangeldev.api_restful_spring_railway.domain.entitties.User;
import br.com.rangeldev.api_restful_spring_railway.domain.repository.IUserRepository;
import br.com.rangeldev.api_restful_spring_railway.service.exeception.BusinessException;
import br.com.rangeldev.api_restful_spring_railway.service.exeception.NotFoundException;

@Service
public class UserServiceImpl implements IUserService {
    /**
     * ID de usuário utilizado na Santander Dev Week 2024.
     * Por isso, vamos criar algumas regras para mantê-lo integro.
     */
    private static final Long UNCHANGEABLE_USER_ID = 1L;

    private final IUserRepository repository;

    public UserServiceImpl(IUserRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<User> findAll() {
        return this.repository.findAll();
    }

    @Transactional(readOnly = true)
    public User findById(Long id) {
        return this.repository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Transactional
    public User create(User userToCreate) {
        ofNullable(userToCreate).orElseThrow(() -> new BusinessException("Ops! User to create must not be null."));
        ofNullable(userToCreate.getAccount()).orElseThrow(() -> new BusinessException("Ops! User account must not be null."));
        ofNullable(userToCreate.getCard()).orElseThrow(() -> new BusinessException("Ops! User card must not be null."));

        this.validateChangeableId(userToCreate.getId(), "created");
        if (repository.existsByAccountNumber(userToCreate.getAccount().getNumber())) {
            throw new BusinessException("Ops! This account number already exists.");
        }
        if (repository.existsByCardNumber(userToCreate.getCard().getNumber())) {
            throw new BusinessException("Ops! This card number already exists.");
        }
        return this.repository.save(userToCreate);
    }

    @Transactional
    public User update(Long id, User userToUpdate) {
        this.validateChangeableId(id, "updated");
        User dbUser = this.findById(id);
        if (!dbUser.getId().equals(userToUpdate.getId())) {
            throw new BusinessException("Update IDs must be the same.");
        }

        dbUser.setName(userToUpdate.getName());
        dbUser.setAccount(userToUpdate.getAccount());
        dbUser.setCard(userToUpdate.getCard());
        dbUser.setFeatures(userToUpdate.getFeatures());
        dbUser.setNews(userToUpdate.getNews());

        return this.repository.save(dbUser);
    }

    @Transactional
    public void delete(Long id) {
        this.validateChangeableId(id, "deleted");
        User dbUser = this.findById(id);
        this.repository.delete(dbUser);
    }

    private void validateChangeableId(Long id, String operation) {
        if (UNCHANGEABLE_USER_ID.equals(id)) {
            throw new BusinessException("User with ID %d can not be %s.".formatted(UNCHANGEABLE_USER_ID, operation));
        }
    }
}

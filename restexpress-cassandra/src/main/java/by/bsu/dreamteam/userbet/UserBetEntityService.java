package by.bsu.dreamteam.userbet;

import com.strategicgains.repoexpress.domain.Identifier;
import com.strategicgains.syntaxe.ValidationEngine;

import java.util.List;


public class UserBetEntityService {
    private UserBetEntityRepository repository;

    public UserBetEntityService(UserBetEntityRepository repository) {
        super();
        this.repository = repository;
    }

    public UserBetEntity create(UserBetEntity entity) {
        ValidationEngine.validateAndThrow(entity);
        return repository.create(entity);
    }

    public UserBetEntity read(Identifier id) {
        return repository.read(id);
    }

    public List<UserBetEntity> readAll(Long userId) {
        return repository.readAll(userId);
    }

    public long count(Long userId) {
        return repository.count(userId);
    }
}

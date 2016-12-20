package by.bsu.dreamteam.userbet;


import com.strategicgains.repoexpress.domain.Identifier;
import com.strategicgains.repoexpress.event.RepositoryObserver;

import java.util.Date;

public class UserBetEntityObserver implements RepositoryObserver<UserBetEntity> {

    private static long betIdNext = 1000;

    @Override
    public void beforeCreate(UserBetEntity object) {
        object.setBetId(betIdNext++);
        Date now = new Date(System.currentTimeMillis());
        object.setCreatedTs(now);
    }

    @Override
    public void afterCreate(UserBetEntity object) {

    }

    @Override
    public void afterDelete(UserBetEntity object) {

    }

    @Override
    public void afterRead(UserBetEntity object) {

    }

    @Override
    public void afterUpdate(UserBetEntity object) {

    }

    @Override
    public void beforeDelete(UserBetEntity object) {

    }

    @Override
    public void beforeRead(Identifier id) {

    }

    @Override
    public void beforeUpdate(UserBetEntity object) {

    }
}

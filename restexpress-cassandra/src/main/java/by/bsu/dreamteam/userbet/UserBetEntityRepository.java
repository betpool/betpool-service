package by.bsu.dreamteam.userbet;

import com.datastax.driver.core.*;
import com.strategicgains.repoexpress.cassandra.AbstractCassandraRepository;
import com.strategicgains.repoexpress.domain.Identifier;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UserBetEntityRepository
        extends AbstractCassandraRepository<UserBetEntity> {
    private static final String IDENTITY_CQL = " where userid = ? and betid = ?";
    private static final String READ_CQL = "select * from %s" + IDENTITY_CQL;
    private static final String CREATE_CQL = "insert into %s (userid, betid, eventid, outcomeid, value, confirmedodd, createdts) values (?, ?, ?, ?, ?, ?, ?)";
    private static final String READ_ALL_CQL = "select * from %s where userid = ?";
    private static final String READ_ALL_COUNT_CQL = "select count(*) from %s where userid = ?";

    private PreparedStatement readStmt;
    private PreparedStatement createStmt;
    private PreparedStatement readAllStmt;
    private PreparedStatement readAllCountStmt;

    public UserBetEntityRepository(Session session) {
        super(session, "userbets");
        addObserver(new UserBetEntityObserver());
        initialize();
    }

    protected void initialize() {
        readStmt = getSession().prepare(String.format(READ_CQL, getTable()));
        createStmt = getSession().prepare(String.format(CREATE_CQL, getTable()));
        readAllStmt = getSession().prepare(String.format(READ_ALL_CQL, getTable()));
        readAllCountStmt = getSession().prepare(String.format(READ_ALL_COUNT_CQL, getTable()));
    }

    @Override
    protected UserBetEntity readEntityById(Identifier identifier) {
        if (identifier == null || identifier.isEmpty()) return null;

        BoundStatement bs = new BoundStatement(readStmt);
        bindIdentifier(bs, identifier);
        return marshalRow(getSession().execute(bs).one());
    }

    @Override
    protected UserBetEntity createEntity(UserBetEntity entity) {
        BoundStatement bs = new BoundStatement(createStmt);
        bindCreate(bs, entity);
        getSession().execute(bs);
        return entity;
    }

    @Override
    protected UserBetEntity updateEntity(UserBetEntity entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void deleteEntity(UserBetEntity entity) {
        throw new UnsupportedOperationException();
    }

    public List<UserBetEntity> readAll(Long userId) {
        BoundStatement bs = new BoundStatement(readAllStmt);
        bs.bind(userId);
        return (marshalAll(getSession().execute(bs)));
    }

    public long count(Long userId) {
        BoundStatement bs = new BoundStatement(readAllCountStmt);
        bs.bind(userId);
        return (getSession().execute(bs).one().getLong(0));
    }

    protected List<UserBetEntity> marshalAll(ResultSet rs) {
        List<UserBetEntity> results = new ArrayList<UserBetEntity>();
        Iterator<Row> i = rs.iterator();

        while (i.hasNext()) {
            results.add(marshalRow(i.next()));
        }

        return results;
    }

    protected UserBetEntity marshalRow(Row row) {
        if (row == null) return null;

        UserBetEntity e = new UserBetEntity();
        e.setUserId(row.getLong("userid"));
        e.setBetId(row.getLong("betid"));
        e.setEventId(row.getLong("eventid"));
        e.setOutcomeId(row.getLong("outcomeid"));
        e.setValue(row.getFloat("value"));
        e.setConfirmedOdd(row.getString("confirmedodd"));
        e.setCreatedTs(row.getTimestamp("createdts"));
        return e;
    }

    private void bindCreate(BoundStatement bs, UserBetEntity entity) {
        bs.bind(entity.getUserId(),
                entity.getBetId(),
                entity.getEventId(),
                entity.getOutcomeId(),
                entity.getValue(),
                entity.getConfirmedOdd(),
                entity.getCreatedTs());
    }

}

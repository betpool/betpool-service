package by.bsu.dreamteam.userbet;

import by.bsu.dreamteam.Constants;
import com.strategicgains.hyperexpress.annotation.BindToken;
import com.strategicgains.repoexpress.domain.Identifiable;
import com.strategicgains.repoexpress.domain.Identifier;
import com.strategicgains.syntaxe.annotation.Required;
import org.restexpress.plugin.hyperexpress.Linkable;

import java.util.Date;


public class UserBetEntity implements Identifiable, Linkable {
    @Required
    @BindToken(value = Constants.Url.USER_ID)
    private Long userId;

    @BindToken(value = Constants.Url.BET_ID)
    private Long betId;

    private Long eventId;

    private Long outcomeId;

    private Float value;

    private String confirmedOdd;

    private Date createdTs;

    public UserBetEntity() {
    }

    @Override
    public Identifier getId() {
        return new Identifier(userId, betId);
    }

    @Override
    public void setId(Identifier id) {
        // nothing here
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getBetId() {
        return betId;
    }

    public void setBetId(Long betId) {
        this.betId = betId;
    }

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public Long getOutcomeId() {
        return outcomeId;
    }

    public void setOutcomeId(Long outcomeId) {
        this.outcomeId = outcomeId;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }

    public String getConfirmedOdd() {
        return confirmedOdd;
    }

    public void setConfirmedOdd(String confirmedOdd) {
        this.confirmedOdd = confirmedOdd;
    }

    public Date getCreatedTs() {
        return (createdTs == null ? null : new Date(createdTs.getTime()));
    }

    public void setCreatedTs(Date date) {
        this.createdTs = (date == null ? new Date() : new Date(date.getTime()));
    }
}

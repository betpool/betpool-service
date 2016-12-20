package by.bsu.dreamteam.userbet;

import by.bsu.dreamteam.Constants;
import com.strategicgains.hyperexpress.builder.DefaultTokenResolver;
import com.strategicgains.hyperexpress.builder.DefaultUrlBuilder;
import com.strategicgains.hyperexpress.builder.UrlBuilder;
import com.strategicgains.repoexpress.domain.Identifier;
import io.netty.handler.codec.http.HttpMethod;
import org.restexpress.Request;
import org.restexpress.Response;
import org.restexpress.common.query.QueryRange;
import org.restexpress.query.QueryRanges;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;


public class UserBetEntityController {
    private static final UrlBuilder LOCATION_BUILDER = new DefaultUrlBuilder();
    private UserBetEntityService service;

    public UserBetEntityController(UserBetEntityService service) {
        super();
        this.service = service;
    }

    public UserBetEntity create(Request request, Response response) {
        long userId = Long.parseLong(request.getHeader(Constants.Url.USER_ID, "userId not provided"));

        UserBetEntity toCreate = request.getBodyAs(UserBetEntity.class, "Resource details not provided");
        toCreate.setUserId(userId);

        long eventId = checkNotNull(toCreate.getEventId(), "eventId not provided");
        long outcomeId = checkNotNull(toCreate.getOutcomeId(), "outcomeId not provided");
        float value = checkNotNull(toCreate.getValue(), "value not provided");
        String confirmedOdd = checkNotNull(toCreate.getConfirmedOdd(), "confirmedOdd not provided");

        UserBetEntity saved = service.create(toCreate);

        // Construct the response for create...
        response.setResponseCreated();

        // Include the Location header...
        String locationPattern = request.getNamedUrl(HttpMethod.GET, Constants.Routes.SINGLE_USER_BET);
        response.addLocationHeader(LOCATION_BUILDER.build(locationPattern, new DefaultTokenResolver()));

        // Return the newly-created resource...
        return saved;
    }

    public UserBetEntity read(Request request, Response response) {
        Long userId = Long.parseLong(request.getHeader(Constants.Url.USER_ID, "userId not provided"));
        Long betId = Long.parseLong(request.getHeader(Constants.Url.BET_ID, "betId not provided"));
        UserBetEntity entity = service.read(new Identifier(userId, betId));
        return entity;
    }

    public List<UserBetEntity> readAll(Request request, Response response) {
        Long userId = Long.parseLong(request.getHeader(Constants.Url.USER_ID, "userId not provided"));
        QueryRange range = QueryRanges.parseFrom(request);
        List<UserBetEntity> entities = service.readAll(userId);
        long count = service.count(userId);
        response.setCollectionResponse(range, entities.size(), count);
        return entities;
    }
}

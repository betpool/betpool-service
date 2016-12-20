package by.bsu.dreamteam;

import java.util.Map;

import by.bsu.dreamteam.userbet.UserBetEntity;
import org.restexpress.RestExpress;
import org.restexpress.common.exception.ConfigurationException;

import com.strategicgains.hyperexpress.HyperExpress;
import com.strategicgains.hyperexpress.RelTypes;

public abstract class Relationships
{
	private static Map<String, String> ROUTES;

	public static void define(RestExpress server)
	{
		ROUTES = server.getRouteUrlsByName();

		HyperExpress.relationships()
		.forCollectionOf(UserBetEntity.class)
			.rel(RelTypes.SELF, href(Constants.Routes.USER_BET_COLLECTION))
			.withQuery("limit={limit}")
			.withQuery("offset={offset}")
			.rel(RelTypes.NEXT, href(Constants.Routes.USER_BET_COLLECTION) + "?offset={nextOffset}")
			.withQuery("limit={limit}")
			.optional()
			.rel(RelTypes.PREV, href(Constants.Routes.USER_BET_COLLECTION) + "?offset={prevOffset}")
			.withQuery("limit={limit}")
			.optional()

		.forClass(UserBetEntity.class)
			.rel(RelTypes.SELF, href(Constants.Routes.SINGLE_USER_BET))
			.rel(RelTypes.UP, href(Constants.Routes.USER_BET_COLLECTION));
	}

	private static String href(String name)
	{
		String href = ROUTES.get(name);
		if (href == null) throw new ConfigurationException("Route name not found: " + name);
		return href;
	}

}

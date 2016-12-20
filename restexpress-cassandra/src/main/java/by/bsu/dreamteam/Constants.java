package by.bsu.dreamteam;

public class Constants
{
	/**
	 * These define the URL parmaeters used in the route definition strings (e.g. '{userId}').
	 */
	public class Url
	{
		public static final String USER_ID = "userId";
		public static final String BET_ID = "betId";
	}

	/**
	 * These define the route names used in naming each route definitions.  These names are used
	 * to retrieve URL patterns within the controllers by name to create links in responses.
	 */
	public class Routes
	{
		public static final String SINGLE_USER_BET = "user.bet.single.route";
		public static final String USER_BET_COLLECTION = "user.bet.collection.route";
	}
}

package by.bsu.dreamteam;

import io.netty.handler.codec.http.HttpMethod;

import org.restexpress.RestExpress;

public abstract class Routes
{
	public static void define(Configuration config, RestExpress server)
	{
		server.uri("/bets/{betId}.{format}", config.getUserBetEntityController())
				.method(HttpMethod.GET)
				.name(Constants.Routes.SINGLE_USER_BET);

		server.uri("/bets.{format}", config.getUserBetEntityController())
				.action("readAll", HttpMethod.GET)
				.method(HttpMethod.POST)
				.name(Constants.Routes.USER_BET_COLLECTION);
	}
}

package by.bsu.dreamteam;

import java.util.Properties;

import by.bsu.dreamteam.userbet.UserBetEntityController;
import by.bsu.dreamteam.userbet.UserBetEntityRepository;
import by.bsu.dreamteam.userbet.UserBetEntityService;
import org.restexpress.RestExpress;
import org.restexpress.util.Environment;

import com.strategicgains.repoexpress.cassandra.CassandraConfig;
import com.strategicgains.restexpress.plugin.metrics.MetricsConfig;

public class Configuration
extends Environment
{
	private static final String DEFAULT_EXECUTOR_THREAD_POOL_SIZE = "20";

	private static final String PORT_PROPERTY = "port";
	private static final String BASE_URL_PROPERTY = "base.url";
	private static final String EXECUTOR_THREAD_POOL_SIZE = "executor.threadPool.size";

	private int port;
	private String baseUrl;
	private int executorThreadPoolSize;
	private MetricsConfig metricsSettings;

	private UserBetEntityController userBetEntityController;

	@Override
	protected void fillValues(Properties p)
	{
		this.port = Integer.parseInt(p.getProperty(PORT_PROPERTY, String.valueOf(RestExpress.DEFAULT_PORT)));
		this.baseUrl = p.getProperty(BASE_URL_PROPERTY, "http://localhost:" + String.valueOf(port));
		this.executorThreadPoolSize = Integer.parseInt(p.getProperty(EXECUTOR_THREAD_POOL_SIZE, DEFAULT_EXECUTOR_THREAD_POOL_SIZE));
		this.metricsSettings = new MetricsConfig(p);
		CassandraConfig dbConfig = new CassandraConfig(p);
		initialize(dbConfig);
	}

	private void initialize(CassandraConfig dbConfig)
	{
		UserBetEntityRepository userBetEntityRepository= new UserBetEntityRepository(dbConfig.getSession());
		UserBetEntityService userBetEntityService = new UserBetEntityService(userBetEntityRepository);
		userBetEntityController = new UserBetEntityController(userBetEntityService);
	}

	public int getPort()
	{
		return port;
	}
	
	public String getBaseUrl()
	{
		return baseUrl;
	}
	
	public int getExecutorThreadPoolSize()
	{
		return executorThreadPoolSize;
	}

	public MetricsConfig getMetricsConfig()
    {
	    return metricsSettings;
    }

	public UserBetEntityController getUserBetEntityController() {
		return userBetEntityController;
	}
}

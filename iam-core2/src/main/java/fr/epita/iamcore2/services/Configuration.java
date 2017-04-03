package fr.epita.iamcore2.services;

import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.epita.iam.iamcore2.tests.TestProperties;

public class Configuration {

private static Configuration configuration;
	
	private String jdbcConxString;
	private String user;
	private String pwd;
	
	private Properties props;

	private static final Logger LOGGER = LogManager.getLogger(TestProperties.class);
	
	private Configuration() throws IOException{
		
		props = new Properties();
		props.load(this.getClass().getResourceAsStream("/test.properties"));
		
		jdbcConxString = props.getProperty("jdbc.connection.string");
		user = props.getProperty("jdbc.connection.user");
		pwd = props.getProperty("jdbc.connection.pwd");
		
		LOGGER.info(jdbcConxString);
		LOGGER.info("using user: {}", user);
	}
	
	public static Configuration getInstance() throws IOException{
		
		if (configuration == null){
			configuration = new Configuration();
		}
		return configuration;
		
	}

	public String getJdbcConxString() {
		return jdbcConxString;
	}

	public String getUser() {
		return user;
	}

	public String getPwd() {
		return pwd;
	}

	public Properties getProps() {
		return props;
	}
}

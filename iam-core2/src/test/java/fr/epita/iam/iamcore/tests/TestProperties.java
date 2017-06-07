package fr.epita.iam.iamcore.tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import fr.epita.iam.iamcore.tests.TestProperties;

public class TestProperties {

	private static final Logger LOGGER = LogManager.getLogger(TestProperties.class);
	
	@Test
	public void testReading() throws FileNotFoundException, IOException{
		Properties props = new Properties();
		
		String filePath = System.getProperty("fr.epita.iam.confFilePath");
		
		//props.load(new FileInputStream(new File(filePath)));
		//alternate method to read from the classpath resource
		props.load(this.getClass().getResourceAsStream("/test.properties"));
		LOGGER.info(props.getProperty("jdbc.connection.string"));
		
		String cnx = props.getProperty("jdbc.connection.string");
		String user = props.getProperty("jdbc.connection.user");
		String pwd = props.getProperty("jdbc.connection.pwd");
		
		System.out.println(cnx);
		System.out.println(user);
		System.out.println(pwd);
		
		
	}
}

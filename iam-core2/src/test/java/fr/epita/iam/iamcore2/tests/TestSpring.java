package fr.epita.iam.iamcore2.tests;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.inject.Inject;
import javax.inject.Named;
//import javax.sql.DataSource;
import javax.sql.DataSource;

//import org.apache.derby.iapi.services.context.Context;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
/*import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ApplicationContextEvent;
import org.springframework.context.support.ClassPathXmlApplicationContext;*/
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.epita.iamcore2.models.Identity;
import fr.epita.iamcore2.services.JdbcDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/applicationContext.xml"})

public class TestSpring {

	private static boolean initialized = false;
	
	@Inject
	JdbcDAO dao;	
	
	@Inject
	@Named("dataSourceBean")
	DataSource ds;
	
	private static final Logger LOGGER = LogManager.getLogger(TestSpring.class);
	
	public static void globalSetup(DataSource source) throws SQLException{
		LOGGER.info("beginning the setup");
		Connection connection = source.getConnection();
		PreparedStatement pstmt = connection.prepareStatement("CREATE TABLE IDENTITIES " +
				"(IDENTITY_ID INT NOT NULL GENERATED ALWAYS AS IDENTITY CONSTRAINT IDENTITY_PK PRIMARY KEY, " + 
				"IDENTITY_DISPLAYNAME VARCHAR(255), " +
				"IDENTITY_EMAIL VARCHAR(255), " +
				"IDENTITY_BIRTHDATE DATE, " +
				"USER_TYPE VARCHAR(8), " +
				"PASSWORD VARCHAR(24)) " );
		
		pstmt.execute();
		connection.commit();
		pstmt.close();
		connection.close();
		LOGGER.info("setup finished : ready to proceed with the testcase");
		
	}
	
	/**
	 * @return
	 * @throws SQLException
	 */

	@Test
	public void testSpringContext() throws SQLException{
		if(!initialized){
			globalSetup(ds);
			initialized = true;
		}
		dao.write(new Identity(null, "Jorge", "jorge@gmail.com", "1989-12-18", "123", "admin"));
	}
}

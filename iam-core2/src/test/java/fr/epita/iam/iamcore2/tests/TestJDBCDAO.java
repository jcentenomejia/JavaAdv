package fr.epita.iam.iamcore2.tests;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import fr.epita.iamcore2.models.Identity;
import fr.epita.iamcore2.services.Configuration;
import fr.epita.iamcore2.services.JdbcDAO;

public class TestJDBCDAO {
	
	private static final Logger LOGGER = LogManager.getLogger(TestJDBCDAO.class);
	
	@BeforeClass
	public static void globalSetup() throws SQLException, IOException{
		LOGGER.info("beginning the setup");
		
		Configuration config = Configuration.getInstance();
		String connectionString = config.getJdbcConxString();
		String user = config.getUser();
		String pwd = config.getPwd();
		Connection connection = DriverManager.getConnection(connectionString, user, pwd);
		
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
	
	@Before
	public void setUp(){
		LOGGER.info("before test setup");
	}
	
	
	@Test
	public void basicTest() throws SQLException{
		JdbcDAO dao = new JdbcDAO();
		dao.write(new Identity(null, "Jorge", "jorge@gmail.com", "1989-12-18", "123", "admin"));
		System.out.println(dao.readAllIdentities());
		
	}
	
	
	@After
	public void tearDown(){
		LOGGER.info("after test cleanup");
	}
	
	
	@AfterClass()
	public static void globalTearDown(){
		LOGGER.info("global cleanup");
	}

}

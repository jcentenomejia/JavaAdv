package fr.epita.iam.iamcore2.tests;

import javax.inject.Inject;
import javax.inject.Named;
import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.epita.iamcore2.services.JdbcDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/applicationContext.xml"})
public class TestThings {

	@Inject
	JdbcDAO dao;
	
	@Inject
	@Named("dataSourceBean")
	DataSource ds;
	
	@Inject
	SessionFactory sFactory;
	
	private static final Logger LOGGER = LogManager.getLogger(TestSpring.class);
	
	@Test
	public void TestAll(){
		
	}
}

package fr.epita.iam.iamcore2.tests;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.epita.iamcore2.models.Identity;
import fr.epita.iamcore2.services.JdbcDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/applicationContext.xml"})
public class TestHibernate {

	@Inject
	SessionFactory sFactory;
	
	@Inject
	JdbcDAO dao;	
	
	@Inject
	@Named("dataSourceBean")
	DataSource ds;
	
	private static final Logger LOGGER = LogManager.getLogger(TestSpring.class);
	
	@Test
	public void testHibernate() throws SQLException{
		
		List<Identity> identitiesListFromJdbc = dao.readAllIdentities();

		LOGGER.info("before : {} ", identitiesListFromJdbc);
		int originalSize = identitiesListFromJdbc.size();
		
		Session session = sFactory.openSession();
		Identity identity = new Identity("1234", "jcenteno", "jcenteno@gmail.com","1989-12-18","123","admin");
		
		session.saveOrUpdate(identity);
		
		identitiesListFromJdbc = dao.readAllIdentities();
		LOGGER.info("after : {}", identitiesListFromJdbc);
		
		Assert.assertEquals(dao.readAllIdentities().size(), originalSize + 1);
		
	}
	
}
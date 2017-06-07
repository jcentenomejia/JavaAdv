package fr.epita.iam.iamcore.tests;

import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.epita.iamcore.models.Identity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/applicationContext.xml"})
public class TestHibernate {

	@Inject
	SessionFactory sFactory;
	
	private static final Logger LOGGER = LogManager.getLogger(TestHibernate.class);
	
	@Test
	public void testHQL() throws SQLException{
		
		String hqlQuery = "from Identity as identity where identity.displayname = :displayname";
		Session session = sFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		String user = "jcenteno";
		session.save(new Identity("123",user, "jcenteno@gmail.com","1989-12-18","123","admin"));
		tx.commit();
		
		Query query = session.createQuery(hqlQuery);
		query.setParameter("displayname", user);
		List<Identity> results = query.list();
		
		Assert.assertTrue(!results.isEmpty());
	}
	
}
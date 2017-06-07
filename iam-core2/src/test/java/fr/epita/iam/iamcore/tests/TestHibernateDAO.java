package fr.epita.iam.iamcore.tests;

import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.epita.iamcore.models.Identity;
import fr.epita.iamcore.services.Dao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/applicationContext.xml"})
public class TestHibernateDAO {
	
	@Inject
	Dao<Identity> dao;
	
	@Test
	public void testEndToEndCrud() throws SQLException{
		Identity identity = new Identity("1234", "jcenteno", "jcenteno@gmail.com","1989-12-18","123","admin");
		dao.write(identity);
		
		Assert.assertTrue(identity.getId()!=0);
		identity.setDisplayname("jorge");
		dao.update(identity);
		
		Identity criteria = new Identity(null, "jorge", null,null,null,null);
		
		
		List<Identity> results = dao.search(criteria.getEmail());
		Assert.assertTrue(results != null && !results.isEmpty());
		
		dao.delete(identity);
		
		results = dao.search(criteria.getDisplayname());
		Assert.assertTrue(results.isEmpty());
	}

}

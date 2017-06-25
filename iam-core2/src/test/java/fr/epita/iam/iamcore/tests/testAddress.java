package fr.epita.iam.iamcore.tests;
import java.sql.SQLException;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.epita.iamcore.models.Address;
import fr.epita.iamcore.models.Identity;
import fr.epita.iamcore.services.Dao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/applicationContext.xml"})
public class testAddress {
	
	@Inject
	Dao<Address> dao;
	
	
	@Test
	public void testDao() throws SQLException{
		//Creating one Identity with 2 addresses
		Identity identity = new Identity();
		identity.setDisplayname("lero");
		identity.setUserType("user");
		identity.setBirthDate("1989-12-18");
		identity.setEmail("jcenteno@gmail.com");
		identity.setPassword("123");

		Address address = new Address();
		address.setAddressDesc("74 cours de vincennes");
		address.setCity("Paris");
		address.setZipcode("75012");
		address.setCountry("France");
		address.setIdentity(identity);
		
		Address address2 = new Address();
		address2.setAddressDesc("14 Rue voltaire");
		address2.setCity("Kremlin Bicetre");
		address2.setZipcode("74000");
		address2.setCountry("France");
		address2.setIdentity(identity);
		
		//Writing the new addresses will also insert the identity
		dao.write(address);
		Assert.assertTrue(address!=null);
		dao.search(address.getAddressDesc());
		
		dao.write(address2);
		Assert.assertTrue(address2!=null);
		dao.search(address2.getAddressDesc());
		
	}
}

package fr.epita.iamcore.services;

import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import fr.epita.iamcore.models.Address;

public class AddressDAO implements Dao<Address>{

	private static final Logger LOGGER = LogManager.getLogger(AddressDAO.class);
	
	//Injecting session variable containing DB connection
	@Inject
	SessionFactory sFactory;
	
	private AddressDAO(){
		//Default constructor
	}
	
	//Insert Address into DB
	public void write(Address address) throws SQLException{
		LOGGER.info("adding address : {} ", address);
		Session session = sFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.saveOrUpdate(address);
		transaction.commit();
		session.close();
	}
	
	//Update Address in DB
	public void update(Address address) throws SQLException {
		LOGGER.info("updating address : {} ", address);
		Session session = sFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.update(address);
		transaction.commit();
		session.close();
	}
	
	//Delete Address from DB
	public void delete(Address address) throws SQLException {
		LOGGER.info("deleting address : {} ", address);
		Session session = sFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.delete(address);
		transaction.commit();
		session.close();
	}
	
	//Searching Address by criteria
	public List<Address> search(String searchString) throws SQLException {
		LOGGER.info("retrieving address with search criteria : {} ", searchString);
		Session session = sFactory.openSession();
		String queryString = "from Address as address where address.addressDesc like :addressDesc";
		Query query = session.createQuery(queryString);
		query.setParameter("addressDesc", "%" + searchString +"%");
		List<Address> addressList = query.list();
		session.close();
		return addressList;
		
	}
	
	//Getting Address by id
	public Address getById(Long id) throws SQLException {
		LOGGER.info("retrieving address with id : {} ", id);
		Session session = sFactory.openSession();
		String queryString = "from Address as address where address.id = :id";
		Query query = session.createQuery(queryString);
		query.setParameter("id", id);
		List<Address> addressList = query.list();
		session.close();
		return addressList.get(0);
	}
}

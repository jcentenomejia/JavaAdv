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

import fr.epita.iamcore.models.Identity;

public class IdentityDAO implements Dao<Identity>{
	
	private static final Logger LOGGER = LogManager.getLogger(IdentityDAO.class);

	@Inject
	SessionFactory sFactory;
	
	private IdentityDAO() throws SQLException {
	}
	
	public void write(Identity identity) throws SQLException{
		LOGGER.info("adding identity : {} ", identity);
		Session session = sFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.saveOrUpdate(identity);
		transaction.commit();
		session.close();
	}
	
	public void update(Identity identity) throws SQLException {
		LOGGER.info("updating identity : {} ", identity);
		Session session = sFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.update(identity);
		transaction.commit();
		session.close();
	}
	
	public void delete(Identity identity) throws SQLException {
		LOGGER.info("deleting identity : {} ", identity);
		Session session = sFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.delete(identity);
		transaction.commit();
		session.close();
	}
	
	public List<Identity> search(String searchString) throws SQLException {
		LOGGER.info("Searching identity containing: {}", searchString);
		Session session = sFactory.openSession();
		String queryString = "from Identity as identity where identity.displayname like :criteria or "
				+ "identity.email like :criteria or identity.birthDate like :criteria";
		Query query = session.createQuery(queryString);
		query.setParameter("criteria", "%" + searchString+"%");
		List<Identity> identityList = query.list();
		session.close();
		return identityList;
		
	}
	
	public Identity getById(Long id) throws SQLException {
		LOGGER.info("retrieving identity with id : {} ", id);
		Session session = sFactory.openSession();
		String queryString = "from Identity as identity where identity.id = :id";
		Query query = session.createQuery(queryString);
		query.setParameter("id", id);
		List<Identity> identities = query.list();
		session.close();
		return identities.get(0);
	}
}
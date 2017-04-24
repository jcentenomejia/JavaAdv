package fr.epita.iamcore2.services;

import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import fr.epita.iamcore2.models.Identity;

public class HibernateDAO implements Dao<Identity>{
	
	private static final Logger LOGGER = LogManager.getLogger(HibernateDAO.class);

	@Inject
	SessionFactory sFactory;
	
	private HibernateDAO() throws SQLException {
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
	
	public List<Identity> search(Identity identity) throws SQLException {
		LOGGER.info("retrieving identity : {} ", identity);
		Session session = sFactory.openSession();
		//String queryString = "from Identity as identity";
		String queryString = "from Identity as identity where identity.displayname like :displayname";
		Query query = session.createQuery(queryString);
		query.setParameter("displayname", "%" + identity.getDisplayname()+"%");
		List<Identity> identityList = query.list();
		session.close();
		return identityList;
		
	}
}
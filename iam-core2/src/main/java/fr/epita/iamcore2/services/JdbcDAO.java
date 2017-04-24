package fr.epita.iamcore2.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.epita.iamcore2.services.JdbcDAO;
import fr.epita.iamcore2.models.Identity;

public class JdbcDAO {
	
	private static final Logger LOGGER = LogManager.getLogger(JdbcDAO.class);
	
	@Inject
	@Named("dataSourceBean")
	private DataSource ds;
	
	private JdbcDAO() throws SQLException{
	}
	
	//Returns a list of Identities in Database
	public List<Identity> readAllIdentities() throws SQLException {
		LOGGER.debug("=> readAll");
		List<Identity> identities = new ArrayList<Identity>();

		Connection connection = ds.getConnection();

		PreparedStatement statement = connection.prepareStatement("select * from IDENTITIES");
		ResultSet rs = statement.executeQuery();
		
		while (rs.next()) {
			String uid = String.valueOf(rs.getString("IDENTITY_ID"));
			String displayName = rs.getString("IDENTITY_DISPLAYNAME");
			String email = rs.getString("IDENTITY_EMAIL");
			String birthDate = rs.getString("IDENTITY_BIRTHDATE");
			String password = rs.getString("PASSWORD");
			String usertype = rs.getString("USER_TYPE");
			
			Identity identity = new Identity(String.valueOf(uid), displayName, email, birthDate,password, usertype);
			identities.add(identity);
		}
		connection.close();
		statement.close();
		return LOGGER.traceExit("<= readAll : {}", identities);
	}
	
	//Inserts new Identity into Database
	public void write(Identity identity) throws SQLException {
		LOGGER.debug("=> writeIdentity : tracing the input : {}", identity);
		Connection connection = ds.getConnection();

		String sqlInstruction = "INSERT INTO IDENTITIES(IDENTITY_DISPLAYNAME, IDENTITY_EMAIL, "
				+ "IDENTITY_BIRTHDATE, PASSWORD, USER_TYPE) VALUES(?,?,?,?,?)";
		PreparedStatement statement = connection.prepareStatement(sqlInstruction);
		statement.setString(1, identity.getDisplayname());
		statement.setString(2, identity.getEmail());
		statement.setString(3, identity.getBirthDate());
		statement.setString(4, identity.getPassword());
		statement.setString(5, identity.getUserType());
		statement.execute();
		
		connection.close();
		statement.close();
		LOGGER.debug("<= writeIdentity: leaving the method with no error" );
	}

	//Updates the Identity using the identity received 
	public void update(Identity identity) throws SQLException {
			
		Connection connection = ds.getConnection();

		String query = "UPDATE IDENTITIES SET IDENTITY_DISPLAYNAME = ?, IDENTITY_EMAIL = ?, "
				+ "IDENTITY_BIRTHDATE = ?, PASSWORD = ?, USER_TYPE = ? WHERE IDENTITY_ID = ?";
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setString(1, identity.getDisplayname());
		statement.setString(2, identity.getEmail());
		statement.setString(3, identity.getBirthDate());
		statement.setString(4, identity.getPassword());
		statement.setString(5, identity.getUserType());
		statement.setString(6, identity.getUid());
		statement.execute();
		
		connection.close();
		statement.close();
	}
	
	//Returns an Identity according to its uid
	public Identity getIdentity(String uid) throws SQLException {
			
		Connection connection = ds.getConnection();
			
		String query = "select * from IDENTITIES where IDENTITY_ID = ?";
			
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setString(1, uid);
			
		ResultSet rs = statement.executeQuery();
		String user = "";
		String email = "";
		String birthdate = "";
		String password = "";
		String usertype = "";
		
		while(rs.next()){
			user = rs.getString("IDENTITY_DISPLAYNAME");
			email = rs.getString("IDENTITY_EMAIL");
			birthdate = rs.getString("IDENTITY_BIRTHDATE");
			password = rs.getString("PASSWORD");
			usertype = rs.getString("USER_TYPE");
		}
		Identity identity = new Identity(uid,user,email,birthdate,password, usertype);
		
		statement.close();
		connection.close();
		return identity;
	}
	
	//This method looks for the user in the db and his password, then compares the passwords and returns
	//a message or an empty String meaning that there was no problem
	public String authenticate(String user, String pass) throws SQLException {
		
		String errors = "";
		
		if("".equals(user) || "".equals(pass)){
			errors = "Username and Password cannot be empty!";
			
		}else{
			Connection connection = ds.getConnection();
	
			String query = "SELECT * FROM IDENTITIES WHERE "
					+ " IDENTITY_DISPLAYNAME = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, user);
			
			ResultSet rs = statement.executeQuery();
			
			String dbPass = "";
			while(rs.next()){
				dbPass = rs.getString("PASSWORD");
			}
			if (!dbPass.equals(pass)){
				errors = "Incorrect username or password. Please try again.";
			}
			statement.close();
			connection.close();
		}
		
		return errors;
	}
		
	//Deletes Identity according to the uid received
	public void delete(String uid) throws SQLException {
		Connection connection = ds.getConnection();

		String sqlInstruction = "DELETE FROM IDENTITIES WHERE IDENTITY_ID = ?";
		PreparedStatement statement = connection.prepareStatement(sqlInstruction);
		statement.setString(1, uid);
		statement.execute();
		
		statement.close();
		connection.close();
	}
	
	public Identity getIdentityFromUsername(String user) throws SQLException{
		Connection connection = ds.getConnection();
		
		String query = "select * from IDENTITIES where IDENTITY_DISPLAYNAME = ?";
			
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setString(1, user);
			
		ResultSet rs = statement.executeQuery();
		String uid = "";
		String email = "";
		String birthdate = "";
		String password = "";
		String usertype = "";
		
		while(rs.next()){
			uid = rs.getString("IDENTITY_ID");
			email = rs.getString("IDENTITY_EMAIL");
			birthdate = rs.getString("IDENTITY_BIRTHDATE");
			password = rs.getString("PASSWORD");
			usertype = rs.getString("USER_TYPE");
		}
		Identity identity = new Identity(uid,user,email,birthdate,password, usertype);
		
		statement.close();
		connection.close();
		return identity;
	}
}

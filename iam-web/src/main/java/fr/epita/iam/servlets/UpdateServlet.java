package fr.epita.iam.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import fr.epita.iamcore.models.Identity;
import fr.epita.iamcore.services.Dao;

@WebServlet(name="UpdateServlet", urlPatterns={"/update"})
public class UpdateServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    
	private static final Logger LOGGER = LogManager.getLogger(UpdateServlet.class);
	
	@Autowired
	Dao<Identity> dao;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		
		long uid = Long.parseLong(request.getParameter("userID"));
		String username = request.getParameter("userName");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String birthdate = request.getParameter("birthdate");
		String type = request.getParameter("userType");
		
		Identity identity = new Identity();
		identity.setId(uid);
		identity.setBirthDate(birthdate);
		identity.setDisplayname(username);
		identity.setEmail(email);
		identity.setPassword(password);
		identity.setUserType(type);
		
		try {
			dao.update(identity);
			request.setAttribute("message", "Identity updated sucessfully.");
			request.setAttribute("message_color", "green");
		    request.getRequestDispatcher("welcome.jsp").forward(request, response);
		} catch (SQLException e) {
			LOGGER.error("An error occured during the update: {}", e);
		}
		
	}
}

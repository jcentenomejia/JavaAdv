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


@WebServlet("/CreationServlet")
public class CreationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private static final Logger LOGGER = LogManager.getLogger(CreationServlet.class);
	
	@Autowired
	Dao<Identity> dao;
	
    public CreationServlet() {
        super();
    }	
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String permission = request.getSession().getAttribute("userType").toString();
    	
    	if(!"admin".equals(permission)){
			request.setAttribute("message", "You have no permission to create.");
			request.setAttribute("message_color", "red");
			request.getRequestDispatcher("welcome.jsp").forward(request, response);
    	}else{
    		response.sendRedirect("newIdentity.jsp");
    	}
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		
		String username = request.getParameter("userName");
		String pass = request.getParameter("password");
		String date = request.getParameter("date");
		String email = request.getParameter("email");
		String type = request.getParameter("userType");
			
		Identity identity = new Identity();
		identity.setBirthDate(date);
		identity.setDisplayname(username);
		identity.setEmail(email);
		identity.setPassword(pass);
		identity.setUserType(type);
			
		try {
			dao.write(identity);
			request.setAttribute("message", "New identity created successfully.");
			request.setAttribute("message_color", "green");
			request.getRequestDispatcher("welcome.jsp").forward(request, response);
		} catch (SQLException e) {
			LOGGER.info("Identity creation failed: {} , {}", identity , e);
		}
			
		LOGGER.info("New identity: {} , {}", username, pass);
	
	}
}

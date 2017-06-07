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

import fr.epita.iam.service.Authenticate;
import fr.epita.iamcore.models.Identity;
import fr.epita.iamcore.services.Dao;

@WebServlet(name="AuthenticationServlet", urlPatterns={"/authenticate"})
public class AuthenticationServlet extends HttpServlet{

	@Autowired
	Dao<Identity> daoHibernate;
	
	private static final long serialVersionUID = 1L;
	
	private static final Logger LOGGER = LogManager.getLogger(AuthenticationServlet.class);
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		
		LOGGER.info("dao instance is : {}", daoHibernate);
		String login = req.getParameter("login");
		String password = req.getParameter("pwd");
		
		login = login.trim();
		password = password.trim();
		
		LOGGER.info("tried to authenticate with this login {}", login);
		
		Identity identity = null;
		
		try {
			identity = Authenticate.getInstance().authenticate(login, password);
		} catch (SQLException e) {
			LOGGER.error("Error creating user! {}",e);
		}
		
		if(identity != null){
			req.getSession().setAttribute("userName",identity.getDisplayname());
			req.getSession().setAttribute("userID",identity.getId());
			req.getSession().setAttribute("userType",identity.getUserType());
			resp.sendRedirect("welcome.jsp");
		}else{
		    req.setAttribute("message", "Wrong username or password.");
		    req.setAttribute("message_color", "red");
			req.getRequestDispatcher("index.jsp").forward(req, resp);
		}
	}
}

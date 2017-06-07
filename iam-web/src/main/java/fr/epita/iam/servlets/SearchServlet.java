package fr.epita.iam.servlets;

import java.util.List;
import java.io.IOException;
import java.sql.SQLException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import fr.epita.iamcore.models.Identity;
import fr.epita.iamcore.services.Dao;

@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private static final Logger LOGGER = LogManager.getLogger(SearchServlet.class);
	
	@Inject
	Dao<Identity> dao;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		
		String searchString = request.getParameter("searchCriteria");
		
		try {
			List<Identity> identities = dao.search(searchString);
			LOGGER.info("Search: {}", identities);
			request.setAttribute("identities", identities);
			request.setAttribute("visibility", "block");
		    request.getRequestDispatcher("searchIdentity.jsp").forward(request, response);
		} catch (SQLException e) {
			LOGGER.error("An error occured during the search: {}", e);
		}
		
	}

}

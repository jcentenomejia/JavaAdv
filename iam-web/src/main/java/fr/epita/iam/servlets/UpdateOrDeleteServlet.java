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

@WebServlet(name="UpdateOrDeleteServlet", urlPatterns={"/actionPerformed"})
public class UpdateOrDeleteServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	@Autowired
	Dao<Identity> dao;
	
	private static final Logger LOGGER = LogManager.getLogger(UpdateOrDeleteServlet.class);
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		
		String permission = req.getSession().getAttribute("userType").toString();
		
		if(!"admin".equals(permission)){
			req.setAttribute("message", "You have no permissions to update or delete.");
			req.setAttribute("message_color", "red");
			req.getRequestDispatcher("searchIdentity.jsp").forward(req, resp);
		}else{
			String actionReceived = req.getParameter("action");
			actionToDo(req, resp, actionReceived);
		}
	}

	private void actionToDo(HttpServletRequest req, HttpServletResponse resp, String actionReceived)
			throws IOException, ServletException {
		if("cancel".equals(actionReceived)){
			resp.sendRedirect("searchIdentity.jsp");
		}else{
			String id = req.getParameter("selection");
					
			Identity identity = null;
					
			try {
						
				identity = dao.getById(Long.parseLong(id));
			} catch (SQLException e) {
				LOGGER.error("Error retrieving identity with id: {} , {}", id,e);
			}
					
			switch(actionReceived){
			case "update":
				req.setAttribute("identity", identity);
				req.getRequestDispatcher("modifyIdentity.jsp").forward(req, resp);
				break;
						
			case "delete":
				try {
					dao.delete(identity);
					req.setAttribute("message", "Identity deleted successfully.");
					req.setAttribute("message_color", "green");
					req.getRequestDispatcher("welcome.jsp").forward(req, resp);
						
				} catch (SQLException e) {
					LOGGER.error("SQL error deleting identity: {}, {}", identity, e);
				}
				break;
			default:
				break;
			}
		}
	}
}

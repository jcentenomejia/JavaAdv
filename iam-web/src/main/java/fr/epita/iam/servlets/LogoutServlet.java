package fr.epita.iam.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
	//Logout servlet
	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = LogManager.getLogger(AuthenticationServlet.class);
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOGGER.info("User {} is logging out.", request.getSession().getAttribute("userName"));
		//Before logging out, destroy session variable
		request.getSession().invalidate();
		//Redirect to authentication page printing loggout message
		request.setAttribute("message", "Successfully logout.");
		request.setAttribute("message_color", "green");
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

}

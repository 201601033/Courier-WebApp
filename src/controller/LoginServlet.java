package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.*;
import utility.CookieHelper;
/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login.action")
public class LoginServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void init(ServletConfig config)throws ServletException{
		//required method to call if there is the ServletConfig
		super.init(config);		
		UserBean.initializeConn();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		boolean rememberedUser = (request.getParameterValues("remember_me") != null);
		Cookie loginCookie = null;
		Cookie emailCookie = null;
		if(request.getCookies() != null) {
			loginCookie = CookieHelper.getCookie(request.getCookies(), "userlogged");
			emailCookie = CookieHelper.getCookie(request.getCookies(), "emaillogged");
		}
		else {
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			
			rd.forward(request, response);
		}
		if(loginCookie != null)
		{
			
			response.addCookie(loginCookie);
			try {
			UserBean loggedUser = UserBean.retrieveUser(loginCookie.getValue());
			request.setAttribute("userlogged", loggedUser);
			}catch(SQLException sqle)
			{
				response.sendRedirect("index.jsp");
			}
		//	response.addCookie(emailCookie);
			RequestDispatcher rd = request.getRequestDispatcher("landingpage.jsp");
			
			rd.forward(request, response);
		}
		if(loginCookie == null) {
		
			try {
				boolean isValidated = UserBean.validation(email, password);
			if(isValidated)
			{
				UserBean loggedUser = UserBean.retrieveUser(email);
				System.out.println(loggedUser.getEmail());
				loginCookie = new Cookie("userlogged", loggedUser.getEmail());
					if(rememberedUser)
					{
					loginCookie.setMaxAge(60 * 60 * 24 *365 * 5);
				//	emailCookie.setMaxAge(60*60*24*365*5);
					}
					else
					{
						loginCookie.setMaxAge(-1);
					//	emailCookie.setMaxAge(-1);
					}
					response.addCookie(loginCookie);
			//		response.addCookie(emailCookie);
			//		request.setAttribute("userlogged", loggedUser);
				//	RequestDispatcher requestDispatcher = request.getRequestDispatcher("landingpage.jsp");
					
			//		requestDispatcher.forward(request, response);
				response.sendRedirect("landingpage.jsp");
				
					System.out.println(loginCookie.getValue());
				//	System.out.println(emailCookie.getValue());
					System.out.println(rememberedUser);
			}
			else {
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
				PrintWriter out = response.getWriter();
				out.println("<h2 font = 'red'>Email/Password incorrect!</h2>");
				requestDispatcher.forward(request, response);
			}
			}catch(SQLException sqle)
			{
				getServletContext().log(sqle.getMessage());
			}
		}
		
	}
}



package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
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
		for(Cookie c : request.getCookies())
		{
			if (c.getName().equals("userlogged"))
			{
				loginCookie = c;
				break;
			}
		}
		
		if(loginCookie != null)
		{
			
			response.addCookie(loginCookie);
			RequestDispatcher rd = request.getRequestDispatcher("landingpage.jsp");
			
			rd.forward(request, response);
		}
		else {
		
			try {
				UserBean loggedUser = new UserBean();
			
				PreparedStatement stmt = UserBean.getConnection().prepareStatement("Select * from users where `email` = ? AND `password` = ?");
				stmt.setString(1, email);
				stmt.setString(2, password);
				ResultSet rs = stmt.executeQuery();
			if(rs.next())
			{
				loggedUser.validation(email, password);
					loginCookie = new Cookie("userlogged", loggedUser.getFirstName());
					if(rememberedUser)
					{
					loginCookie.setMaxAge(60 * 60 * 24 *365 * 5);
					}
					else
					{
						loginCookie.setMaxAge(-1);
					}
					response.addCookie(loginCookie);
					request.setAttribute("userlogged", loggedUser);
				//	RequestDispatcher requestDispatcher = request.getRequestDispatcher("landingpage.jsp");
					
			//		requestDispatcher.forward(request, response);
					response.sendRedirect("landingpage.jsp");
				
					System.out.println(loginCookie.getValue());
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



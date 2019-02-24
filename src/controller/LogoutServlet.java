package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Cookie;
import utility.CookieHelper;
/**
 * Servlet implementation class LogoutServlet
 */
@WebServlet("/logout.action")
public class LogoutServlet extends HttpServlet {
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
		// TODO Auto-generated method stub
		Cookie userLogged = CookieHelper.getCookie(request.getCookies(), "userlogged");
		if(userLogged != null)
		{
			//for deletion
			userLogged.setMaxAge(0);
			response.addCookie(userLogged);
		}
		request.getRequestDispatcher("index.jsp").forward(request, response);

	}

}

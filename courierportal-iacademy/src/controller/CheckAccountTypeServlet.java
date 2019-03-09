package controller;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utility.CookieHelper;
import model.UserBean;
import java.sql.SQLException;
/**
 * Servlet implementation class CheckAccountTypeServlet
 */
@WebServlet("/checkaccounttype.action")
public class CheckAccountTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		UserBean.initializeConn();
	}

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
		try {
		UserBean userbean = UserBean.retrieveUser(CookieHelper.getCookie(request.getCookies(), "userlogged").getValue());
		if(userbean.getRoleID() == 2 || userbean.getRoleID() == 3)
		{
			request.setAttribute("userlogged", userbean);
			response.sendRedirect("newannouncement.jsp");
		}
		}catch(SQLException sqle)
		{
			System.err.println(sqle.getMessage());
			response.sendRedirect("landingpage.jsp");
		}
	}

}

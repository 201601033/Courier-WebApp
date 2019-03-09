package controller;

import java.io.IOException;

import java.sql.SQLException;

import java.util.ArrayList;


import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.AnnouncementBean;
import utility.CookieHelper;

/**
 * Servlet implementation class SubmitAnnouncementServlet
 */
@WebServlet("/submitannouncement.action")
public class SubmitAnnouncementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		AnnouncementBean.initializeConn();
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
		String email = CookieHelper.getCookie(request.getCookies(), "userlogged").getValue();
		String announcementbody = request.getParameter("announcementbody");
		String[] tags = request.getParameterValues("tagged");
		ArrayList<String> toArrayList = new ArrayList<String>();
		for(int i = 0; i < tags.length; i++)
		{
			toArrayList.add(tags[i]);
		}
		if(tags.length >=1) {
			toArrayList.add(tags[0]);
		}
		for(int i = 1; i < tags.length; i++) {
			toArrayList.add(", " + tags[i]);
		}
		try {
			AnnouncementBean announcementBean = new AnnouncementBean(email, announcementbody, toArrayList);
			announcementBean.submitAnnouncement();
			request.setAttribute("announcements", AnnouncementBean.getAllAnnouncements());
			response.sendRedirect("landingpage.jsp");
			
		}catch(SQLException sqle)
		{
			System.err.println(sqle.getMessage());
		}
	}

}

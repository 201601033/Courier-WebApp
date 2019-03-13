<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
 	import = "model.AnnouncementBean, java.sql.ResultSet"%>
<jsp:useBean id="announcement" type = "java.sql.ResultSet" scope = "request"></jsp:useBean>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Announcement Bean</title>
</head>
<body>
	<% while(announcement.next()){ %>
	<p><%=announcement.getString("name") %></p>
	<p><%=announcement.getString("announcementBody") %>
	<p><%=announcement.getDate("datePosted") %>
	<p><%=announcement.getTime("timePosted") %>
	
	<%} %>
	<form action = "landingpage.jsp">
	<input type = "submit" value= "Go Back">
	
	</form>
</body>
</html>
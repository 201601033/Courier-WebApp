<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import = "utility.CookieHelper, model.UserBean"
    %>
<jsp:useBean id="userlogged" class = "model.UserBean" scope = "request"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome!</title>
<%
	String str = null;
	Cookie userCookie = CookieHelper.getCookie(request.getCookies(), "userlogged");
	str = userCookie.getValue();
%>
<link rel ="stylesheet" type = "text/css" href = "css/navbar.css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
</head>
<body>

 
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <a class="navbar-brand" href="#">Courier Portal: iACADEMY</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarColor02" aria-controls="navbarColor02" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarColor02">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        <a class="nav-link" href="landingpage.jsp">Home <span class="sr-only"></span></a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#">Compose Announcement</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#">Notifications</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#">Messages</a>
      </li>
       <li class="nav-item">
        <a class="nav-link" href="#"><%=str %></a>
      </li>
      
    </ul>
    <form class="form-inline my-2 my-lg-0">
      <input class="form-control mr-sm-2" type="text" placeholder="Search">
      <button class="btn btn-secondary my-2 my-sm-0" type="submit">Search</button>
    </form>
    <form class="form-inline my-2 my-lg-0" action = "logout.action" method = "post">
      <button class="btn btn-secondary my-2 my-sm-0" type="submit">Log Out</button>
    </form>
  </div>
</nav>

</body>
</html>
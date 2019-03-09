<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import = "java.util.ArrayList, utility.CookieHelper" %>
<% ArrayList<String> tags = new ArrayList<String>();
	tags.add("General");
	tags.add("Computing");
	tags.add("Design");
	tags.add("Business");
	tags.add("SE");
	tags.add("GD");
	tags.add("WD");
	tags.add("Animation");
	tags.add("MMA");
	tags.add("FD");
	tags.add("BA");
	tags.add("FM");
	
%>
<%	
	String str = null;
	Cookie userCookie = CookieHelper.getCookie(request.getCookies(), "userlogged");
	str = userCookie.getValue();
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>CPi: Composing Announcement</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">

<style>


/* Smaller menu when on small screen
 All padding and margin are in em, so they will scale as well */

/* NAVIGATOR BAR */
  @import url(https://fonts.googleapis.com/css?family=Raleway);
  h2 {
    vertical-align: center;
    text-align: center;
  }

  html, body {
    margin: 0;
    height: 100%;
  }

  * {
    font-family: "Raleway";
    box-sizing: border-box;
  }

  .top-nav {
    display: flex;
    flex-direction: row;
    align-items: center;
    justify-content: space-between;
    background-color: #00BAF0;
    background: #014fb3;
  /* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
    color: #FFF;
    height: 50px;
    padding: 1em;
  }

  .menu {
    display: flex;
    flex-direction: row;
    list-style-type: none;
    margin: 0;
    padding: 0;
  }

  .menu > li {
    margin: 0 1rem;
  }

  .menu-button-container {
    display: none;
    height: 100%;
    width: 30px;
    cursor: pointer;
    flex-direction: column;
    justify-content: center;
    align-items: center;
  }

  #menu-toggle {
    display: none;
  }

  .menu-button,
  .menu-button::before,
  .menu-button::after {
    display: block;
    background-color: #fff;
    position: absolute;
    height: 4px;
    width: 30px;
    transition: transform 400ms cubic-bezier(0.23, 1, 0.32, 1);
    border-radius: 2px;
  }

  .menu-button::before {
    content: '';
    margin-top: -8px;
  }

  .menu-button::after {
    content: '';
    margin-top: 8px;
  }

  #menu-toggle:checked + .menu-button-container .menu-button::before {
    margin-top: 0px;
    transform: rotate(405deg);
  }

  #menu-toggle:checked + .menu-button-container .menu-button {
    background: rgba(255, 255, 255, 0);
  }

  #menu-toggle:checked + .menu-button-container .menu-button::after {
    margin-top: 0px;
    transform: rotate(-405deg);
  }

  @media (max-width: 700px) {
    .menu-button-container {
    display: flex;
  }

  .menu {
    position: absolute;
    top: 0;
    margin-top: 50px;
    left: 0;
    flex-direction: column;
    width: 100%;
    justify-content: center;
    align-items: center;
  }

  #menu-toggle ~ .menu li {
    height: 0;
    margin: 0;
    padding: 0;
    border: 0;
    transition: height 400ms cubic-bezier(0.23, 1, 0.32, 1);
  }

  #menu-toggle:checked ~ .menu li {
    border: 1px solid #333;
    height: 2.5em;
    padding: 0.5em;
    transition: height 400ms cubic-bezier(0.23, 1, 0.32, 1);
  }

  .menu > li {
    display: flex;
    justify-content: center;
    margin: 0;
    padding: 0.5em 0;
    width: 100%;
    color: white;
    background-color: #222;
  }

  .menu > li:not(:last-child) {
    border-bottom: 1px solid #444;
  }
}  /* NAV BAR CSS END */


/* CONTAINERS */

/* BOXES */
body
{
  background-color: #fcfcfc;
}
.container
{
  text-align: center;
  padding: 15px;
}

.dpBox /* LEFT BOX. copy-paste if need more */
{
  width: 100%;
  display: inline-block;
  max-width: 300px;
  padding: 30px;
  border-radius: 3px;
  margin: 15px;
  vertical-align: top;
  margin-top: -140px;
}

.dpBoxPicFix{
  display: block;
  margin-left: auto;
  margin-right: auto;
  height: 1in;
  width: 1in;
  border: #014fb3;
  border-style: solid;
  border-radius: 50%;
}

@media screen and (max-width: 600px)
{
  .left-div, .right-div
    {
       max-width: 100%;
    }
}

/* END OF CONTAINERS */

/* SEND BUTTON */


/* END OF SEND BUTTON */

/* GO KYLE HAHA (My re-formatting of necessary things) */
    .schoolLogo{
        /* Logo fixer  */
        margin-left: 10px;
        width: 1in;
        height: 1in;
        max-width: 100%;
        width: auto/10;
    }

    .profilePicture{
        border-radius: 50%;
    }

    .barrier{
        /* barrier = the blue table  */
        background-color: #014fb3;
        margin-top: -20px;
        padding: 10px;
        text-align: center;
    }

    .barrierText{
        /* Text on top of barrier */
        text-align: center;
        margin-right: 50px;
        font-family: Arial, Helvetica, sans-serif;
		    font-size: 1.4vw;
        color: white;
    }

    .textFix{
      color: black;
      font-size: 1vw;
    }

    textarea {
      text-align: left;
      width: 100%;
      height: 220px;
      padding: 12px 20px;
      box-sizing: border-box;
      border: 2px solid #ccc;
      border-radius: 4px;
      background-color: #f8f8f8;
      resize: none;
    }

</style>
</head>

<body>

    <section class="top-nav">
        <div class="barrierText">
                <!-- USER PROFILE PICTURE -->
                <!-- <a href> IS THE PROFILE OF USER -->
                <a href="#"><!--img src="C:\Users\Christian\Pictures\penguin.jpeg"
                    alt="Courier Portal: iACADEMY" width="40" height="40" hspace="15" class="profilePicture"-->Courier Portal: iACADEMY</a>

        </div>
        <input id="menu-toggle" type="checkbox" />
        <label class='menu-button-container' for="menu-toggle">
        <div class='menu-button'></div>
      </label>


        <!-- TOP NAVIGATOR -->
        <ul class="menu">
          <a href="landingpage.jsp"><li class="textFix"><font color="white">Home</a></li></font>
          <a href="checkaccounttype.action"><li class="textFix"><font color="white">Compose Announcement</a></li></font>
          <a href="homePage_sender.html"><li class="textFix"><font color="white">Inbox</a></li></font>
          <a href="homePage_sender.html"><li class="textFix"><font color="white">Notifications</a></li></font>
          <a href="homePage_sender.html"><li class="textFix"><font color="white">Others</a></li></font>
          <li class = "textFix"><form class = "form-inline my-2 my-lg-0">
            <input class = "form-control mr-sm-2" type "text">
            <button class = "btn btn-secondary my-2 my-sm-0" type = "submit">Search</button>
            </form>
          </li>
          <a href="logout.action"><li class="textFix"><font color="white">Sign Out</a></li></font>
        </ul>
      </section>

      <h1> <!-- SCHOOL LOGO -->
            <div class="schoolLogo">
                    <a href="#"><img src="images/iacademylogo.png"
                        alt="Courier Portal: iACADEMY" class="schoolLogo"></a>
            </div>
      </h1>
    </div>

    <!-- USER PROFILE PIC (Center) -->
    <div class="container"><div class="dpBox"><img src="C:\Users\Christian\Pictures\penguin.jpeg"
      alt="Courier Portal: iACADEMY" class="dpBoxPicFix"></div></div>

	<form action = "submitannouncement.action" method = "post">
    <!-- TEXT AREA -->
    <div class="container"><textarea placeholder="Please input content/s of announcement" class="textFix" required="required" name = "announcementbody"></textarea>
    </div>
    <div class = "container">
    <%for(int i = 0; i < tags.size(); i++){ %>
     <input type = checkbox name = "tagged" value = <%=tags.get(i) %>><%=tags.get(i) %>
     <br>
     <%} %>
    </div>

    <!-- UPLOAD IMG/FILE && CUSTOM SYMBOL ANNOUNCEMENT (Icons are missing, but that's their positions na hehe) -->
    <input type = "file" name = "file">
    <div class="container">
    <button class = "btn btn-secondary my-2 my-sm-0" type = "submit">Post!</button>
    </div>
    </form>
     

</body>
</html>

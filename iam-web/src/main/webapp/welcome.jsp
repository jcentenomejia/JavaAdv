<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Identity Module</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<div class="bs-example">
    <div class="jumbotron">
        <div class="container">
            <h1 class="text-info">Welcome to the IAM System </h1><%=session.getAttribute("userName")%><a href="logout"> disconnect</a>
        </div>
    </div>
    <div class="container">
    	<p style="color:${message_color};">${message}</p>
        <div class="row">
            <div class="col-xs-6">
                <h4>Identity Creation</h4>

                <p>Thanks to this action, you can create a brand new Identity, you can click on the button below to
                    begin</p>
                <form action="CreationServlet">
                	<button>Create!</button>
                </form>
            </div>
            <div class="col-xs-6">
                <h4>Identity Search</h4>

                <p>Thanks to this action, you can search an identity and then access to its information. Through this
                    action, you can also modify or delete the wished identity</p>
                <form action="searchIdentity.jsp">	
                	<button>Search!</button>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
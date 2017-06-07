<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Identity Module</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script type="text/javascript">
    function reloadPage(){
        window.location = "searchIdentity.jsp";
    }
</script>
</head>
<body>
<div xmlns="http://www.w3.org/1999/xhtml" class="bs-example">
    <div class="container">
        <h2 class="text-info">Identity Search </h2>
        <a href="welcome.jsp">&lt;&lt; back</a>

        <h3 class="text-info">Search Criteria</h3>
        <p style="color:${message_color};">${message}</p>
    </div>
	
    <form class="form-horizontal" role="form" action="SearchServlet" method="post">
        <div class="form-group">
            <label class="col-sm-2 control-label" for=searchCriteria>Search</label>

            <div class="col-sm-10">
                <input class="form-control" id="searchCriteria" name="searchCriteria" type="text" placeholder="Username or email contains..." />
            </div>
        </div>
        
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10 text-right">
                <button class="btn btn-primary" type="submit">Search</button>
            </div>
        </div>
    </form>
	<c:set var="visibility" value="${(empty visibility) ? 'none' : visibility}" />
    <div class="container" style="display:${visibility}">
        <h3 class="text-info">Search Results</h3>

        <form class="form-horizontal" method="post" action="actionPerformed">
            <div class="table-responsive">
                <table class="table">
                    <thead>
                    <tr>
                        <th>Selection</th>
                        <th>UID</th>
                        <th>Username</th>
                        <th>Email</th>
                        <th>Birth date</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${identities}" var="identity">
					    <tr>      
					        <td> <input name="selection" type="radio" value="${identity.id}" required/></td>
					        <td>${identity.id}</td>
					        <td>${identity.displayname}</td>
					        <td>${identity.email}</td>
					        <td>${identity.birthDate}</td>  
					    </tr>
					</c:forEach>
                   	
                    </tbody>
                </table>
            </div>
            <div class="form-group">
                <div class=" col-sm-offset-2 col-sm-10 text-right">
                    <button class="btn btn-primary" type="submit" name="action" value="update">Modify</button>
                    <button class="btn btn-primary" type="submit" name="action" value="delete">Delete</button>
                    <button class="btn btn-default" type="button" id="cancelButton" onclick="javascript:reloadPage()" >Cancel</button>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
</html>
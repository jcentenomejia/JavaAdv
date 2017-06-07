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
<div xmlns="http://www.w3.org/1999/xhtml" class="container">
        <h2 class="text-info">Identity Modification</h2>
        <a href="welcome.jsp">&lt;&lt; back</a>
    </div>
<form xmlns="http://www.w3.org/1999/xhtml" class="form-horizontal" role="form" method="post" action="update">
		
		<div class="form-group">
            <label class="col-sm-2 control-label" for="userID">UID</label>

            <div class="col-sm-10">
                <input readonly="readonly" class="form-control" id="userID" name="userID" type="text" value="${identity.id}" />
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label" for="userName">Username</label>

            <div class="col-sm-10">
                <input class="form-control" id="userName" name="userName" type="text" placeholder="Username" value="${identity.displayname}" required/>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label" for="email">Email</label>

            <div class="col-sm-10">
                <input class="form-control" id="email" name="email" type="email" placeholder="Email" value="${identity.email}" required/>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label" for="password">Password</label>

            <div class="col-sm-10">
                <input class="form-control" id="password" type="password" name="password" placeholder="Password" value="${identity.password}" required/>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-2 control-label" for="birthdate">Birthdate</label>

            <div class="col-sm-10">
                <input class="form-control" id="birthdate" name="birthdate" type="date" placeholder="yyyy-MM-dd" value="${identity.birthDate}" />
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-2 control-label" for="userType">User type</label>

            <div class="col-sm-10">
                <select class="form-control" id="userType" name="userType"> 
                	<option value="admin" ${identity.userType == 'admin' ? 'selected' : ''}>Admin</option>
					<option value="user" ${identity.userType == 'user' ? 'selected' : ''}>User</option>
                </select>
            </div>
        </div>
        
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button class="btn btn-default" type="submit">Submit</button>
            </div>
        </div>
    </form>

</body>
</html>
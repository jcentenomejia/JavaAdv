<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome Page</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
	
	<div class="container">
	<h2 class="title">Welcome to the identity application</h2>
		
		<form action="authenticate" method="post" role="form">
			
			<p style="color:${message_color};">${message}</p>
			
			<div class="form-group">
				<label>login</label>
				<input class="form-control" name="login" type="text" placeholder="Enter Login" required>
			</div>
			<div class="form-group">
				<label>password</label>
				<input class="form-control" name="pwd" type="password" placeholder="Password" required>
			</div>
			<button class="btn" type="submit">Login</button>
		</form>
	</div>
	
</body>
</html>
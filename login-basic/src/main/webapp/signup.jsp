<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Sign up</title>
<link rel="stylesheet" href="style.css"/>
</head>
<body>
	<h2>Signup form</h2>

	<form action="" method="POST">
	<div class="field">
		<label for="name">Name</label>
		<input required type="text" id="name" name="name" />
		<span style='color: red'>${formErr.get("name") }</span>
	</div>
<div class="field">
		<label for="name">FullName</label>
		<input type="text" id="fullname" name="fullname" />
		<span style='color: red'>${formErr.get("fullname") }</span>
</div>		
<div class="field"> 
		<label for="name">Age</label>
		<input required type="number" id="age" name="age" />
		<span style='color: red'>${formErr.get("age") }</span>
</div>		

<div class="field">
		<label for="password">Password</label>
		<input required type="password" id="password" name="password" />
		<span style='color: red'>${formErr.get("password") }</span>
</div>		

<div class="field">
		<button type="submit">Create my account</button>
</div>		
	</form>
	<p>If you have an account - <a href="login">Login here</a></p>
</body>
</html>
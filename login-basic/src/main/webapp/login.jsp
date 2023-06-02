<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
<link rel="stylesheet" href="style.css"/>
</head>
<body>
<h2>Login form</h2>


<form action="" method="POST">
<div class="field">
<label for="name">Name</label>
<input required type="text" id="name" name="name"/>
<span style='color:red'>${formErr.get("name") } </span>
</div>

<div class="field">
<label for="password">Password</label>
<input required type="password" id="password" name="password"/>
<span style='color:red'>${formErr.get("password") } </span>
</div>

<div class="field">
<button type="submit">Login</button>
</div>
</form>
<p>You don't have any account?<a href="signup"> Sign up here</a></p>
</body>
</html>
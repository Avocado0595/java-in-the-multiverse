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

<span style='color:red'>${formErr.get("header") } </span>
<form action="" method="POST">
<div class="field">
<label for="email">Email</label>
<input required type="email" id="email" name="email"/>
<span style='color:red'>${formErr.get("email") } </span>
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
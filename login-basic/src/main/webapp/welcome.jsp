<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome</title>
</head>
<body>
<h2>Welcome ${user.name}</h2>
<p>Full name: ${user.fullname}</p>
<p>Age: ${user.age}</p>
<button type="submit" id="logout-btn">Log out</button>
<script>
var logoutBtn = document.getElementById("logout-btn");
logoutBtn.onclick=function(){
	fetch("welcome", {method:'post'})
	.then(function(){
		window.location.href="login";
	})
};
</script>
</body>
</html>
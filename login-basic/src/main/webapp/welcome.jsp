<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome</title>
<link rel="stylesheet" href="style.css"/>
</head>
<body>
<h2>Welcome ${user.name}</h2>
<p>Email: ${user.email}</p>
<img id="avatar-img" class="avatar" src="data:image/jpg;base64, <%=request.getAttribute("base64Image") %>"/>
<button type="button" id="logout-btn">Log out</button>
<button type="button" id="update-btn">Update</button>
<script>
var logoutBtn = document.getElementById("logout-btn");
var updateBtn = document.getElementById("update-btn");
logoutBtn.onclick=function(){
	fetch("welcome", {method:'post'})
	.then(function(){
		window.location.href="login";
	})
};

updateBtn.onclick=function(){
	window.location.href="update";
};
</script>
</body>
</html>
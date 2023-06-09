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
<span style='color:red'>${formErr.get("header") } </span>
	<form action="" method="POST" enctype="multipart/form-data">
	<div class="field">
		<label for="name">Name</label>
		<input required type="text" id="name" name="name" />
		<span style='color: red'>${formErr.get("name") }</span>
	</div>
<div class="field">
		<label for="email">Email</label>
		<input type="email" id="email" name="email" />
		<span style='color: red'>${formErr.get("email") }</span>
</div>		
	

<div class="field">
		<label for="password">Password</label>
		<input required type="password" id="password" name="password" />
		<span style='color: red'>${ formErr.get("password") }</span>
</div>		
<div class="field">
		
        <img id="avatar-img" class="avatar" src="data:image/jpg;base64, <%=request.getAttribute("base64Image") %>"/>                            
        
		<input value="default-avatar.jpg" required type="file" id="avatar" name="avatar" />
		<span style='color: red'>${formErr.get("avatar") }</span>
</div>	
<div class="field">
		<button type="submit">Create my account</button>
</div>		
	</form>
	<p>If you have an account - <a href="login">Login here</a></p>
	<script>
	var avatarImg = document.getElementById("avatar-img");
	var avatar =document.getElementById("avatar");
	avatar.onchange = evt => {
		  const [file] = avatar.files;
		  if (file) {
			  avatarImg.src = URL.createObjectURL(file)
		  }
		}
	</script>
</body>
</html>
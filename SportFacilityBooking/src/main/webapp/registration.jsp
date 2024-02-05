<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Registration</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container mt-5">
    <h2 class="text-center mb-4">Registration Form</h2>

    <form action="register" method="post" onsubmit="validateForm(event)" id="myForm">
        <div class="form-group">
            <label for="first_name">First Name:</label>
            <input type="text" name="first_name" id="first_name" oninput="handleInput('first_name')"
                   placeholder="First name" class="form-control" required>
            <p id="ferror" style="color:red"></p>
        </div>

        <div class="form-group">
            <label for="last_name">Last Name:</label>
            <input type="text" name="last_name" id="last_name" oninput="handleInput('last_name')"
                   placeholder="Last name" class="form-control" required>
            <p id="lerror" style="color:red"></p>
        </div>

        <div class="form-group">
            <label for="tel">Phone Number:</label>
            <input type="text" name="tel" id="tel" oninput="handleInput('tel')"
                   placeholder="0912345678" class="form-control" required>
            <p id="perror" style="color:red"></p>
        </div>

        <div class="form-group">
            <label for="email">Email:</label>
            <input type="email" name="email" id="email" oninput="handleInput('email')"
                   placeholder="like abc@email.com" class="form-control" required>
            <p id="emlerror" style="color:red"></p>
        </div>

        <div class="form-group">
            <label for="password">New Password:</label>
            <input type="password" name="password" id="password" oninput="handleInput('password')"
                   placeholder="********" class="form-control" required>
            <p id="pwderror" style="color:red"></p>
        </div>

        <input type="submit" value="Register" class="btn btn-primary btn-block">
    </form>

    <a href="home.html" class="mt-3 d-block text-center">Home</a>
</div>


	<script type="text/javascript">
		function handleInput(str){
			var valid = true
		  var disabled = document.getElementById(str).disabled;
		   switch (str){
		   case 'first_name':
			   {
				var regex = /^[A-Za-z]+$/;
				var first_name = document.getElementById("first_name").value;
				 var t = regex.test(first_name);
				 if(t || disabled){
				 document.getElementById("ferror").textContent = "";
				 }else{
					 document.getElementById("ferror").textContent = "Input must only contain letters";
					 valid = false
					 }
			   break;
			   }
		   case 'last_name':
			   {
				var regex = /^[A-Za-z]+$/;
				 var last_name = document.getElementById("last_name").value;
				 var t = regex.test(last_name);
				 if(t || disabled){
				 document.getElementById("lerror").textContent = "";
				 }else{document.getElementById("lerror").textContent = "Input must only contain letters";valid = false;}
				break;
			   }
		   case 'tel':
			   {
				var regex = /^0+\d{9}$/;
				 var tel = document.getElementById("tel").value;
				 var t = regex.test(tel);
				 if(t || disabled){
				 document.getElementById("perror").textContent = "";
				 }else{document.getElementById("perror").textContent = "Input must be a phone number like 0912345678";valid = false;}
				break;
			   }
		   case 'email':
			   {
				var regex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
				var email = document.getElementById("email").value;
				 var t = regex.test(email);
				 if(t || disabled){
				 document.getElementById("emlerror").textContent = "";
				 }else{document.getElementById("emlerror").textContent = "Input must be an email like abc@email.com";valid = false;}
				break;
			   }	
		   case 'password':
			   {
				var pwd = document.getElementById("password").value;
				 isPasswordValid(pwd);
				 }
	  
		   }
		   return valid;
	   }
	   
	   function isPasswordValid(password) {
		var disabled = document.getElementById("password").disabled;
		document.getElementById("pwderror").innerHTML = "";
		valid = true;
		// At least 8 characters
		if (password.length < 8 && !disabled) {
			document.getElementById("pwderror").innerHTML += "<p>Must have at least 8 characters</p>";
			valid = false;
		}
	  
		// Contains at least one lowercase letter
		if (!/[a-z]/.test(password) && !disabled) {
		  document.getElementById("pwderror").innerHTML += "<p>Must contain at least one lowercase letter</p>";
		  valid = false;
		}
	  
		// Contains at least one uppercase letter
		if (!/[A-Z]/.test(password) && !disabled) {
		  document.getElementById("pwderror").innerHTML += "<p>Must contain at least one uppercase letter</p";
			valid = false;
		}
	  
		// Contains at least one digit
		if (!/\d/.test(password) && !disabled) {
		 document.getElementById("pwderror").innerHTML += "<p>Must contain at least one number</p>";
		 valid = false;
		}
	  
		// Contains at least one special character
		if (!/[!@#$%^&*]/.test(password) && !disabled) {
		  document.getElementById("pwderror").innerHTML += "<p>Must contain at least one special character like !@#$%^&*</p>";
		  valid = false;
		}
	  
	  }
	  
	   function toggleEdit(val,btn){
		   var stat = document.getElementById(val);
		   var btn = document.getElementById(btn);
		   if (stat.disabled){
			   stat.disabled = !stat.disabled;
			   btn.innerHTML = 'Cancel';
		   }else{
			   stat.disabled = !stat.disabled;
			   stat.value = "";
			   btn.innerHTML = 'Edit';
			   handleInput(val);
		   }
	   }
	   function validateForm(event) {
  event.preventDefault(); 

  var nameInput = document.getElementById("first_name");
  var lnameInput = document.getElementById("last_name");
  var emailInput = document.getElementById("email");
  var phone = document.getElementById("tel");
  var password = document.getElementById("paswword");

  if (!handleInput('first_name')) {
    nameInput.focus();
    return;
  }if (!handleInput('last_name')) {
    lnameInput.focus();
    return;
  }if (!handleInput('email')) {
    emailInput.focus();
    return;
  }if (!handleInput('tel')) {
    phone.focus();
    return;
  }if (!handleInput('password')) {
    password.focus();
    return;
  }
  
  document.getElementById("myForm").submit();
  
	   }
	   </script>
</body>
</html>
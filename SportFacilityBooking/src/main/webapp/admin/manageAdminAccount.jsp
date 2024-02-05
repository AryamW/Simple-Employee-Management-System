<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.sfb.User" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Manage Account</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css">
    <script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.bundle.js"></script>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f8f9fa;
            margin: 0;
            padding: 0;
        }

        h1 {
            text-align: center;
            margin-top: 20px;
        }

        form {
            max-width: 400px;
            margin: 20px auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        input[type="text"],
        input[type="email"],
        input[type="password"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 10px;
            box-sizing: border-box;
            border: 1px solid #ced4da;
            border-radius: 4px;
        }

        button {
            background-color: #007bff;
            color: #fff;
            border: none;
            padding: 10px 20px;
            cursor: pointer;
            border-radius: 4px;
        }

        button:disabled {
            background-color: #ccc;
            cursor: not-allowed;
        }

        p {
            margin: 5px 0;
        }

        p.error {
            color: red;
        }
    </style>
</head>
<body>

    <%@ include file="../adminHeader.jsp" %>
    <%
	User user = (User) request.getAttribute("user");
%>
    <h1>Manage Your Profile</h1>
    <form action="manageAdminAccount" method="POST" onsubmit="validateForm(event)" id="myForm">
        First Name: <input type="text" name="first_name" id="first_name" oninput="handleInput('first_name')" placeholder="<%= user.getFname() %>" disabled required>
        <button type="button" id="edit-btn1" onclick="toggleEdit('first_name','edit-btn1')">Edit</button>
        <p id="ferror" class="error"></p>
        <br>
        Last Name: <input type="text" name="last_name" id="last_name" oninput="handleInput('last_name')" placeholder="<%= user.getLname() %>" disabled required>
        <button type="button" id="edit-btn2" onclick="toggleEdit('last_name','edit-btn2')">Edit</button>
        <p id="lerror" class="error"></p>
        <br>
        Phone Number: <input type="text" name="tel" id="tel" oninput="handleInput('tel')" placeholder="0<%= user.getTel() %>" disabled required>
        <button type="button" id="edit-btn3" onclick="toggleEdit('tel','edit-btn3')">Edit</button>
        <p id="perror" class="error"></p>
        <br>
        Email: <input type="email" name="email" id="email" oninput="handleInput('email')" placeholder="<%= user.getEmail() %>" disabled required>
        <button type="button" id="edit-btn4" onclick="toggleEdit('email','edit-btn4')">Edit</button>
        <p id="emlerror" class="error"></p>
        <br>
        New Password: <input type="password" name="password" id="password" oninput="handleInput('password')" placeholder="********" disabled required>
        <button type="button" id="edit-btn5" onclick="toggleEdit('password','edit-btn5')">Edit</button>
        <p id="pwderror" class="error"></p>
        <br>
        <input type="submit" value="Confirm Changes">
    </form>

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
	  var password = document.getElementById("password");

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
	  } if(nameInput.disabled && lnameInput.disabled && emailInput.disabled && phone.disabled && password.disabled){
		  return;
	  }
	  
	  
	  document.getElementById("myForm").submit();
	  
		   }
 </script>
</body>
</html>
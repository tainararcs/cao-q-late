<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="br.trcs.petshop.utils.Consts" %>

<jsp:include page="messages.jsp" />

<!DOCTYPE html>

<html>
	<head>
		<meta charset="UTF-8">
		<title>Cão Q-Late - Login</title>
		
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">
		<link rel="stylesheet" type="text/css" href="css/login.css">
		<link rel="icon" type="image/png" href="img/favicon.ico">
	</head>
	
	<body>
		<h1 class="title">Cão Q-Late</h1>
		
		<form method="post" action="controller" class="login">
			<div class="email">
				<label for="email">E-mail</label>
				<input id="email" type="email" name="email" required>
			</div>
		
			<div class="password">
				<label for="password">Senha</label>
				<input id="password" type="password" name="password" required>
			</div>
			
			<input type="hidden" name="logic" value="AuthAdmin">
			<input type="submit" value="Login">
		</form>
	</body>
</html>
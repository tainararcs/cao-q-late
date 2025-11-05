<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="br.trcs.petshop.utils.Consts" %>
<%@ page import="java.time.LocalDate"%>

<!DOCTYPE html>

<html>
	<head>
		<meta charset="UTF-8">
		<title>Cão Q-Late - Cadastrar Cliente</title>
		
		<link rel="icon" type="image/png" href="img/favicon.ico">
		<link rel="stylesheet" href="css/form.css">
	</head>
	
	<body>
		<c:import url="<%= Consts.MENU %>"/>		
		
		<h2>Cadastrar Cliente</h2>
		
		<jsp:include page="messages.jsp"/>
		
		<form method="post" action="controller">
			<div class="name">
				<label>Nome</label>
				<input type="text" name="name" required> 
			</div>
			
			<div class="cpf">
				<label>CPF</label>
				<input type="text" name="cpf" placeholder="123.456.789-00" pattern="\d{3}\.\d{3}\.\d{3}-\d{2}" required> 
			</div>
			
			<div class="birthDate">
				<label>Data de nascimnto</label>
				<input type="date" name="birthDate" max="<%= LocalDate.now() %>" required> 
			</div>
			
			<div class="email">
				<label>E-mail</label>
				<input type="email" name="email" required> 
			</div>
			<div class="phoneNumber">
				<label>Número de telefone</label>
				<input type="text" name="phoneNumber" placeholder="(32) 99999-9999" required> 
			</div>

			<input type="hidden" name="logic" value="AddClient">
			<input type="submit" value="Cadastrar Cliente">
		</form>
		
		<c:import url="<%= Consts.FOOTER %>"/>		
	</body>
</html>
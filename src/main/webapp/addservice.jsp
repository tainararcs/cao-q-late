<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="br.trcs.petshop.utils.Consts" %>

<!DOCTYPE html>

<html>
	<head>
		<meta charset="UTF-8">
		<title>Cão Q-Late - Cadastrar Serviço</title>
		
		<link rel="icon" type="image/png" href="img/favicon.ico">
		<link rel="stylesheet" href="css/form.css">
	</head>
	
	<body>
		<c:import url="<%= Consts.MENU %>"/>		
		
		<main>
		 	<h2>Cadastrar Serviço</h2>
		 	
		 	<jsp:include page="messages.jsp" />
		 	
		 	<form method="post" action="controller">
				<div class="name">
					<label>Serviço</label>
					<input type="text" name="name" required> 
				</div>
				<div class="price">
					<label>Preço (R$)</label>
					<input type="text" name="price" placeholder="50.50" required> 
				</div>
				
				<input type="hidden" name="logic" value="AddService"> 
				<input type="submit" value="Registrar Serviço">
			</form>
		</main>
		
		<c:import url="<%= Consts.FOOTER %>"/>		
	</body>
</html>
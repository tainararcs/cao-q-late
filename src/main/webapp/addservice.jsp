<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="br.trcs.petshop.utils.Consts" %>

<!DOCTYPE html>

<html>
	<head>
		<meta charset="UTF-8">
		<title>Cão Q-Late - ${Consts.ADD_SERVICE_TITLE}</title>
		
		<link rel="icon" type="image/png" href="img/favicon.ico">
		<link rel="stylesheet" href="css/form.css">
	</head>
	
	<body>
		<jsp:include page="${Consts.MENU_JSP}"/>
		
		<main>
		 	<h2>${Consts.ADD_SERVICE_TITLE}</h2>
		 	
		 	<jsp:include page="${Consts.MESSAGES_JSP}"/>
		 	
		 	<form method="post" action="${Consts.CONTROLLER}">
				<div class="name">
					<label>Serviço</label>
					<input type="text" name="name" required>
				</div>
				
				<div class="price">
					<label>Preço (R$)</label>
					<input type="text" name="price" placeholder="50.50" required>
				</div>
				
				<input type="hidden" name="logic" value="${Consts.ADD_SERVICE_LOGIC}">
				<input type="submit" value="${Consts.ADD_BUTTON}">
			</form>
		</main>
		
		<jsp:include page="${Consts.FOOTER_JSP}"/>
	</body>
</html>
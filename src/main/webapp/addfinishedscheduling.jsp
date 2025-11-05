<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page import="java.time.LocalDate"%>
<%@ page import="br.trcs.petshop.utils.Consts" %>

<!DOCTYPE html>

<html>
	<head>
		<meta charset="UTF-8">
		<title>Cão Q-Late - Finalizar Agendamento</title>
		
		<link rel="icon" type="image/png" href="img/favicon.ico">
		<link rel="stylesheet" href="css/form.css">
	</head>
	
	<body>
		<c:import url="<%= Consts.MENU %>"/>
		
		<main>
			<h2>Lançar Agendamento Finalizado</h2>
			
			<jsp:include page="messages.jsp"/>
			
			<form method="post" action="controller">
			    <div class="idscheduling">
				    <label>Id do agendamento</label>
				    <input type="text" name="idscheduling" required>
			    </div>
			
				<div class="date">
				    <label>Data de execução</label>
				    <input type="date" name="date" required max="<%= LocalDate.now() %>">
				</div>
				
			    <input type="hidden" name="logic" value="AddFinishedScheduling">
			    <input type="submit" value="Registrar Agendamento Finalizado">
			</form>
		</main>
		
		<c:import url="<%= Consts.FOOTER %>"/>
	</body>
</html>
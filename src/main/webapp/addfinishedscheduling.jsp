<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="br.trcs.petshop.utils.Consts" %>
<%@page import="java.time.LocalDate"%>

<!DOCTYPE html>

<html>
	<head>
		<meta charset="UTF-8">
		<title>Cão Q-Late - ${Consts.ADD_FINISHED_SCHEDULING_TITLE}</title>
		
		<link rel="icon" type="image/png" href="img/favicon.ico">
		<link rel="stylesheet" href="css/form.css">
	</head>
	
	<body>
		<jsp:include page="${Consts.MENU_JSP}"/>
		
		<main>
			<h2>${Consts.ADD_FINISHED_SCHEDULING_TITLE}</h2>
			
			<jsp:include page="${Consts.MESSAGES_JSP}"/>
			
			<form method="post" action="${Consts.CONTROLLER}">
			    <div class="idscheduling">
				    <label>Id do agendamento</label>
				    <input type="text" name="idscheduling" required>
			    </div>
			
				<div class="date">
				    <label>Data de execução</label>
				    <input type="date" name="date" required max="<%= LocalDate.now() %>">
				</div>
				
			    <input type="hidden" name="logic" value="${Consts.ADD_FINISHED_SCHEDULING_LOGIC}">
			    <input type="submit" value="${Consts.FINALIZE_BUTTON}">
			</form>
		</main>
		
		<jsp:include page="${Consts.FOOTER_JSP}"/>
	</body>
</html>

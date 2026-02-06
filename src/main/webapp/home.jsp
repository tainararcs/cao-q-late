<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="br.trcs.petshop.utils.Consts" %>

<jsp:include page="messages.jsp"/>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Cão Q-Late</title>
		
		<!-- Bootstrap Icons -->
		<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css" rel="stylesheet">
		<link rel="stylesheet" type="text/css" href="css/home.css">
		<link rel="icon" type="image/png" href="img/favicon.ico">
		
		<script type="text/javascript" src="js/home.js"></script>
	</head>
	
	<body>
		<div class="title"><span class="paw">🐾</span> Cão Q-Late</div>
		
		<!-- Botão sair -->
		<a href="${Consts.CONTROLLER}?logic=${Consts.LOGOUT_LOGIC}" class="logout"><i class="bi bi-box-arrow-right"></i></a>
		
		<h2>Bem-vindo ao Cão Q-Late, Admin!</h2>
		
		
		
		<div class="main-container">
		    <div class="image-container">
			    <div class="image-background"></div>
			    <img id="dog-image" src="img/dogs.jpg" alt="Cachorro">
			    <img id="dog-image" src="img/dogs1.jpg" alt="Cachorro">
			    <img id="dog-image" src="img/dogs2.jpg" alt="Cachorro">
			</div>
		
		    <nav class="options">
		        <ul>
		            <li><a href="${Consts.CONTROLLER}?logic=${Consts.REDIRECT_ADD_CLIENT_JSP}"> <i class="bi bi-person-add"></i> Cadastrar Cliente</a></li>
		            <li><a href="${Consts.CONTROLLER}?logic=${Consts.LIST_CLIENTS_LOGIC}&returnPage=${Consts.LIST_CLIENTS_JSP}"> <i class="bi bi-list-ul"></i> Listar Clientes</a></li>
					<li><a href="${Consts.CONTROLLER}?logic=${Consts.REDIRECT_ADD_DOG_JSP}"> <i class="bi bi-heart"></i> Cadastrar Cão</a></li>
					<li><a href="${Consts.CONTROLLER}?logic=${Consts.REDIRECT_LIST_DOGS_JSP}"> <i class="bi bi-list-ul"></i> Listar cães</a></li>
					<li><a href="${Consts.CONTROLLER}?logic=${Consts.REDIRECT_ADD_SERVICE_JSP}"> <i class="bi bi-clipboard-check"></i> Cadastrar Serviço</a></li>
					<li><a href="${Consts.CONTROLLER}?logic=${Consts.LIST_SERVICES_LOGIC}&returnPage=${Consts.LIST_SERVICES_JSP}"> <i class="bi bi-list-ul"></i> Listar serviços</a></li>
					<li><a href="${Consts.CONTROLLER}?logic=${Consts.REDIRECT_UPDATE_SERVICE_JSP}"> <i class="bi bi-pencil-square"></i> Alterar Serviço</a></li>
					<li><a href="${Consts.CONTROLLER}?logic=${Consts.REDIRECT_ADD_SCHEDULING_JSP}"> <i class="bi bi-calendar-plus"></i> Cadastrar Agendamento</a></li>
					<li><a href="${Consts.CONTROLLER}?logic=${Consts.REDIRECT_ADD_FINISHED_SCHEDULING_JSP}"> <i class="bi bi-calendar-check"></i> Finalizar Agendamento</a></li>
					<li><a href="${Consts.CONTROLLER}?logic=${Consts.REDIRECT_LIST_DOG_SCHEDULINGS_JSP}"> <i class="bi bi-list-ul"></i> Listar Agendamentos do Cão</a></li>
					<li><a href="${Consts.CONTROLLER}?logic=${Consts.REDIRECT_LIST_NOT_FINISHED_SCHEDULING_JSP}"> <i class="bi bi-clock-history"></i> Listar Agendamentos não Finalizados</a></li>
					<li><a href="${Consts.CONTROLLER}?logic=${Consts.REDIRECT_SHOW_REPORT_JSP}"> <i class="bi bi-file-earmark-text"></i> Relatório</a></li>
		        </ul>
		    </nav>
		</div>
		
		<jsp:include page="${Consts.FOOTER_JSP}"/>
	</body>
</html>

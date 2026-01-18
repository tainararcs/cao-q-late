<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="br.trcs.petshop.utils.Consts" %>
<%@ page import="br.trcs.petshop.dao.ServiceDAO" %>

<%
   // Carrega a lista de serviços.
   ServiceDAO serviceDAO = new ServiceDAO();
   request.setAttribute("servicesList", serviceDAO.list());
%>

<!DOCTYPE html>

<html>
	<head>
		<meta charset="UTF-8">
   	<title>Cão Q-Late - Serviços Cadastrados</title>
   	
   	<link rel="icon" type="image/png" href="img/favicon.ico">
   	<link rel="stylesheet" href="css/form.css">
	</head>
	
	<body>
		<c:import url="${Consts.MENU}"/>
		
		<main>
			<h2>Lista de Serviços Cadastrados</h2>
			
			<!-- Serviços -->
           <div class="services">
           	<c:if test="${not empty servicesList}">
	            	<table>
	            		<thead>
	            			<tr><th>ID do serviço</th><th>Nome</th><th>Preço (R$)</th></tr>
	            		</thead>
	            		<tbody>
			            	<c:forEach var="s" items="${servicesList}">
			            		<tr><td>${s.id}</td><td>${s.name}</td><td>${s.price}</td></tr>
			                </c:forEach>
	                	</tbody>
	            	</table>
           	</c:if>
           </div>
		</main>
		
		<c:import url="${Consts.FOOTER}"/>
	</body>
</html>
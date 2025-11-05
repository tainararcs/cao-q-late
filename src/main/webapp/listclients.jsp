<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="br.trcs.petshop.utils.Consts" %>
<%@ page import="br.trcs.petshop.dao.ClientDAO" %>

<%
    // Carrega a lista de clientes.
    ClientDAO dao = new ClientDAO();
    request.setAttribute("clientsList", dao.list());
%>

<!DOCTYPE html>

<html>
	<head>
		<meta charset="UTF-8">
    	<title>Cão Q-Late - Clientes Cadastrados</title>
    	
    	<link rel="icon" type="image/png" href="img/favicon.ico">
    	<link rel="stylesheet" href="css/form.css">
	</head>
	
	<body>
		<c:import url="<%= Consts.MENU %>"/>
		
		<main>
			<h2>Lista de Clientes Cadastrados</h2>
			
			<!-- Clientes -->
            <div class="services">
            	<c:if test="${not empty clientsList}">
	            	<table>
	            		<thead>
	            			<tr><th>CPF</th><th>Nome</th><th>Data de Nascimento</th><th>E-mail</th><th>Telefone</th></tr>
	            		</thead>
	            		<tbody>
			            	<c:forEach var="c" items="${clientsList}">
			            		<tr><td>${c.cpf}</td><td>${c.name}</td><td>${c.birthDate}</td><td>${c.email}</td><td>${c.phoneNumber}</td></tr>
			                </c:forEach>
	                	</tbody>
	            	</table>
            	</c:if>
            </div>
		</main>
		
		<c:import url="<%= Consts.FOOTER %>"/>
	</body>
</html>
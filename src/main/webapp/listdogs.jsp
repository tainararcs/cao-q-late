<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="br.trcs.petshop.utils.Consts" %>
<%@ page import="br.trcs.petshop.dao.ClientDAO" %>

<%
    // Carrega a lista de cães.
    String cpf = request.getParameter("cpf");
    if (cpf != null && !cpf.isEmpty()) {
    	ClientDAO dao = new ClientDAO();
    	request.setAttribute("dogsList", dao.listDogs(cpf));
    }
%>

<!DOCTYPE html>

<html>
	<head>
		<meta charset="UTF-8">
    	<title>Cão Q-Late - Cães Cadastrados</title>
    	
    	<link rel="icon" type="image/png" href="img/favicon.ico">
    	<link rel="stylesheet" href="css/form.css">
	</head>
	
	<body>
		<c:import url="<%= Consts.MENU %>"/>
		
		<main>
			<h2>Lista de Cães Cadastrados</h2>
			
			<form method="get" action="<%= Consts.LIST_DOGS %>">
				<div class="cpf">
					<label>CPF do cliente</label>
					<input type="text" name="cpf" value="${param.cpf}" placeholder="123.456.789-00" required> 
				</div>
				<input type="submit" value="Listar Cães">
			</form>
		
			<!-- Cães -->
            <div class="dogs">
            	<c:if test="${not empty dogsList}">
	            	<table>
	            		<thead>
	            			<tr><th>ID do cão</th><th>Nome</th><th>Raça</th><th>Porte</th></tr>
	            		</thead>
	            		<tbody>
			            	<c:forEach var="d" items="${dogsList}">
			            		<tr><td>${d.id}</td><td>${d.name}</td><td>${d.breed}</td><td>${d.size}</td></tr>
			                </c:forEach>
	                	</tbody>
	            	</table>
            	</c:if>
            </div>
		</main>
		
		<c:import url="<%= Consts.FOOTER %>"/>
	</body>
</html>
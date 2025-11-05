<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="br.trcs.petshop.utils.Consts" %>
<%@ page import="br.trcs.petshop.dao.ClientDAO" %>
<%@ page import="br.trcs.petshop.enums.DogSize" %>

<%
    DogSize[] dogSizes = DogSize.values();
    request.setAttribute("dogSizes", dogSizes);
    
 	// Se o CPF foi enviado, busca os cães do cliente.
    String name = request.getParameter("name");
    if (name != null && !name.isEmpty()) {
        ClientDAO clientDAO = new ClientDAO();
        request.setAttribute("clientList", clientDAO.list());
    }
%>

<!DOCTYPE html>

<html>
	<head>
		<meta charset="UTF-8">
		<title>Cão Q-Late - Cadastrar Cão</title>
		
		<link rel="icon" type="image/png" href="img/favicon.ico">
		<link rel="stylesheet" href="css/form.css">
	</head>
	
	<body>		
		<c:import url="<%= Consts.MENU %>"/>		
		
		<h2>Cadastrar Cão</h2>
		
		<jsp:include page="messages.jsp" />
		
		<form method="get" action="<%= Consts.ADD_DOG %>">
			<div class="name">
		        <label>Nome do Cão</label>
		        <input type="text" name="name" value="${param.name}" required>
		    </div>
		
		    <div class="breed">
		        <label>Raça</label>
		        <input type="text" name="breed" value="${param.breed}" required>
		    </div>
		
		    <!-- Lista de portes -->
		    <div class="size">
		        <label>Porte</label>
		        <c:forEach var="s" items="${dogSizes}">
				    <label>
				        <input type="radio" name="size" value="${s.label}" <c:if test="${param.size == s.label}">checked</c:if> required>
				        ${s.label}
				    </label>
				</c:forEach>
		    </div>
		
		    <input type="submit" value="Buscar clientes">
		</form>
		
		<!-- Exibe a lista de clientes já cadastrados -->
		<c:if test="${not empty clientList}">
		    <form method="post" action="controller">
		        <div class="client">
		            <label>Selecione o tutor</label>
		            <c:forEach var="c" items="${clientList}">
		                <label>
			            	<input type="radio" name="client" value="${c.cpf}" required>
			                ${c.cpf} - ${c.name}
						</label>
		            </c:forEach>
		        </div>
		
		        <!-- Preserva os dados do cão -->
		        <input type="hidden" name="name" value="${param.name}">
		        <input type="hidden" name="breed" value="${param.breed}">
		        <input type="hidden" name="size" value="${param.size}">
		
		        <input type="hidden" name="logic" value="AddDog">
		        <input type="submit" value="Cadastrar Cão">
		    </form>
		</c:if>
		
		<c:import url="<%= Consts.FOOTER %>"/>		
	</body>
</html>
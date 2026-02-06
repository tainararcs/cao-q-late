<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="br.trcs.petshop.utils.Consts" %>
<%@ page import="br.trcs.petshop.enums.DogSize" %>

<!DOCTYPE html>

<html>
	<head>
		<meta charset="UTF-8">
		<title>Cão Q-Late - ${Consts.ADD_DOG_TITLE}</title>
		
		<link rel="icon" type="image/png" href="img/favicon.ico">
		<link rel="stylesheet" href="css/form.css">
	</head>
	
	<body>		
		<jsp:include page="${Consts.MENU_JSP}"/>
		
		<main>
			<h2>${Consts.ADD_DOG_TITLE}</h2>
			
			<jsp:include page="${Consts.MESSAGES_JSP}"/>
			
			<form method="get" action="${Consts.CONTROLLER}">
				<div class="name">
			        <label>Nome do Cão</label>
			        <input type="text" name="name" value="${param.name}" required>
			    </div>
			
				<!-- Raça -->
			    <div class="breed">
			        <label>Raça</label>
			        <input type="text" name="breed" value="${param.breed}" required>
			    </div>
			
			    <!-- Portes -->
			    <div class="size">
			        <label>Porte</label>
			        <label>
						<input type="radio" name="size" value="${DogSize.SMALL}" ${param.size == 'SMALL' ? 'checked' : ''} required> 
						Pequeno
					</label>
						
					<label>
						<input type="radio" name="size" value="${DogSize.MEDIUM}" ${param.size == 'MEDIUM' ? 'checked' : ''} required>
						Médio
					</label>
						
					<label>
						<input type="radio" name="size" value="${DogSize.LARGE}" ${param.size == 'LARGE' ? 'checked' : ''} required>
						Grande
					</label>
			    </div>
				
				<input type="hidden" name="logic" value="${Consts.LIST_CLIENTS_LOGIC}">
				<input type="hidden" name="returnPage" value="${Consts.ADD_DOG_JSP}">
			    <input type="submit" value="${Consts.LIST_CLIENTS_BUTTON}">
			</form>
			
			<!-- Exibe a lista de clientes já cadastrados -->
			<c:if test="${not empty clientsList}">
			    <form method="post" action="${Consts.CONTROLLER}">
			        <div class="client">
			            <label>Selecione o tutor</label>
			            <c:forEach var="c" items="${clientsList}">
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
			
			        <input type="hidden" name="logic" value="${Consts.ADD_DOG_LOGIC}">
			        <input type="submit" value="${Consts.ADD_BUTTON}">
			    </form>
			</c:if>
		</main>
		
		<jsp:include page="${Consts.FOOTER_JSP}"/>
	</body>
</html>

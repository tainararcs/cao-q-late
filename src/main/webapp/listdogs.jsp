<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="br.trcs.petshop.utils.Consts" %>

<!DOCTYPE html>

<html>
	<head>
		<meta charset="UTF-8">
	   	<title>Cão Q-Late - ${Consts.LIST_DOGS_TITLE}</title>
	   	
	   	<link rel="icon" type="image/png" href="img/favicon.ico">
	   	<link rel="stylesheet" href="css/form.css">
	</head>
	
	<body>
		<jsp:include page="${Consts.MENU_JSP}"/>
		
		<main>
			<h2>${Consts.LIST_DOGS_TITLE}</h2>
			
			<jsp:include page="${Consts.MESSAGES_JSP}"/>
			
			<form method="get" action="${Consts.CONTROLLER}">
				<div class="cpf">
					<label>CPF do cliente</label>
					<input type="text" name="cpf" value="${param.cpf}" placeholder="123.456.789-00" required>
				</div>
				
				<input type="hidden" name="logic" value="${Consts.LIST_DOGS_LOGIC}">
				<input type="hidden" name="returnPage" value="${Consts.LIST_DOGS_JSP}">
				<input type="submit" value="${Consts.LIST_DOGS_BUTTON}">
			</form>
		
			<!-- Cães -->
			<div class="dogs">
				<c:if test="${not empty dogsList}">
				 	<table>
				 		<thead>
				 			<tr>
				  			<th>ID do cão</th>
				  			<th>Nome</th>
				  			<th>Raça</th>
				  			<th>Porte</th>
				 			</tr>
				 		</thead>
				 		
				 		<tbody>
							<c:forEach var="d" items="${dogsList}">
								<tr>
									<td>${d.id}</td>
									<td>${d.name}</td>
									<td>${d.breed}</td>
									<td>${d.size}</td>	
								</tr>
							</c:forEach>
				     	</tbody>
				 	</table>
				</c:if>
			</div>
		</main>
		
		<jsp:include page="${Consts.FOOTER_JSP}"/>
	</body>
</html>
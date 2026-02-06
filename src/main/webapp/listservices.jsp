<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="br.trcs.petshop.utils.Consts" %>

<!DOCTYPE html>

<html>
	<head>
		<meta charset="UTF-8">
	   	<title>Cão Q-Late - ${Consts.LIST_SERVICES_TITLE}</title>
	   	
	   	<link rel="icon" type="image/png" href="img/favicon.ico">
	   	<link rel="stylesheet" href="css/form.css">
	</head>
	
	<body>
		<jsp:include page="${Consts.MENU_JSP}"/>
		
		<main>
			<h2>${Consts.LIST_SERVICES_TITLE}</h2>
			
			<jsp:include page="${Consts.MESSAGES_JSP}"/>
			
			<div class="services">
				<c:if test="${not empty servicesList}">
					<table>
						<thead>
							<tr>
								<th>ID do serviço</th>
								<th>Nome</th>
								<th>Preço (R$)</th>
							</tr>
						</thead>
						
						<tbody>
						 	<c:forEach var="s" items="${servicesList}">
							<tr>
								<td>${s.id}</td>
								<td>${s.name}</td>
								<td>${s.price}</td>
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
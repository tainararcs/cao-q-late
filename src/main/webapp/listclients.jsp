<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="br.trcs.petshop.utils.Consts" %>

<!DOCTYPE html>

<html>
	<head>
		<meta charset="UTF-8">
	   	<title>Cão Q-Late - ${Consts.LIST_CLIENTS_TITLE}</title>
	   	
	   	<link rel="icon" type="image/png" href="img/favicon.ico">
	   	<link rel="stylesheet" href="css/form.css">
	</head>
	
	<body>
		<jsp:include page="${Consts.MENU_JSP}"/>
		
		<main>
			<h2>${Consts.LIST_CLIENTS_TITLE}</h2>
			
			<jsp:include page="${Consts.MENU_JSP}"/>
			
           	<div class="clients">
				<c:if test="${not empty clientsList}">
				<table>
					<thead>
						<tr>
							<th>CPF</th>
							<th>Nome</th>
							<th>Data de Nascimento</th>
							<th>E-mail</th>
							<th>Telefone</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="c" items="${clientsList}">
							<tr>
								<td>${c.cpf}</td>
								<td>${c.name}</td>
								<td>${c.birthDate}</td>
								<td>${c.email}</td>
								<td>${c.phoneNumber}</td>
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
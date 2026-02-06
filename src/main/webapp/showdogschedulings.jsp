<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="br.trcs.petshop.utils.Consts" %>

<!DOCTYPE html>

<html>
	<head>
		<meta charset="UTF-8">
		<title>Cão Q-Late - ${Consts.LIST_DOG_SCHEDULINGS_TITLE}</title>
		
		<link rel="icon" type="image/png" href="img/favicon.ico">
		<link rel="stylesheet" href="css/form.css">
	</head>
	
	<body>
		<jsp:include page="${Consts.MENU_JSP}"/>		
		
		<main>
			<h2>${Consts.LIST_DOG_SCHEDULINGS_TITLE}</h2>
			
			<jsp:include page="${Consts.MESSAGES_JSP}"/>
			
		    <form method="get" action="${Consts.CONTROLLER}">
		        <div class="cpf">
			        <label>CPF do cliente</label>
					<input type="text" name="cpf" value="${param.cpf}" placeholder="123.456.789-00" required>
		        </div>
		        
		        <input type="hidden" name="logic" value="${Consts.LIST_DOGS_LOGIC}">
		        <input type="hidden" name="returnPage" value="${Consts.LIST_DOG_SCHEDULINGS_JSP}">
		        <input type="submit" value="${Consts.LIST_DOGS_BUTTON}">
		    </form>
	
		    <c:if test="${not empty requestScope.dogsList}">
			    <form method="get" action="${Consts.CONTROLLER}">
			        <!-- Lista de cães -->
			        <div class="dogs">
		                <label>Selecione o cão</label>
		                <c:forEach var="d" items="${requestScope.dogsList}">
		                    <label>
						        <input type="radio" name="dog" value="${d.id}" ${param.dog == d.id ? "checked" : ""}>
						        ${d.name}
						    </label>
		                </c:forEach>
		            </div>
			       
			        <!-- Preserva o CPF para a próxima requisição -->
               		<input type="hidden" name="cpf" value="${param.cpf}">
              		
              		<input type="hidden" name="logic" value="${Consts.LIST_DOG_SCHEDULINGS_LOGIC}">
			        <input type="submit" value="${Consts.SELECT_DOG}">
			    </form>
			</c:if>
			
			<!--  Lista de serviços já realizados para o cão selecionado -->
			<c:if test="${not empty finishedList}">								
			    <table>
			        <thead>
			        	<tr>
				        	<th>Data</th>
				        	<th>Serviços</th>
				        	<th>Preço do Serviço</th>
				        	<th>Valor Bruto</th>
				        	<th>Desconto</th>
				        	<th>Valor Final</th>
			        	</tr>
			        </thead>
			       
			        <tbody>
				        <c:forEach var="f" items="${finishedList}">
		                    <tr>
		                        <td>${f.finished.executionDate}</td>
		                        <td>${f.serviceName}</td>
		                        <td>${f.servicePrice}</td>
		                        <td>${f.finished.grossPrice}</td>
		                        <td>${f.finished.discount}</td>
		                        <td>${f.finished.finalPrice}</td>
		                    </tr>
				        </c:forEach>
				    </tbody>
			    </table>
			</c:if>
		</main>
		
	    <jsp:include page="${Consts.FOOTER_JSP}"/>
	</body>
</html>
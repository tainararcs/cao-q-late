<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="br.trcs.petshop.utils.Consts" %>

<!DOCTYPE html>

<html>
	<head>
	    <meta charset="UTF-8">
	    <title>Cão Q-Late - ${Consts.UPDATE_SERVICE_TITLE}</title>
	   
	    <link rel="icon" type="image/png" href="img/favicon.ico">
	    <link rel="stylesheet" href="css/form.css">
	</head>
	
	<body>
	    <jsp:include page="${Consts.MENU_JSP}"/>
	   
	    <main>
		    <h2>${Consts.UPDATE_SERVICE_TITLE}</h2>
			
			<jsp:include page="${Consts.MESSAGES_JSP}"/>
			
		    <form method="get" action="${Consts.CONTROLLER}">
		        <div class="name">
		            <label>Serviço</label>
		            <input type="text" name="name" value="${service.name}" required>
		        </div>
		        
		        <input type="hidden" name="logic" value="${Consts.SEARCH_SERVICE_LOGIC}"> 
		        <input type="submit" value="${Consts.LIST_SERVICES_BUTTON}">
		    </form>
		
		    <c:if test="${not empty service}">
		        <form method="post" action="${Consts.CONTROLLER}">
		            <p class="price">Preço atual: R$ ${service.price}</p>
		
		            <div class="newPrice">
		                <label>Novo preço (R$)</label>
		                <input type="text" name="newPrice" placeholder="50.50" required>
		            </div>
					
					<!-- Preserva o nome do serviço -->
					<input type="hidden" name="name" value="${service.name}">
					
		            <input type="hidden" name="logic" value="${Consts.UPDATE_SERVICE_LOGIC}">
		            <input type="submit" value="${Consts.UPDATE_BUTTON}">
		        </form>
		    </c:if>
		</main>
		
	   <jsp:include page="${Consts.FOOTER_JSP}"/>
	</body>
</html>
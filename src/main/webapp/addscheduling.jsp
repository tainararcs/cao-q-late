<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="br.trcs.petshop.utils.Consts" %>
<%@page import="java.time.LocalDate"%>

<!DOCTYPE html>

<html>
	<head>
	    <meta charset="UTF-8">
	    <title>Cão Q-Late - ${Consts.ADD_SCHEDULING_TITLE}</title>
	   
	    <link rel="icon" type="image/png" href="img/favicon.ico">
	    <link rel="stylesheet" href="css/form.css">
	</head>
	<body>
	    <jsp:include page="${Consts.MENU_JSP}"/>
		
		<main>
		    <h2>${Consts.ADD_SCHEDULING_TITLE}</h2>
			
			<jsp:include page="${Consts.MESSAGES_JSP}"/>
			
		    <!-- Formulário para buscar cães pelo CPF -->
		    <form method="get" action="${Consts.CONTROLLER}">
		    	<div class="cpf">
			        <label>CPF do cliente</label>
			        <input type="text" name="cpf" value="${param.cpf}" required>
		        </div>
		        
		        <input type="hidden" name="logic" value="${Consts.LIST_DOGS_LOGIC}">
		        <input type="hidden" name="returnPage" value="${Consts.ADD_SCHEDULING_JSP}">
		        <input type="submit" value="${Consts.LIST_DOGS_BUTTON}">
		    </form>
		
		    <!-- Só mostra o segundo formulário se houver cães -->
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
		
		            <div class="date">
		                <label>Data do agendamento</label>
		                <input type="date" name="date" value="${param.date}" min="<%= LocalDate.now() %>" required>
		            </div>
					
					<!-- Preserva os dados do cliente para não enviar valor nulo -->
		            <input type="hidden" name="cpf" value="${param.cpf}">
		           
		            <input type="hidden" name="logic" value="${Consts.LIST_SERVICES_LOGIC}">
		            <input type="hidden" name="returnPage" value="${Consts.ADD_SCHEDULING_JSP}">
		            <input type="submit" value="${Consts.LIST_SERVICES_BUTTON}">
		        </form>
		    </c:if>
		    
		    <!-- Serviços -->
		    <c:if test="${not empty servicesList}">
		    	<form method="post" action="${Consts.CONTROLLER}">
		            <div class="services">
		                <label>Selecione os serviços</label>
		                <c:forEach var="s" items="${servicesList}">
		                    <label><input type="checkbox" name="service" value="${s.id}"> ${s.name} - R$ ${s.price}</label>
		                </c:forEach>
		            </div>
		            
		            <!-- Preserva os dados do formulário -->
		            <input type="hidden" name="cpf" value="${param.cpf}">
		            <input type="hidden" name="dog" value="${param.dog}">
		            <input type="hidden" name="date" value="${param.date}">
		            
		            <input type="hidden" name="logic" value="${Consts.ADD_SCHEDULING_LOGIC}">
			        <input type="submit" value="${Consts.SCHEDULE_BUTTON}">
		        </form>
		    </c:if>
	    </main>
	   
		<jsp:include page="${Consts.FOOTER_JSP}"/>
	</body>
</html>

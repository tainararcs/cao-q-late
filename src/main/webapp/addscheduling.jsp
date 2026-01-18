<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="java.util.List" %>
<%@ page import="br.trcs.petshop.utils.Consts" %>
<%@ page import="br.trcs.petshop.model.Dog" %>
<%@ page import="br.trcs.petshop.model.Service" %>
<%@ page import="br.trcs.petshop.dao.ClientDAO" %>
<%@ page import="br.trcs.petshop.dao.ServiceDAO" %>
<%@page import="java.time.LocalDate"%>

<%
   // Carrega a lista de serviços.
   ServiceDAO serviceDAO = new ServiceDAO();
   request.setAttribute("servicesList", serviceDAO.list());
   // Se o CPF foi enviado, busca os cães do cliente.
   String cpf = request.getParameter("client");
   if (cpf != null) {
       cpf = cpf.trim();
       if (!cpf.isEmpty()) {
           ClientDAO clientDAO = new ClientDAO();
           request.setAttribute("dogsList", clientDAO.listDogs(cpf));
       }
   }
%>

<!DOCTYPE html>

<html>
	<head>
	    <meta charset="UTF-8">
	    <title>Cão Q-Late - Agendar Serviços</title>
	   
	    <link rel="icon" type="image/png" href="img/favicon.ico">
	    <link rel="stylesheet" href="css/form.css">
	</head>
	<body>
	    <c:import url="${Consts.MENU}"/>		
		
		<main>
		    <h2>Agendamento de Serviços</h2>
			
			<jsp:include page="messages.jsp"/>
			
		    <!-- Formulário para buscar cães pelo CPF -->
		    <form method="get" action="<%= Consts.ADD_SCHEDULING %>">
		    	<div class="cpf">
			        <label>CPF do cliente</label>
			        <input type="text" name="client" value="${param.client}" required>
		        </div>
		        <input type="submit" value="Buscar cães">
		    </form>
		
		    <!-- Só mostra o segundo formulário se houver cães -->
		    <c:if test="${not empty dogsList}">
		        <form method="post" action="controller">
		           
		            <!-- Lista de cães -->
		            <div class="dogs">
		                <label>Selecione o cão</label>
		                <c:forEach var="d" items="${dogsList}">
		                    <label><input type="radio" name="dog" value="${d.id}"> ${d.name}</label>
		                </c:forEach>
		            </div>
		
		            <div class="date">
		                <label>Data do agendamento</label>
		                <input type="date" name="date" min="<%= LocalDate.now() %>" required>
		            </div>
		
		            <!-- Serviços -->
		            <div class="services">
		                <label>Selecione os serviços</label>
		                <c:forEach var="s" items="${servicesList}">
		                    <label><input type="checkbox" name="service" value="${s.id}"> ${s.name} - R$ ${s.price}</label>
		                </c:forEach>
		            </div>
					
					<!-- Preserva os dados do cliente para não enviar valor nulo -->
		            <input type="hidden" name="client" value="${param.client}">
		           
		            <input type="hidden" name="logic" value="AddScheduling">
		            <input type="submit" value="Agendar Serviço">
		        </form>
		    </c:if>
	    </main>
	   
		<c:import url="${Consts.FOOTER}"/>		
	</body>
</html>

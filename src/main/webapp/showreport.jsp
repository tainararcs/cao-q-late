<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="java.util.*"%>
<%@ page import="java.math.BigDecimal"%>
<%@ page import="br.trcs.petshop.utils.Consts" %>
<%@ page import="br.trcs.petshop.model.Report"%>

<!DOCTYPE html>

<html>
	<head>
	    <meta charset="UTF-8">
	    <title>Cão Q-Late - Relatório de Serviços Prestados</title>
	   
	    <link rel="icon" type="image/png" href="img/favicon.ico">
	    <link rel="stylesheet" href="css/form.css">
	</head>
	
	<body>
		<c:import url="${Consts.MENU}"/>		
		
		<main>
		    <h2>Relatório de Serviços Prestados</h2>
		   
		    <jsp:include page="messages.jsp"/>
		
		    <form method="get" action="controller">
		    	<div class="date">
			    	<label>Data inicial</label>
			        <input type="date" name="start" value="<%= request.getAttribute("start") != null ? request.getAttribute("start") : "" %>" required>
				</div>
				<div class="date">
			        <label>Data final</label>
			        <input type="date" name="end" value="<%= request.getAttribute("end") != null ? request.getAttribute("end") : "" %>" required>
				</div>
				
		        <input type="hidden" name="logic" value="ShowReport">
		        <input type="submit" value="Gerar Relatório">
		    </form>
		
		     <!-- Verifica se a lista de relatórios existe e não está vazia -->
	        <c:if test="${not empty reportList}">
	            <table>
	                <thead>
	                    <tr><th>Data</th><th>Cliente</th><th>Cachorro</th><th>Serviço</th><th>Preço (R$)</th></tr>
	                </thead>
	               
	                <tbody>
	                    <!-- Itera sobre cada relatório -->
	                    <c:forEach var="report" items="${reportList}">
	                        <tr>
	                            <td>${report.date}</td>
	                            <td>${report.clientName}</td>
	                            <td>${report.dogName}</td>
	                            <td>${report.serviceName}</td>
	                            <td>${report.price}</td>
	                        </tr>
	                    </c:forEach>
	                    <tr class="total">
	                        <td colspan="4">Total Faturado</td>
	                        <td>R$ ${totalRevenue}</td>
	                    </tr>
	                </tbody>
	            </table>
	        </c:if>
		</main>
		
		<c:import url="${Consts.FOOTER}"/>	
	</body>
</html>
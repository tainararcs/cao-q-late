<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="br.trcs.petshop.utils.Consts" %>

<!DOCTYPE html>

<html>
	<head>
	    <meta charset="UTF-8">
	    <title>Cão Q-Late - ${Consts.SHOW_REPORT_TITLE}</title>
	   
	    <link rel="icon" type="image/png" href="img/favicon.ico">
	    <link rel="stylesheet" href="css/form.css">
	</head>
	
	<body>
		<jsp:include page="${Consts.MENU_JSP}"/>
		
		<main>
		    <h2>${Consts.SHOW_REPORT_TITLE}</h2>
		   
		    <jsp:include page="${Consts.MESSAGES_JSP}"/>
		
		    <form method="get" action="${Consts.CONTROLLER}">
		    	<div class="date">
			    	<label>Data inicial</label>
			        <input type="date" name="start" value="<%= request.getAttribute("start") != null ? request.getAttribute("start") : "" %>" required>
				</div>
				
				<div class="date">
			        <label>Data final</label>
			        <input type="date" name="end" value="<%= request.getAttribute("end") != null ? request.getAttribute("end") : "" %>" required>
				</div>
				
		        <input type="hidden" name="logic" value="${Consts.SHOW_REPORT_LOGIC}">
		        <input type="submit" value="${Consts.GENERATE_REPORT_BUTTON}">
		    </form>
		
		     <!-- Verifica se a lista de relatórios existe e não está vazia -->
	        <c:if test="${not empty reportList}">
	            <table>
	                <thead>
	                    <tr>
		                    <th>Data</th>
		                    <th>Cliente</th>
		                    <th>Cachorro</th>
		                    <th>Serviço</th>
		                    <th>Preço (R$)</th>
	                    </tr>
	                </thead>
	               
	                <tbody>
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
		
		<jsp:include page="${Consts.FOOTER_JSP}"/>
	</body>
</html>
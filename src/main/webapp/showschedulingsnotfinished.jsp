<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="br.trcs.petshop.utils.Consts" %>

<!DOCTYPE html>

<html>
	<head>
		<meta charset="UTF-8">
	   	<title>Cão Q-Late - ${Consts.LIST_NOT_FINISHED_SCHEDULING_TITLE}</title>
	   	
	   	<link rel="icon" type="image/png" href="img/favicon.ico">
	   	<link rel="stylesheet" href="css/form.css">
	</head>
		
	<body>
		<jsp:include page="${Consts.MENU_JSP}"/>
		
		<main>
			<h2>${Consts.LIST_NOT_FINISHED_SCHEDULING_TITLE}</h2>
			
			<jsp:include page="${Consts.MESSAGES_JSP}"/>
			
			<form method="get" action="${Consts.CONTROLLER}">
		        <div class="date">
			        <label>Data inicial</label>
			        <input type="date" name="date" value="${param.date}" required>
		        </div>
		        
		        <input type="hidden" name="logic" value="${Consts.LIST_NOT_FINISHED_SCHEDULING_LOGIC}">
		        <input type="submit" value="${Consts.LIST_NOT_FINISHED_SCHEDULING_BUTTON}">
		    </form>
		
		    <c:if test="${not empty schedulingList}">
			    <table>
			        <thead>
			            <tr>
			            	<th>ID</th>
				            <th>Data</th>
				            <th>Cão</th>
				            <th>Serviço</th>
				            <th></th>
			            </tr>
			        </thead>
			        <tbody>
						<c:forEach var="s" items="${schedulingList}">
						    <tr>
						        <td>${s.id}</td>
						        <td>${s.date}</td>
						        <td>${s.dogName}</td>
						        <td>${s.services}</td>
						        <td style="text-align: center;">
						        	<c:if test="${s.canCancel}">
							        	<a href="controller?logic=${Consts.DELETE_SCHEDULING_LOGIC}&id=${s.id}&date=${param.date}">
							        		<i class="bi bi-clipboard-x"></i>
							        	</a>
						        	</c:if>
						        </td>
						    </tr>
						</c:forEach>
			        </tbody>
			    </table>
			</c:if>
		</main>
		
		<jsp:include page="${Consts.FOOTER_JSP}"/>
	</body>
</html>
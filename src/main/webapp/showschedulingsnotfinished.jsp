<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="java.time.LocalDate" %>
<%@ page import="java.util.*" %>
<%@ page import="br.trcs.petshop.dao.SchedulingDAO" %>
<%@ page import="br.trcs.petshop.utils.Consts" %>

<%
   String dateParam = request.getParameter("date");
   List<Map<String, Object>> schedulingList = null;
   if (dateParam != null && !dateParam.isEmpty()) {
       LocalDate startDate = LocalDate.parse(dateParam);
       SchedulingDAO dao = new SchedulingDAO();
       schedulingList = dao.listNotFinishedFromDate(startDate);
   }
   request.setAttribute("schedulingList", schedulingList);
   request.setAttribute("now", LocalDate.now());
%>

<!DOCTYPE html>

<html>
	<head>
		<meta charset="UTF-8">
   	<title>Cão Q-Late - Agendamentos não Finalizados</title>
   	
   	<link rel="icon" type="image/png" href="img/favicon.ico">
   	<link rel="stylesheet" href="css/form.css">
	</head>
		
	<body>
		<c:import url="${Consts.MENU}"/>		
		
		<main>
			<h2>Listar Agendamentos não Finalizados</h2>
			
			<jsp:include page="messages.jsp"/>
			
			<form method="get" action="<%= Consts.SHOW_NOT_FINISHED_SCHEDULING %>">
		        <div class="date">
			        <label>Data inicial</label>
			        <input type="date" name="date" value="${param.date}" required>
		        </div>
		        <input type="submit" value="Buscar Agendamentos Pendentes">
		    </form>
		
		    <c:if test="${not empty schedulingList}">
			    <table>
			        <thead>
			            <tr><th>Data</th><th>Cão</th><th>Serviço</th><th></th></tr>
			        </thead>
			        <tbody>
						<c:forEach var="s" items="${schedulingList}">
						    <tr>
						        <td>${s.date}</td>
						        <td>${s.dogName}</td>
						        <td>${s.services}</td>
						        <td style="text-align: center;">
						        	<c:if test="${s.canCancel}"><a href="controller?logic=DeleteScheduling&id=${s.id}&date=${param.date}"><i class="bi bi-clipboard-x"></i></a></c:if>
						        </td>
						    </tr>
						</c:forEach>
			        </tbody>
			    </table>
			</c:if>
		</main>
		
		<c:import url="${Consts.FOOTER}"/>	
	</body>
</html>
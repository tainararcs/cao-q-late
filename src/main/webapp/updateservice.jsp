<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="br.trcs.petshop.utils.Consts" %>
<%@ page import="br.trcs.petshop.dao.ServiceDAO" %>

<%
String name = request.getParameter("name");
if (name != null && !name.isEmpty()) {
    br.trcs.petshop.dao.ServiceDAO dao = new br.trcs.petshop.dao.ServiceDAO();
    pageContext.setAttribute("service", dao.findByName(name));
}
%>

<!DOCTYPE html>

<html>
	<head>
	    <meta charset="UTF-8">
	    <title>Cão Q-Late - Alterar Serviço</title>
	    
	    <link rel="icon" type="image/png" href="img/favicon.ico">
	    <link rel="stylesheet" href="css/form.css">
	</head>
	
	<body>
	    <c:import url="<%= Consts.MENU %>"/>		
	    
	    <main>
		    <h2>Alterar Serviços Prestados</h2>
			
			<jsp:include page="messages.jsp"/>
			
		    <form method="get" action=" <%= Consts.UPDATE_SERVCICE %>">
		        <div class="name">
		            <label>Serviço</label>
		            <input type="text" name="name" value="${service.name}" required>
		        </div>
		        <input type="submit" value="Buscar Serviço">
		    </form>
		
		    <c:if test="${not empty service}">
		        <form method="post" action="controller">
		            <p class="price">Preço atual: R$ ${service.price}</p>
		
		            <div class="newPrice">
		                <label>Novo preço (R$)</label>
		                <input type="text" name="newPrice" placeholder="50.50" required>
		            </div>
					
					<!-- Preserva o nome do serviço -->
					<input type="hidden" name="name" value="${service.name}">
					
		            <input type="hidden" name="logic" value="UpdateService">
		            <input type="submit" value="Alterar Serviço">
		        </form>
		    </c:if>
		</main>
		
	    <c:import url="<%= Consts.FOOTER %>"/>		
	</body>
</html>

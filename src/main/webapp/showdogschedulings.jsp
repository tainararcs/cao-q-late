<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="java.util.List" %>
<%@ page import="br.trcs.petshop.utils.Consts" %>
<%@ page import="br.trcs.petshop.model.Dog" %>
<%@ page import="br.trcs.petshop.dao.ClientDAO" %>
<%@ page import="br.trcs.petshop.dao.DogDAO" %>

<%  
   // Se o CPF foi enviado, busca os cães do cliente.
   String cpf = request.getParameter("cpf");
   if (cpf != null) {
       cpf = cpf.trim();
       if (!cpf.isEmpty()) {
           ClientDAO clientDAO = new ClientDAO();
           request.setAttribute("dogsList", clientDAO.listDogs(cpf));
       }
   }
  
   // Carrega a lista de serviços do cão se o parâmetro dog existir e não for vazio.
   String dog = request.getParameter("dog");
   if (dog != null && !dog.isEmpty()) {
       Integer idDog = Integer.valueOf(dog);
       DogDAO dogDAO = new DogDAO();
       request.setAttribute("finishedList", dogDAO.listFinishedSchedulings(idDog));
   }
%>

<!DOCTYPE html>

<html>
	<head>
		<meta charset="UTF-8">
		<title>Cão Q-Late - Agendamentos do Cão</title>
		
		<link rel="icon" type="image/png" href="img/favicon.ico">
		<link rel="stylesheet" href="css/form.css">
	</head>
	
	<body>
		<c:import url="${Consts.MENU}"/>		
		
		<main>
			<h2>Listar Agendamentos do Cão</h2>
			
			<!-- Formulário para buscar cães pelo CPF -->
		    <form method="get" action="<%= Consts.SHOW_DOG_SCHEDULINGS %>">
		        <div class="cpf">
			        <label>CPF do cliente</label>
					<input type="text" name="cpf" value="${param.cpf}" placeholder="123.456.789-00" required>
		        </div>
		        <input type="submit" value="Buscar cães">
		    </form>
	
		    <c:if test="${not empty dogsList}">
			    <form method="get" action="<%= Consts.SHOW_DOG_SCHEDULINGS %>">
			        <!-- Lista de cães -->
			        <div class="dogs">
			            <label>Selecione o cão</label>
			            <c:forEach var="d" items="${dogsList}">
			                <label>
			                    <input type="radio" name="dog" value="${d.id}" <c:if test="${param.dog == d.id}">checked</c:if> />
			                    ${d.name}
			                </label>
			            </c:forEach>
			        </div>
			       
			        <!-- Preserva o CPF para a próxima requisição -->
               	<input type="hidden" name="cpf" value="${param.cpf}">
              
			        <input type="submit" value="Selecionar cão">
			    </form>
			</c:if>
			
			<!--  Lista de serviços já realizados para o cão selecionado -->
			<c:if test="${not empty finishedList}">								
			    <table>
			        <thead>
			        	<tr><th>Data</th><th>Serviços</th><th>Preço do Serviço</th><th>Valor Bruto</th><th>Desconto</th><th>Valor Final</th></tr>
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
		
	    <c:import url="${Consts.FOOTER}"/>
	</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    
    <!-- Bootstrap Icons -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css">
    <link rel="stylesheet" type="text/css" href="css/menu.css">
  </head>
  
  <body>
  
    <header>
	    <div class="logo"><span class="paw">🐾</span> Cão Q-Late</div>
	
	    <nav>
	    	<ul>
				<li><a href="home.jsp" class="home-icon"><i class="bi bi-house-door"></i></a></li>
				
				<li class="dropdown">
				   <a href="#">Clientes ▾</a>
				   <ul class="dropdown-menu">
				     <li><a href="controller?logic=redirect:addclient.jsp"><i class="bi bi-person-add"></i> Cadastrar</a></li>
				     <li><a href="controller?logic=redirect:listclients.jsp"><i class="bi bi-list-ul"></i> Listar</a></li>
				   </ul>
				</li>
				
				<li class="dropdown">
				   <a href="#">Cães ▾</a>
				   <ul class="dropdown-menu">
				     <li><a href="controller?logic=redirect:adddog.jsp"><i class="bi bi-heart"></i> Cadastrar</a></li>
				     <li><a href="controller?logic=redirect:listdogs.jsp"><i class="bi bi-list-ul"></i> Listar</a></li>
				   </ul>
				</li>
				
				<li class="dropdown">
				   <a href="#">Serviços ▾</a>
				   <ul class="dropdown-menu">
				     <li><a href="controller?logic=redirect:addservice.jsp"><i class="bi bi-clipboard-check"></i> Cadastrar</a></li>
				     <li><a href="controller?logic=redirect:listservices.jsp"><i class="bi bi-list-ul"></i> Listar</a></li>
				     <li><a href="controller?logic=redirect:updateservice.jsp"><i class="bi bi-pencil-square"></i> Alterar</a></li>
				   </ul>
				</li>
				
				 <li class="dropdown">
				   <a href="#">Agendamentos ▾</a>
				   <ul class="dropdown-menu">
				     <li><a href="controller?logic=redirect:addscheduling.jsp"><i class="bi bi-calendar-plus"></i> Cadastrar</a></li>
				     <li><a href="controller?logic=redirect:addfinishedscheduling.jsp"><i class="bi bi-calendar-check"></i> Finalizar</a></li>
				     <li><a href="controller?logic=redirect:showdogschedulings.jsp"><i class="bi bi-list-ul"></i> Por Cão</a></li>
				     <li><a href="controller?logic=redirect:showschedulingsnotfinished.jsp"><i class="bi bi-clock-history"></i> Não Finalizados</a></li>
				   </ul>
				</li>
				
				<li><a href="controller?logic=redirect:showreport.jsp">Relatório</a></li>
				
				<li><a href="controller?logic=Logout" class="logout"><i class="bi bi-box-arrow-right"></i></a></li>
	    	</ul>
	  	</nav>
  
	</header>
    
  </body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="br.trcs.petshop.utils.Consts" %>

<!DOCTYPE html>

<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		
		<title>Erro - PetShop</title>
		<link rel="icon" type="image/png" href="img/favicon.ico">
		<link rel="stylesheet" href="css/error.css">
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
	</head>

	<body>
		<div class="floating-shapes">
			<div class="shape"></div>
			<div class="shape"></div>
		</div>

		<div class="error-container">
			<div class="error-icon">
				<i class="fa-solid fa-paw"></i>
			</div>
			
			<h1 class="error-code">Oops!</h1>
			<h2 class="error-message">Algo deu errado</h2>
			
			<p class="error-description">
				A página ou recurso que está tentando acessar não existe
			</p>

			<div class="error-actions">
				<a href="${Consts.CONTROLLER}?logic=${Consts.REDIRECT_HOME_JSP}" class="btn btn-primary">
					<i class="fa-solid fa-house"></i> Página Inicial
				</a>
			</div>
		</div>
	</body>
</html>
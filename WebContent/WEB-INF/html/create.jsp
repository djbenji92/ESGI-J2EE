<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:directive.page import="fr.model.User" />
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>Partage de photo</title>
	<link href="styles/index.css" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="//fonts.googleapis.com/css?family=Open+Sans" />
	</head>
	<body>
	
	<div class="wrapper">
		<div class="mock-up">
			<img src="img/iphone.png">
		</div>
		
		<div class="content">
			<div class="connexion">
				<div class="padding">
					<h1>YourPhoto</h1>
					<h3>Inscrivez-vous et découvrez notre application permettant le partage de photo</h3>
					<h2>Connectez-vous</h2>
					<form method="get" action="login">
						<input type="text" name="user" placeholder="Nom d'utilisateur">
						<br>
						<input name="password" type="password" placeholder="Mot de passe">
						<br>
						<button type="submit">Se connecter</button>
					</form>
					
					<h2>Inscrivez vous</h2>
					<form method="get" action="create">
						<input type="text" name="mailInscription"  placeholder="Adresse email">
						<br>
						<input type="text" name="nameInscription" placeholder="Nom complet">
						<br>
						<input type="text" name="userInscription" placeholder="Nom d'utilisateur">
						<br>
						<input type="password" name="passwordInscription" placeholder="Mot de passe">
						<br>
						<button type="submit">S'inscrire</button>
					</form>
					<div class="error">
						<% if(request.getAttribute("errorMessage") != null){ %>
								<p>${errorMessage}<p>
						<% } 
						else if(request.getAttribute("success") != null){ %>
							<p>L'utilisateur est connecté</p>
						<% } %>
					</div>
					
				</div>
			</div>
		</div>
	</div>
	
	</body>
</html>
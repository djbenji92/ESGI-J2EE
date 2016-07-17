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
	<link href='https://fonts.googleapis.com/css?family=Oswald' rel='stylesheet' type='text/css'>
	
	</head>
	<body>
	
	<div class="wrapper">
		<div class="mock-up">
			<img src="img/mockup_acceuil.png">
		</div>
		
		<div class="content">
			<div class="connexion">
				<div class="padding">
					<div class="logo">
					<img src="img/LogoJ2EE.png">
					</div>
					<h3>Cher Bikers ! Inscrivez-vous, Découvrez & Partagez vos plus belles photos.</h3>
					<h2>Vous avez un compte ? Connectez-vous !</h2>
					<form method="get" action="login">
						<input type="text" name="user" placeholder="Nom d'utilisateur">
						<br>
						<input name="password" type="password" placeholder="Mot de passe">
						<br>
						<button type="submit">Se connecter</button>
					</form>
					<div class="trait"></div>
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
	<footer>
  		
	</footer>
	</body>
</html>
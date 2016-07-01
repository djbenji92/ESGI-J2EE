<jsp:directive.page contentType="text/html; charset=UTF-8" />
	<header class="header">
		<h2>SharePhoto</h2>
		<ul class="navigation">
			<form action="recherche" type="GET" >
				<input name="search" placeholder="Recherche...">
				<button type="submit">GO</button>
			</form>
			<li><a href="publierImage">Publier...</a></li>
			<li><a href="mes-images">Mes posts</a></li>
			<li><a href="#">Profil</a></li>
			<li><a href="logout">Déconnexion</a></li>
		</ul>
	</header>
<jsp:directive.page contentType="text/html; charset=UTF-8" />
<link href="styles/home.css" rel="stylesheet">

	<header class="header">
		<a href="home">
		<h2>Motorcycles Club</h2>
		</a>

		<ul class="navigation">
			<form action="recherche" type="GET" >
				<input name="search" placeholder="Recherchez...">
			</form>
			<li><a href="publierImage"><img src="img/telechargement.png"></a></li>
			<li><a href="mes-images"><img src="img/photo.png"></a></li>
			<li><a href="mes-favoris"><img src="img/like.png"></a></li>
			 <%--<li><a href="#"><img src="img/user.png"></a></li>--%>
			<li><a href="logout"><img src="img/power-signal.png"></a></li>
		</ul>
		

		
	</header>
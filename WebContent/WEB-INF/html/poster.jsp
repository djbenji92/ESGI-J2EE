<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>Partage de photo</title>
	<link href="styles/publier.css" rel="stylesheet">
	<link href="styles/home.css" rel="stylesheet">
	</head>
	<body>
	
	<div class="wrapper">
	
	<jsp:directive.include file="directives/navBar.jsp" />
	  <article class="main">
	    <div class="content">	
	    	<div class="publier">
		    	<h1>Publier une nouvelle image :</h1>
				<form class="form-publish" method="post" action="uploader"  enctype="multipart/form-data">
					<input name="titre" placeholder="Titre de la publication">
					<input name="hashtag" placeholder="Hashtags">
					<input type="file" name="file" size="60"/>
					<input type="text" value="/tmp" name="destination"/>
					<button type="submit">Publier</button>
				</form>
	    	</div>
		</div> 
	  </article>
	  <footer class="footer">Footer</footer>
	</div>
	
	</body>
</html>
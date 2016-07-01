<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="fr.model.*" %>
<jsp:directive.page import="java.util.List" />
<%
String user = (String) request.getSession().getAttribute("userSession");
if(user == null){
	
	System.out.println("utilisateur pas connecté");
	response.sendRedirect("login");
}
else{
	System.out.println("utilisateur connecté");
}


%>

<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>Partage de photo</title>
	<link href="styles/home.css" rel="stylesheet">
	</head>
	<body>
	
	<div class="wrapper">
	
	<jsp:directive.include file="directives/navBar.jsp" />
	  <article class="main">
	    <div class="content">
	    	
	    	<div class="post">
	    	<% 
			String id = request.getParameter("id");
			//out.println( "Image : " + id );
			%>
			
			<% List<Post> allPosts = (List<Post>)request.getAttribute("getPost2");
					for(Post p : allPosts){ %>
					<div class="post-image">
						<p>Intitulé de la publication : <% out.write(p.getTitre()); %></p>
						<p>Posté par : <%  out.write(p.getLogin()); %> </p>
						<p>Hashtag : <%  out.write(p.getHashtag()); %> </p>
						<img src="imgUpload/<% out.write(p.getImage()); %>" >
						
					</div>
					
				<% } %>
				
				<a href="home">Retour</a>
				
				<h2>Commentaires </h2>
				<form action="publierCommentaire" type="post">
					<textarea rows="4" col="50"></textarea>
					<button type="submit">Envoyer commentaire</button>
				</form>
			
	    	</div>			
		</div> 
	  </article>
	  <footer class="footer">Footer</footer>
	</div>
	
	</body>
</html>
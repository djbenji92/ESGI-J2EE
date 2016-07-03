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
						<a href="home">Retour</a>
					</div>
					
				<% } %>
				
				
				
				<h2 class="600 center">Poster un commentaire : </h2>
				<form class="publier-comments" action="publierCommentaire" type="post">
					<textarea name="comment" rows="4" col="50"></textarea>
					<input name="id" value="<% out.write(id); %>">
					<button type="submit">Envoyer commentaire</button>
				</form>
				
	    	
				
				<h2 class="600 center">Commentaires </h2>
				<div class="comment">
					<h2>Michel</h2>
					<p>Lorem ipius bbbbbla bla bla bla bla bla bla  bla bla bla bla bla blabl al bbbb</p>
				</div>
				<div class="separator"></div>
				<div class="comment">
					<h2>Michel</h2>
					<p>Lorem ipius bbbbbla bla bla bla bla bla bla  bla bla bla bla bla blabl al bbbb</p>
				</div>
				<div class="separator"></div>
				
				<% List<Comment> allCommentByImage = (List<Comment>)request.getAttribute("getComments");
					for(Comment m : allCommentByImage){ %>
						<div class="comment">
							<h2><%  out.write(m.getIdUser()); %></h2>
							<p><%  out.write(m.getText()); %></p>
							<p><%  out.write(m.getDate()); %></p>
						</div>
						<div class="separator"></div>
					
					
				<% } %>
	    	</div>			
		</div> 
	  </article>
	  <footer class="footer">Footer</footer>
	</div>
	
	</body>
</html>
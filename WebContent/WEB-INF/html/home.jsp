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
	    	<p>Connecté sous : <% if(user != null) {out.write(user);} %></p>
	    	
	    	<div class="actualite">
	    		
				
				<% List<Post> allPosts = (List<Post>)request.getAttribute("postList");
					for(Post p : allPosts){ %>
		    		<div class="bloc">
		    			<div class="info-profil">
		    				<p class="user-bloc"><% out.write(p.getLogin()); %></p>
		    			</div>
		    			<div class="content-bloc">
		    				
		    				<% out.write("<a href='image?id="+p.getId() + "'><img src='imgUpload/" + p.getImage() + "'></a>"); %>
		    				<p>Tag : <% out.write(p.getHashtag()); %></p>
		    			</div>
					</div>
				<% } %>
				
				
				
	    	</div>
	    	
	    	
			
		</div> 
	  </article>
	  <footer class="footer">Footer</footer>
	</div>
	
	</body>
</html>
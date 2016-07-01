<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:directive.page import="fr.model.User" />
<jsp:directive.page import="java.util.List" />
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
		
	</div>
	<table>
		<thead>
			<tr>
				<th>Utilisateur</th>
				<th>Password</th>
			</tr>
		</thead>
		<tbody>
			<% List<User> allUsers = (List<User>)request.getAttribute("userList");
			for(User u : allUsers){ %>
			<tr>
				<td><% out.write(u.getLogin() + "<br />");  %></td>
				<td><% out.write(u.getPassword()); %></td>
			</tr>
			<% } %>
		</tbody>
	</table>
	
	</body>
</html>
package fr.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.sql.PreparedStatement;
import java.sql.Statement;

public class PostManagerSQL implements IPostManager {
	
	private Connection connection;
	
	public PostManagerSQL(){
		try {
			this.connection =  this.getConnexion();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Connection getConnexion() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
	Connection connection = null;
	String url = "jdbc:mysql://localhost:8889/jee";
	Class.forName("com.mysql.jdbc.Driver").newInstance();
	connection = DriverManager.getConnection(url, "root", "root");
	
	return connection;
	}
	
	@Override
	public boolean insertPost(String titre, String hashtag, String image, String user) {
		PreparedStatement stmt = null;
		int result = 0;
		try {
			
			String userSQL = "INSERT INTO posts(titrePost, hashtagPost, imagePost, idUser) VALUES(?, ?, ?, ?)";
			stmt = this.connection.prepareStatement(userSQL);
			
			stmt.setString(1, titre);
			stmt.setString(2, hashtag);
			stmt.setString(3, image);
			stmt.setString(4, user);
			
			result = stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return result == 1;
	}
	
	@Override
	public boolean imageDontExist(String image) {
		
			int result = 0;
			PreparedStatement stmt = null;
			ResultSet rs= null;
			try {
				//stmt = this.connection.createStatement();
				String query = "SELECT * FROM posts WHERE imagePost = ?";
				
				stmt = this.connection.prepareStatement(query);
				stmt.setString(1, image);
				rs = stmt.executeQuery();
				while(rs.next()){
					result = result + 1;
				}
				rs.close();
			} catch(SQLException e){
				e.printStackTrace();
			}
			System.out.println("RESULT : " + result);
			if(result > 0){
				return false;
			}
			else{
				return true;
			}
		
	}

	@Override
	public List<Post> allPosts() {
		// TODO Auto-generated method stub
		List<Post> postList = new ArrayList<>();
		Statement stmt = null;
		ResultSet rs= null;
		try {
			stmt = this.connection.createStatement();
			String PostSQL = "SELECT * FROM posts ORDER BY IdPost DESC";
			
			rs = stmt.executeQuery(PostSQL);
			while(rs.next()){
				Integer id = rs.getInt("idPost");
				String titre = rs.getString("titrePost");
				String image = rs.getString("imagePost");
				String hashtag = rs.getString("hashtagPost");
				String user = rs.getString("idUser");
				Post newPost = new Post(id, titre, image, hashtag, user);
				postList.add(newPost);
				System.out.println(id);
			}
			rs.close();
		} catch(SQLException e){
			e.printStackTrace();
		}
		
		return postList;
	}

	@Override
	public List<Post> getPost2(Integer id) {
		// TODO Auto-generated method stub
		List<Post> postList = new ArrayList<>();
		PreparedStatement stmt = null;
		ResultSet rs= null;
		
		try {
			String PostSQL = "SELECT * FROM posts WHERE idPost = ?";
			stmt = this.connection.prepareStatement(PostSQL);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			while(rs.next()){
				Integer idPost = rs.getInt("idPost");
				String titre = rs.getString("titrePost");
				String image = rs.getString("imagePost");
				String hashtag = rs.getString("hashtagPost");
				String user = rs.getString("idUser");
				Post newPost = new Post(id, titre, image, hashtag, user);
				postList.add(newPost);
				System.out.println(id);
				
			}
			rs.close();
		} catch(SQLException e){
			e.printStackTrace();
		}
		
		return postList;
	}
	
	@Override
	public Post getPost(Integer id) {
		// TODO Auto-generated method stub
		Post newPost = new Post(0, "", "", "", "");
		PreparedStatement stmt = null;
		ResultSet rs= null;
		
		try {
			String PostSQL = "SELECT * FROM posts WHERE idPost = ?";
			stmt = this.connection.prepareStatement(PostSQL);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			while(rs.next()){
				Integer idPost = rs.getInt("idPost");
				String titre = rs.getString("titrePost");
				String image = rs.getString("imagePost");
				String hashtag = rs.getString("hashtagPost");
				String user = rs.getString("idUser");
				newPost = new Post(idPost, titre, image, hashtag, user);
				
			}
			rs.close();
		} catch(SQLException e){
			e.printStackTrace();
		}
		
		return newPost;
	}
	
	//
	public List<Post> allPostsProfil(String login) {
		// TODO Auto-generated method stub
		List<Post> postList = new ArrayList<>();
		PreparedStatement stmt = null;
		ResultSet rs= null;
		
		try {
			String PostSQL = "SELECT * FROM posts WHERE idUser = ? ORDER BY idPost DESC";
			stmt = this.connection.prepareStatement(PostSQL);
			stmt.setString(1, login);
			rs = stmt.executeQuery();
			while(rs.next()){
				Integer idPost = rs.getInt("idPost");
				String titre = rs.getString("titrePost");
				String image = rs.getString("imagePost");
				String hashtag = rs.getString("hashtagPost");
				String user = rs.getString("idUser");
				Post newPost = new Post(idPost, titre, image, hashtag, user);
				postList.add(newPost);
			}
			rs.close();
		} catch(SQLException e){
			e.printStackTrace();
		}
		
		return postList;
	}
	
	@Override
	public List<Post> doSearch(String search) {
		// TODO Auto-generated method stub
		List<Post> postList = new ArrayList<>();
		PreparedStatement stmt = null;
		ResultSet rs= null;
		
		try {
			String PostSQL = "SELECT * from posts WHERE titrePost LIKE ? OR hashtagPost LIKE ? OR idUser LIKE ?";
			//String PostSQL = "SELECT * FROM posts WHERE idPost = ?";
			stmt = this.connection.prepareStatement(PostSQL);
			stmt.setString(1, search);
			stmt.setString(2, search);
			stmt.setString(3, search);
			rs = stmt.executeQuery();
			while(rs.next()){
				Integer idPost = rs.getInt("idPost");
				String titre = rs.getString("titrePost");
				String image = rs.getString("imagePost");
				String hashtag = rs.getString("hashtagPost");
				String user = rs.getString("idUser");
				Post newPost = new Post(idPost, titre, image, hashtag, user);
				postList.add(newPost);
				System.out.println(idPost);
				
			}
			rs.close();
		} catch(SQLException e){
			e.printStackTrace();
		}
		
		return postList;
	}
	

}

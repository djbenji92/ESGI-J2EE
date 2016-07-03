package fr.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.text.DateFormat;

public class FavorisManagerSQL implements IFavorisManager {
	
	private Connection connection;
	
	public FavorisManagerSQL(){
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
	public boolean insertFavoris(Integer id,  String user) {
		PreparedStatement stmt = null;
		int result = 0;
		try {
			
			
			String userSQL = "INSERT INTO favoris( idPost, idUser) VALUES(?, ?)";
			stmt = this.connection.prepareStatement(userSQL);
			
			stmt.setInt(1, id);
			stmt.setString(2, user);
			
			result = stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return result == 1;
	}
	
	@Override
	public boolean deleteFavoris(Integer id, String user) {
		PreparedStatement stmt = null;
		int result = 0;
		try {
			
			
			String userSQL = "DELETE FROM favoris WHERE idPost = ? AND idUser = ?";
			stmt = this.connection.prepareStatement(userSQL);
			
			stmt.setInt(1, id);
			stmt.setString(2, user);
			
			result = stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return result == 1;
	}
	
	public List<Post> allPostsFavoris(String login) {
		// TODO Auto-generated method stub
		List<Post> postList = new ArrayList<>();
		PreparedStatement stmt = null;
		ResultSet rs= null;
		List<Integer> listeFavoriesImages = new ArrayList<>();
		
		try {
			//chercher les images favorites
			String FavorisSQL = "SELECT * FROM favoris WHERE idUser = ? ORDER BY idPost DESC";
			stmt = this.connection.prepareStatement(FavorisSQL);
			stmt.setString(1, login);
			rs = stmt.executeQuery();
			while(rs.next()){
				Integer idPost = rs.getInt("idPost");
				System.out.println(idPost);
				listeFavoriesImages.add(idPost);
				//ajouter dans un tableau
				
			}
			rs.close();
			
			for(Integer i : listeFavoriesImages){ 
				String PostSQL = "SELECT * FROM posts WHERE idUser = ? AND idPost = ? ORDER BY idPost DESC";
				stmt = this.connection.prepareStatement(PostSQL);
				stmt.setString(1, login);
				stmt.setInt(2, i);
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
			}

			
		} catch(SQLException e){
			e.printStackTrace();
		}
		
		return postList;
	}
	
	public boolean verifImageFavoris(Integer id){
		
		Integer nbFavoris = 0;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			
			String userSQL = "SELECT * FROM favoris WHERE idPost = ?";
			stmt = this.connection.prepareStatement(userSQL);
			stmt.setInt(1, id);
			
			rs = stmt.executeQuery();
			while(rs.next()){
				nbFavoris = nbFavoris + 1;
				
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		
		if(nbFavoris > 0){
			return true;
		}
		else{
			return false;
		}
		
	}
}

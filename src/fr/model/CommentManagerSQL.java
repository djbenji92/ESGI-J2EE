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

public class CommentManagerSQL implements ICommentManager {
	
	private Connection connection;
	
	public CommentManagerSQL(){
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
	public boolean insertComment(Integer id, String comment, String user, String date) {
		PreparedStatement stmt = null;
		int result = 0;
		try {
			
			
			String userSQL = "INSERT INTO comments(textComment, dateComment, idPost, idUser) VALUES(?, ?, ?, ?)";
			stmt = this.connection.prepareStatement(userSQL);
			
			stmt.setString(1, comment);
			stmt.setString(2, date);
			stmt.setInt(3, id);
			stmt.setString(4, user);
			
			result = stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return result == 1;
	}
	
	public List<Comment> allCommentByImage(Integer id) {
		// TODO Auto-generated method stub
		List<Comment> postComment = new ArrayList<>();
		PreparedStatement stmt = null;
		ResultSet rs= null;
		
		try {
			String PostSQL = "SELECT * FROM comments WHERE idPost = ? ORDER BY idComment DESC";
			stmt = this.connection.prepareStatement(PostSQL);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			while(rs.next()){
				Integer idComment = rs.getInt("idComment");
				String textComment = rs.getString("textComment");
				String date = rs.getString("dateComment");
				String idUser = rs.getString("idUser");
				Integer idPost = rs.getInt("idPost");
				Comment newPost = new Comment(idComment, textComment, date, idUser, idPost);
				postComment.add(newPost);
			}
			rs.close();
		} catch(SQLException e){
			e.printStackTrace();
		}
		
		return postComment;
	}
	
}

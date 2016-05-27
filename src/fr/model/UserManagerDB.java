package fr.model;

import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.BulkWriteOperation;
import com.mongodb.BulkWriteResult;
import com.mongodb.Cursor;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.ParallelScanOptions;
import com.mongodb.ServerAddress;


import java.sql.PreparedStatement;
import java.sql.Statement;

public class UserManagerDB implements IUserManager {
	
	private Connection connection;
	
	public boolean testRequete(String login, String password){
		boolean connectOK = false;
		
		MongoClient mongoClient = new MongoClient( "localhost" , 27017 ); 
		
		DB db = mongoClient.getDB( "projet_java" );
			
		DBCollection coll = db.getCollection("User");
		
		BasicDBObject doc = new BasicDBObject("login", login)
		        .append("password", password);
		coll.insert(doc);
		
		return connectOK;
	}
	

	public Connection getConnexion() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
	Connection connection = null;
	String url = "jdbc:mysql://localhost:8889/esgi";
	Class.forName("com.mysql.jdbc.Driver").newInstance();
	connection = DriverManager.getConnection(url, "root", "root");
	
	return connection;
	}
	
	@Override
	public boolean checkLogin(String login) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean checkLoginWithPassword(String login, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean createUser(String login, String password) {
		// TODO Auto-generated method stub
		java.sql.PreparedStatement stmt = null;
		int result = 0;
		try{
			String userSQL = "INSERT INTO users(login, password) VALUE(?, ?)";
			stmt = this.connection.prepareStatement(userSQL);
			
			stmt.setString(1, login);
			stmt.setString(2, password);
			
			result = stmt.executeUpdate();
			
			stmt.close();
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		
		return result == 1;
	}
	
	public Boolean deleteUser(String login){
		PreparedStatement stmt = null;
		
		int resultat = 0;
		try{
			String deleteUserSQL = "DELETE FROM users WHERE login = ?";
			stmt = (PreparedStatement) this.connection.prepareStatement(deleteUserSQL);
			stmt.setString(1, login);
			
			resultat = stmt.executeUpdate();
			stmt.close();
		} catch(SQLException e){
			e.printStackTrace();
		}
		return resultat == 1;
	}

	@Override
	public List<User> allUsers() {
		// TODO Auto-generated method stub
		List<User> userList = new ArrayList<>();
		Statement stmt = null;
		ResultSet rs= null;
		try {
			stmt = this.connection.createStatement();
			String userSQL = "SELECT * FROM users";
			
			rs = stmt.executeQuery(userSQL);
			while(rs.next()){
				String login = rs.getString("login");
				String password = rs.getString("password");
				User newUser = new User(login, password);
				userList.add(newUser);
			}
			rs.close();
		} catch(SQLException e){
			e.printStackTrace();
		}
		
		return userList;
	}

}


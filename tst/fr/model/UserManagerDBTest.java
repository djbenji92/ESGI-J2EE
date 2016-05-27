package fr.model;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;

public class UserManagerDBTest {

	private UserManagerDB dbManager;
	
	@Before
	public void setUp() throws Exception{
		dbManager = new UserManagerDB();
	}
	
	public void tearDown() throws Exception {
		
	}
	@Test
	public void testConnection() {
		
		Connection conn;
		
		try {
			conn = dbManager.getConnexion();
			Assert.assertNotNull("Db connection should not be null", conn);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			Assert.fail("DB connection create should not thrown an exception");
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCreateUser(){
		Boolean userCreated = dbManager.createUser("user2", "pass");
		Assert.assertTrue("Should be able to create a user", userCreated);
		
		Boolean userDelete = dbManager.deleteUser("user2");
		Assert.assertTrue("Should be able to create a user", userDelete);
	}
	
	@Test
	public void testGetAllUsers(){
		dbManager.createUser("test_user1", "mdp");
		List<User> userList = dbManager.allUsers();
		
		Assert.assertNotNull("The list should not be null", userList);
		Assert.assertFalse("The list should have at least one user", userList.isEmpty());
		
		Boolean userFound = false;
		for(User user : userList){
			if(user.getLogin().equals("test_user1") &&
					user.getPassword().equals("mdp")){
				userFound = true;
				break;
			}
		}
		Assert.assertTrue("We should've found the user we just created", userFound);
		dbManager.deleteUser("test_user1");
	}
	
	@Test
	public void testCheckLogin(){
		dbManager.createUser("test_user1", "mdp");
		
		Boolean userChecked = dbManager.checkLogin("test_user1");
		Assert.assertTrue("ne trouve pas l'utilisateur", userChecked);
		
		dbManager.deleteUser("test_user1");
	}
	
	

}

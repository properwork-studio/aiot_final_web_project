package com.project.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.project.model.User;

public class UserDAO {
	String url = "jdbc:mysql://localhost:3306/test_users?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=CST";
    String driver = "com.mysql.jdbc.Driver";
    String userName = "user";
    String password = "lomo81818";

	private static final String INSERT_USERS_SQL = "INSERT INTO Users" + "  (user_email, user_password, username, phone_number) VALUES "
			+ " (?, ?, ?, ?);";

	private static final String SELECT_USER_BY_ID = "SELECT * FROM Users WHERE user_id =?";
	private static final String SELECT_USER_BY_EMAIL = "SELECT user_id, user_email, user_password, username, phone_number FROM Users WHERE user_email = ?";
	private static final String SELECT_ALL_USERS = "SELECT * FROM Users";
//	private static final String DELETE_USERS_SQL = "DELETE FROM Users WHERE user_id = ?;";
//	private static final String UPDATE_USERS_SQL = "UPDATE Users SET nickname = ?,email= ?, introduction =?, user_image =? WHERE user_id = ?;";
	
	public UserDAO() {
		
	}
	
	protected Connection getConnection() {
//		System.out.println("MySQL JDBC Example.");
		Connection con = null;
    	try {
    		Class.forName(driver);
    		con = DriverManager.getConnection(url, userName, password);
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
		return con;
	}
	
//	public void insertUser(Connection con, User user) throws SQLException {
	public void insertUser(User user) throws SQLException {
		System.out.println(INSERT_USERS_SQL);
		System.out.println(user.getEmail());
		System.out.println(user.getPassword());
		System.out.println(user.getName());
		System.out.println(user.getPhoneNumber());
		// try-with-resource statement will auto close the connection.
		try (
			Connection con = getConnection();
			PreparedStatement preparedStatement = con.prepareStatement(INSERT_USERS_SQL)) {
			preparedStatement.setString(1, user.getEmail());
			preparedStatement.setString(2, user.getPassword());
			preparedStatement.setString(3, user.getName());
			preparedStatement.setString(4, user.getPhoneNumber());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}
	
//	public User selectUserById(Connection con, int id) {
	public User selectUserById(int id) {
		User user = null;
		try (
			Connection con = getConnection();
			PreparedStatement preparedStatement = con.prepareStatement(SELECT_USER_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			// Step: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step: Process the ResultSet object.
			while (rs.next()) {
				String userEmail = rs.getString("user_email");
				String userPassword = rs.getString("user_password");
				String userName = rs.getString("username");
				String phoneNumber = rs.getString("phone_number");
				user = new User(id, userEmail, userPassword, userName, phoneNumber);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return user;
	}
	
//	public User selectUserByEmail(Connection con, String email) {
	public User selectUserByEmail(String email) {
		User user = null;
		try (
			Connection con = getConnection();
			PreparedStatement preparedStatement = con.prepareStatement(SELECT_USER_BY_EMAIL);) {
			preparedStatement.setString(1, email);
			System.out.println(preparedStatement);
			// Step: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("user_id");
				String userEmail = rs.getString("user_email");
				String userPassword = rs.getString("user_password");
				String userName = rs.getString("username");
				String phoneNumber = rs.getString("phone_number");
				user = new User(id, userEmail, userPassword, userName, phoneNumber);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return user;
	}
	
//	public List<User> selectAllUsers(Connection con) {
	public List<User> selectAllUsers() {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<User> users = new ArrayList<>();
		try (
			// Step :Create a statement using connection object
			Connection con = getConnection();
			PreparedStatement preparedStatement = con.prepareStatement(SELECT_ALL_USERS);) {
			System.out.println(preparedStatement);
			// Step : Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step : Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("user_id");
				String userEmail = rs.getString("user_email");
				String userPassword = rs.getString("user_password");
				String userName = rs.getString("username");
				String phoneNumber = rs.getString("phone_number");
				users.add(new User(id, userEmail, userPassword, userName, phoneNumber));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return users;
	}
	
	
	
	
//  ================================================================================================  //
	
	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}
}

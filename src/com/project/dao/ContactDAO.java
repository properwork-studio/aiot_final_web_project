package com.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.project.model.Contact;

public class ContactDAO {

	private static final String INSERT_CONTACT_SQL = "INSERT INTO contact" + "  (contact_name, relationship, email, phone_number) VALUES "
			+ " (?, ?, ?, ?);";

	private static final String SELECT_CONTACT_BY_ID = "SELECT * FROM contact WHERE contact_id =?";
//	private static final String SELECT_USER_BY_USERNAME = "SELECT user_id,username,user_password,email,nickname,introduction,user_image FROM Users WHERE username =?";
	private static final String SELECT_ALL_CONTACT = "SELECT * FROM contact";
//	private static final String DELETE_USERS_SQL = "DELETE FROM Users WHERE user_id = ?;";
	private static final String UPDATE_CONTACT_SQL = "UPDATE contact SET contact_name = ?, relationship = ?, email = ?, phone_number = ? WHERE contact_id = ?;";
	
	public ContactDAO() {
		
	}
	
	public void insertContact(Connection con, Contact contact) throws SQLException {
		System.out.println(INSERT_CONTACT_SQL);
		System.out.println(contact.getContactName());
		System.out.println(contact.getRelationship());
		System.out.println(contact.getEmail());
		System.out.println(contact.getPhoneNumber());
		// try-with-resource statement will auto close the connection.
		try (
			PreparedStatement preparedStatement = con.prepareStatement(INSERT_CONTACT_SQL)) {
			preparedStatement.setString(1, contact.getContactName());
			preparedStatement.setString(2, contact.getRelationship());
			preparedStatement.setString(3, contact.getEmail());
			preparedStatement.setString(4, contact.getPhoneNumber());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}
	
	public Contact selectContactById(Connection con, int id) {
		Contact contact = null;
		try (
			PreparedStatement preparedStatement = con.prepareStatement(SELECT_CONTACT_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			// Step: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step: Process the ResultSet object.
			while (rs.next()) {
				String contactName = rs.getString("contact_name");
				String relationship = rs.getString("relationship");
				String email = rs.getString("email");
				String phoneNumber = rs.getString("phone_number");
				contact = new Contact(id, contactName, relationship, email, phoneNumber);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return contact;
	}
	
	public List<Contact> selectAllContact(Connection con) {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<Contact> contacts = new ArrayList<>();
		try (
			// Step :Create a statement using connection object
			PreparedStatement preparedStatement = con.prepareStatement(SELECT_ALL_CONTACT);) {
			System.out.println(preparedStatement);
			// Step : Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step : Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("contact_id");
				String contactName = rs.getString("contact_name");
				String relationship = rs.getString("relationship");
				String email = rs.getString("email");
				String phoneNumber = rs.getString("phone_number");
				contacts.add(new Contact(id, contactName, relationship, email, phoneNumber));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return contacts;
	}
	
	public boolean updateContact(Connection con, Contact contact) throws SQLException {
		boolean rowUpdated;
		try (
			PreparedStatement statement = con.prepareStatement(UPDATE_CONTACT_SQL);) {
			statement.setString(1, contact.getContactName());
			statement.setString(2, contact.getRelationship());
			statement.setString(3, contact.getEmail());
			statement.setString(4, contact.getPhoneNumber());
			statement.setInt(5, contact.getContactId());

			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
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

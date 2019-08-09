package com.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.project.model.FallRecord;

public class FallRecordDAO {

	private static final String INSERT_RECORD_SQL = "INSERT INTO fallRecords" + "  (fall_condition) VALUES "
			+ " (?);";

	private static final String SELECT_RECORD_BY_ID = "SELECT * FROM fallRecords WHERE record_id =?";
//	private static final String SELECT_USER_BY_USERNAME = "SELECT * FROM Users WHERE username =?";
	private static final String SELECT_ALL_RECORDS = "SELECT * FROM fallRecords ORDER BY record_id DESC";
//	private static final String DELETE_USERS_SQL = "DELETE FROM Users WHERE user_id = ?;";
//	private static final String UPDATE_USERS_SQL = "UPDATE Users SET nickname = ?,email= ?, introduction =?, user_image =? WHERE user_id = ?;";
	
	public FallRecordDAO() {
		
	}
	
	public void insertRecord(Connection con, FallRecord record) throws SQLException {
		System.out.println(INSERT_RECORD_SQL);
		System.out.println(record.getCondition());
		// try-with-resource statement will auto close the connection.
		try (
			PreparedStatement preparedStatement = con.prepareStatement(INSERT_RECORD_SQL)) {
			preparedStatement.setString(1, record.getCondition());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}
	
	public FallRecord selectRecordById(Connection con, int id) {
		FallRecord record = null;
		try (
			PreparedStatement preparedStatement = con.prepareStatement(SELECT_RECORD_BY_ID)) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			// Step: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step: Process the ResultSet object.
			while (rs.next()) {
				String timeStamp = rs.getString("time_stamp");
				String doorCondition = rs.getString("fall_condition");
				record = new FallRecord(id, timeStamp, doorCondition);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return record;
	}
	
	public List<FallRecord> selectAllRecords(Connection con) {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<FallRecord> records = new ArrayList<>();
		try (
			// Step :Create a statement using connection object
			PreparedStatement preparedStatement = con.prepareStatement(SELECT_ALL_RECORDS);) {
			System.out.println(preparedStatement);
			// Step : Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step : Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("record_id");
				String timeStamp = rs.getString("time_stamp");
				String doorCondition = rs.getString("fall_condition");
				records.add(new FallRecord(id, timeStamp, doorCondition));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return records;
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

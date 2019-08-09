package com.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.project.model.MedicineRecord;

public class MedicineRecordDAO {

	private static final String INSERT_RECORD_SQL = "INSERT INTO medicineRecords" + "  (member_name, medicine, member_condition) VALUES "
			+ " (?, ?, ?);";

	private static final String SELECT_RECORD_BY_ID = "SELECT * FROM medicineRecords WHERE record_id =?";
//	private static final String SELECT_USER_BY_USERNAME = "SELECT * FROM Users WHERE username =?";
	private static final String SELECT_ALL_RECORDS = "SELECT * FROM medicineRecords ORDER BY record_id DESC LIMIT 100";
	private static final String SELECT_FIVE_RECORDS = "SELECT * FROM medicineRecords ORDER BY record_id DESC LIMIT 5";
//	private static final String DELETE_USERS_SQL = "DELETE FROM Users WHERE user_id = ?;";
//	private static final String UPDATE_USERS_SQL = "UPDATE Users SET nickname = ?,email= ?, introduction =?, user_image =? WHERE user_id = ?;";
	
	public MedicineRecordDAO() {
		
	}
	
	public void insertRecord(Connection con, MedicineRecord record) throws SQLException {
		System.out.println(INSERT_RECORD_SQL);
		System.out.println(record.getMemberName());
		System.out.println(record.getMedicine());
		System.out.println(record.getMemberCondition());
		// try-with-resource statement will auto close the connection.
		try (
			PreparedStatement preparedStatement = con.prepareStatement(INSERT_RECORD_SQL)) {
			preparedStatement.setString(1, record.getMemberName());
			preparedStatement.setString(2, record.getMedicine());
			preparedStatement.setString(3, record.getMemberCondition());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}
	
	public MedicineRecord selectRecordById(Connection con, int id) {
		MedicineRecord record = null;
		try (
			PreparedStatement preparedStatement = con.prepareStatement(SELECT_RECORD_BY_ID)) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			// Step: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step: Process the ResultSet object.
			while (rs.next()) {
				String memberName = rs.getString("member_name");
				String timeStamp = rs.getString("time_stamp");
				String medicine = rs.getString("medicine");
				String memberCondition = rs.getString("member_condition");
				record = new MedicineRecord(id, memberName, timeStamp, medicine, memberCondition);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return record;
	}
	
	public List<MedicineRecord> selectAllRecords(Connection con) {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<MedicineRecord> records = new ArrayList<>();
		try (
			// Step :Create a statement using connection object
			PreparedStatement preparedStatement = con.prepareStatement(SELECT_ALL_RECORDS);) {
			System.out.println(preparedStatement);
			// Step : Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step : Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("record_id");
				String memberName = rs.getString("member_name");
				String timeStamp = rs.getString("time_stamp");
				String medicine = rs.getString("medicine");
				String memberCondition = rs.getString("member_condition");
				records.add(new MedicineRecord(id, memberName, timeStamp, medicine, memberCondition));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return records;
	}
	
	public List<MedicineRecord> selectFiveRecords(Connection con) {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<MedicineRecord> records = new ArrayList<>();
		try (
			// Step :Create a statement using connection object
			PreparedStatement preparedStatement = con.prepareStatement(SELECT_FIVE_RECORDS);) {
			System.out.println(preparedStatement);
			// Step : Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step : Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("record_id");
				String memberName = rs.getString("member_name");
				String timeStamp = rs.getString("time_stamp");
				String medicine = rs.getString("medicine");
				String memberCondition = rs.getString("member_condition");
				records.add(new MedicineRecord(id, memberName, timeStamp, medicine, memberCondition));
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

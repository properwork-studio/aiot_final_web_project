package com.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.project.model.Medicine;

public class MedicineDAO {

	private static final String INSERT_MEDICINES_SQL = "INSERT INTO medicines" + "  (member_name, alert_time, box_1, box_2, box_3, box_4, box_5, "
			+ "medicine_1, medicine_2, medicine_3, medicine_4, medicine_5) VALUES "
			+ " (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

	private static final String SELECT_MEDICINE_BY_ID = "SELECT * FROM medicines WHERE rule_id =?";
//	private static final String SELECT_USER_BY_USERNAME = "SELECT * FROM Users WHERE username =?";
	private static final String SELECT_ALL_MEDICINES = "SELECT * FROM medicines ORDER BY alert_time";
	private static final String DELETE_MEDICINES_SQL = "DELETE FROM medicines WHERE rule_id = ?;";
	private static final String UPDATE_MEDICINES_SQL = "UPDATE medicines SET member_name = ?, alert_time = ?, box_1 = ?, box_2 = ?, box_3 = ?, box_4 = ?, "
			+ "box_5 = ?, medicine_1 = ?, medicine_2 = ?, medicine_3 = ?, medicine_4 = ?, medicine_5 = ? WHERE rule_id = ?;";
	
	public MedicineDAO() {
		
	}
	
	public void insertMedicine(Connection con, Medicine medicine) throws SQLException {
		System.out.println(INSERT_MEDICINES_SQL);
		System.out.println(medicine.getMemberName());
		System.out.println(medicine.getAlertTime());
		System.out.println(medicine.getBox_1());
		System.out.println(medicine.getBox_2());
		System.out.println(medicine.getBox_3());
		System.out.println(medicine.getBox_4());
		System.out.println(medicine.getBox_5());
		// try-with-resource statement will auto close the connection.
		try (
			PreparedStatement preparedStatement = con.prepareStatement(INSERT_MEDICINES_SQL)) {
			preparedStatement.setString(1, medicine.getMemberName());
			preparedStatement.setString(2, medicine.getAlertTime());
			preparedStatement.setString(3, medicine.getBox_1());
			preparedStatement.setString(4, medicine.getBox_2());
			preparedStatement.setString(5, medicine.getBox_3());
			preparedStatement.setString(6, medicine.getBox_4());
			preparedStatement.setString(7, medicine.getBox_5());
			preparedStatement.setString(8, medicine.getMedicine_1());
			preparedStatement.setString(9, medicine.getMedicine_2());
			preparedStatement.setString(10, medicine.getMedicine_3());
			preparedStatement.setString(11, medicine.getMedicine_4());
			preparedStatement.setString(12, medicine.getMedicine_5());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}
	
	public Medicine selectMedicineById(Connection con, int id) {
		Medicine medicine = null;
		try (
			PreparedStatement preparedStatement = con.prepareStatement(SELECT_MEDICINE_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			// Step: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step: Process the ResultSet object.
			while (rs.next()) {
				String memberName = rs.getString("member_name");
				String alertTime = rs.getString("alert_time");
				String box_1 = rs.getString("box_1");
				String box_2 = rs.getString("box_2");
				String box_3 = rs.getString("box_3");
				String box_4 = rs.getString("box_4");
				String box_5 = rs.getString("box_5");
				String medicine_1 = rs.getString("medicine_1");
				String medicine_2 = rs.getString("medicine_2");
				String medicine_3 = rs.getString("medicine_3");
				String medicine_4 = rs.getString("medicine_4");
				String medicine_5 = rs.getString("medicine_5");
				medicine = new Medicine(id, memberName, alertTime, box_1, box_2, box_3, box_4, box_5, medicine_1, medicine_2, medicine_3, medicine_4, medicine_5);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return medicine;
	}
	
	public List<Medicine> selectAllMedicines(Connection con) {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<Medicine> medicines = new ArrayList<>();
		try (
			// Step :Create a statement using connection object
			PreparedStatement preparedStatement = con.prepareStatement(SELECT_ALL_MEDICINES);) {
			System.out.println(preparedStatement);
			// Step : Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step : Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("rule_id");
				String memberName = rs.getString("member_name");
				String alertTime = rs.getString("alert_time");
				String box_1 = rs.getString("box_1");
				String box_2 = rs.getString("box_2");
				String box_3 = rs.getString("box_3");
				String box_4 = rs.getString("box_4");
				String box_5 = rs.getString("box_5");
				String medicine_1 = rs.getString("medicine_1");
				String medicine_2 = rs.getString("medicine_2");
				String medicine_3 = rs.getString("medicine_3");
				String medicine_4 = rs.getString("medicine_4");
				String medicine_5 = rs.getString("medicine_5");
				medicines.add(new Medicine(id, memberName, alertTime, box_1, box_2, box_3, box_4, box_5, medicine_1, medicine_2, medicine_3, medicine_4, medicine_5));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return medicines;
	}
	
	public boolean deleteMedicine(Connection con, int id) throws SQLException {
		boolean rowDeleted;
		try (
			PreparedStatement statement = con.prepareStatement(DELETE_MEDICINES_SQL);) {
			System.out.println(statement);
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}
	
	public boolean updateMedicine(Connection con, Medicine medicine) throws SQLException {
		boolean rowUpdated;
		try (
			PreparedStatement preparedStatement = con.prepareStatement(UPDATE_MEDICINES_SQL);) {
			System.out.println(preparedStatement);
			preparedStatement.setString(1, medicine.getMemberName());
			preparedStatement.setString(2, medicine.getAlertTime());
			preparedStatement.setString(3, medicine.getBox_1());
			preparedStatement.setString(4, medicine.getBox_2());
			preparedStatement.setString(5, medicine.getBox_3());
			preparedStatement.setString(6, medicine.getBox_4());
			preparedStatement.setString(7, medicine.getBox_5());
			preparedStatement.setString(8, medicine.getMedicine_1());
			preparedStatement.setString(9, medicine.getMedicine_2());
			preparedStatement.setString(10, medicine.getMedicine_3());
			preparedStatement.setString(11, medicine.getMedicine_4());
			preparedStatement.setString(12, medicine.getMedicine_5());
			preparedStatement.setInt(13, medicine.getRuleId());

			rowUpdated = preparedStatement.executeUpdate() > 0;
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

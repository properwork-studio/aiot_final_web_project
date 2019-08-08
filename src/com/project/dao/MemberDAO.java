package com.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.project.model.Member;

public class MemberDAO {

	private static final String INSERT_MEMBERS_SQL = "INSERT INTO members" + "  (member_name, member_code, birthday, id_number, member_photo) VALUES "
			+ " (?, ?, ?, ?, ?);";

	private static final String SELECT_MEMBER_BY_ID = "SELECT * FROM members WHERE member_id =?";
	private static final String SELECT_MEMBER_BY_MEMBERNAME = "SELECT * FROM members WHERE member_name =?";
	private static final String SELECT_ALL_MEMBERS = "SELECT * FROM members";
//	private static final String DELETE_USERS_SQL = "DELETE FROM Users WHERE user_id = ?;";
//	private static final String UPDATE_USERS_SQL = "UPDATE Users SET nickname = ?,email= ?, introduction =?, user_image =? WHERE user_id = ?;";
	
	public MemberDAO() {
		
	}
	
	public void insertMember(Connection con, Member member) throws SQLException {
		System.out.println(INSERT_MEMBERS_SQL);
		System.out.println(member.getMemberName());
		System.out.println(member.getMemberCode());
		System.out.println(member.getBirthday());
		System.out.println(member.getIdNumber());
		System.out.println(member.getPhotoPath());
		// try-with-resource statement will auto close the connection.
		try (
			PreparedStatement preparedStatement = con.prepareStatement(INSERT_MEMBERS_SQL)) {
			preparedStatement.setString(1, member.getMemberName());
			preparedStatement.setString(2, member.getMemberCode());
			preparedStatement.setString(3, member.getBirthday());
			preparedStatement.setString(4, member.getIdNumber());
			preparedStatement.setString(5, member.getPhotoPath());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}
	
	public Member selectMemberById(Connection con, int id) {
		Member member = null;
		try (
			PreparedStatement preparedStatement = con.prepareStatement(SELECT_MEMBER_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			// Step: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step: Process the ResultSet object.
			while (rs.next()) {
				String memberName = rs.getString("member_name");
				String memberCode = rs.getString("member_code");
				String birthday = rs.getString("birthday");
				String idNumber = rs.getString("id_number");
				String memberPhoto = rs.getString("member_photo");
				member = new Member(id, memberName, memberCode, birthday, idNumber, memberPhoto);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return member;
	}
	
	public Member selectMemberByName(Connection con, String name) {
		Member member = null;
		try (
			PreparedStatement preparedStatement = con.prepareStatement(SELECT_MEMBER_BY_MEMBERNAME);) {
			preparedStatement.setString(1, name);
			System.out.println(preparedStatement);
			// Step: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("member_id");
				String memberName = rs.getString("member_name");
				String memberCode = rs.getString("member_code");
				String birthday = rs.getString("birthday");
				String idNumber = rs.getString("id_number");
				String memberPhoto = rs.getString("member_photo");
				member = new Member(id, memberName, memberCode, birthday, idNumber, memberPhoto);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return member;
	}
	
	public List<Member> selectAllMembers(Connection con) {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<Member> members = new ArrayList<>();
		try (
			// Step :Create a statement using connection object
			PreparedStatement preparedStatement = con.prepareStatement(SELECT_ALL_MEMBERS);) {
			System.out.println(preparedStatement);
			// Step : Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step : Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("member_id");
				String memberName = rs.getString("member_name");
				String memberCode = rs.getString("member_code");
				String birthday = rs.getString("birthday");
				String idNumber = rs.getString("id_number");
				String memberPhoto = rs.getString("member_photo");
				members.add(new Member(id, memberName, memberCode, birthday, idNumber, memberPhoto));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return members;
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

package com.project.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Base64;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.dao.FallRecordDAO;
import com.project.dao.UserDAO;
import com.project.model.User;

/**
 * Servlet implementation class AccountServlet
 */
@WebServlet("/new_account")
public class AccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO;
	final Base64.Encoder encoder = Base64.getEncoder();
	private static final String CREATE_MEMBER_TABLE = "create table members (\n" + 
			"    member_id int not null AUTO_INCREMENT,\n" + 
			"    member_name varchar(255) not null,\n" + 
			"    member_code varchar(255) not null,\n" + 
			"    birthday varchar(255) not null,\n" + 
			"    id_number varchar(255) not null,\n" + 
			"    member_photo varchar(255) not null,\n" + 
			"	PRIMARY KEY (member_id)\n" + 
			");";
	private static final String CREATE_CONTACT_TABLE = "create table contact (\n" + 
			"    contact_id int not null AUTO_INCREMENT,\n" + 
			"    contact_name varchar(255) not null,\n" + 
			"    relationship varchar(255) not null,\n" + 
			"    email varchar(255) not null,\n" + 
			"    phone_number varchar(255) not null,\n" + 
			"	PRIMARY KEY (contact_id)\n" + 
			");";
	private static final String CREATE_MEDICINE_TABLE = "create table medicines (\n" + 
			"    rule_id int not null AUTO_INCREMENT,\n" + 
			"    member_name varchar(255) not null,\n" + 
			"    alert_time varchar(255) not null,\n" + 
			"    box_1 varchar(20) not null,\n" + 
			"    box_2 varchar(20) not null,\n" + 
			"    box_3 varchar(20) not null,\n" + 
			"    box_4 varchar(20) not null,\n" + 
			"    box_5 varchar(20) not null,\n" + 
			"    medicine_1 varchar(255),\n" + 
			"    medicine_2 varchar(255),\n" + 
			"    medicine_3 varchar(255),\n" + 
			"    medicine_4 varchar(255),\n" + 
			"    medicine_5 varchar(255),\n" + 
			"	PRIMARY KEY (rule_id)\n" + 
			");";
	private static final String CREATE_MEDICINERECORD_TABLE = "create table medicineRecords (\n" + 
			"    record_id int not null AUTO_INCREMENT,\n" + 
			"    member_name varchar(255) not null,\n" + 
			"    time_stamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON update CURRENT_TIMESTAMP,\n" + 
			"    medicine varchar(255) not null,\n" + 
			"    member_condition varchar(255) not null,\n" + 
			"	PRIMARY KEY (record_id)\n" + 
			");";
	private static final String CREATE_DOORRECORD_TABLE = "create table doorRecords (\n" + 
			"    record_id int not null AUTO_INCREMENT,\n" + 
			"    member_name varchar(255) not null,\n" + 
			"    time_stamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON update CURRENT_TIMESTAMP,\n" + 
			"    door_condition varchar(255) not null,\n" + 
			"    real_fake varchar(100),\n" + 
			"	PRIMARY KEY (record_id)\n" + 
			");";
	private static final String CREATE_FALLRECORD_TABLE = "create table fallRecords (\n" + 
			"    record_id int not null AUTO_INCREMENT,\n" + 
			"    time_stamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON update CURRENT_TIMESTAMP,\n" + 
			"    fall_condition varchar(255) not null,\n" + 
			"    room varchar(100),\n" + 
			"	PRIMARY KEY (record_id)\n" + 
			");";
	private static final String INSERT_FALLRECORD_SQL = "INSERT INTO fallRecords" + "  (fall_condition) VALUES "
			+ " (\"safe\");";
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init() {
    	userDAO = new UserDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		ServletContext ctx = this.getServletContext();
		List<User> listUsers = userDAO.selectAllUsers();
		String email = request.getParameter("email");
		Boolean success = true;
		for(int i=0; i<listUsers.size(); i++) {
			if(listUsers.get(i).getEmail() == email) {
				success = false;
			}
		}
		if(success) {
			try {
				addNewUser(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// Change dbconnection to current user
			String dbname = email.substring(0, email.indexOf("@"));
			Connection con = newDBConnection(dbname, ctx);
			try {
				initDatabase(con);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.getRequestDispatcher("setting_member").forward(request, response);
		} else {
			System.out.println("creating account error");
			response.sendRedirect("setting_account?account_error=true");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private Connection newDBConnection (String dbname, ServletContext ctx) {
		String JDBC_DRIVER = "com.mysql.jdbc.Driver";
		String DB_URL = "jdbc:mysql://localhost:3306/test_" + dbname + "?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=CST&characterEncoding=utf8";
		String USER = "root";
		String PASSWORD = "lomo81818";
		
		Connection con = null;
		try {
    		Class.forName(JDBC_DRIVER);
    		con = DriverManager.getConnection(DB_URL, USER, PASSWORD);
    		ctx.setAttribute("current_db", con);
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
		
		return con;
	}
	
	private void initDatabase (Connection con) throws SQLException {
			PreparedStatement preparedStatement = con.prepareStatement(CREATE_MEMBER_TABLE);
//			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
			preparedStatement = con.prepareStatement(CREATE_CONTACT_TABLE);
//			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
			preparedStatement = con.prepareStatement(CREATE_MEDICINE_TABLE);
//			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
			preparedStatement = con.prepareStatement(CREATE_MEDICINERECORD_TABLE);
//			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
			preparedStatement = con.prepareStatement(CREATE_DOORRECORD_TABLE);
//			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
			preparedStatement = con.prepareStatement(CREATE_FALLRECORD_TABLE);
//			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
			preparedStatement = con.prepareStatement(INSERT_FALLRECORD_SQL);
//			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
			preparedStatement.close();
	}
	
	private void addNewUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		// Reading new user's data
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String phoneNumber = request.getParameter("phone");
		byte[] passwordByte = password.getBytes();
		String encodedPassword = encoder.encodeToString(passwordByte);
		// Putting data into database
		User newUser = new User(email, encodedPassword, name, phoneNumber);
//		userDAO.insertUser(con, newUser);
		userDAO.insertUser(newUser);
		
		// Passing current user down
		String dbname = email.substring(0, email.indexOf("@"));
		HttpSession session = request.getSession();
		ServletContext ctx = this.getServletContext();
		session.setAttribute("currentUser", newUser.getName());
		session.setAttribute("current_dbname", dbname);
	}

}

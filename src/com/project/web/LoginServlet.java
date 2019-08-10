package com.project.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Base64;

import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.model.User;
import com.project.dao.UserDAO;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet({"/login", "/toLogin"})
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO;
	final Base64.Encoder encoder = Base64.getEncoder();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
		String action = request.getServletPath();
//		ServletContext ctx = getServletContext();
		try {
			switch (action) {
			case "/toLogin":
				toLogin(request, response);
				break;
			case "/login":
				showLoginForm(request, response);
				break;
			default:
				request.getRequestDispatcher("error.jsp").forward(request, response);
				break;
			}
		} catch (Exception ex) {
			throw new ServletException(ex);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void showLoginForm(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.getRequestDispatcher("login.jsp").forward(request, response);
	}
	
	private void toLogin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
		ServletContext ctx = this.getServletContext();
		Connection con = (Connection)ctx.getAttribute("users_db");
//		List<User> listUsers = userDAO.selectAllUsers(con);
		String email = request.getParameter("email");
		String dbname = email.substring(0,email.indexOf("@"));
//		User user = userDAO.selectUserByEmail(con, email);
		User user = userDAO.selectUserByEmail(email);
		String password = request.getParameter("password");
		byte[] passwordByte = password.getBytes();
		String encodedPassword = encoder.encodeToString(passwordByte);
		if(user != null) {
			if(user.getPassword().equals(encodedPassword)) {
				HttpSession session = request.getSession();
				session.setAttribute("currentUser", user.getName());
				newDBConnection(dbname, ctx);
				response.sendRedirect("dashboard");
			} else {
				System.out.println("login error");
				response.sendRedirect("login?login_error=true");
			}
		} else {
			System.out.println("user not exist!s");
			response.sendRedirect("login?login_error=true");
		}
		
	}
	
	private void newDBConnection (String dbname, ServletContext ctx) {
		String JDBC_DRIVER = "com.mysql.jdbc.Driver";
		String DB_URL = "jdbc:mysql://localhost:3306/test_" + dbname + "?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=CST";
		String USER = "root";
		String PASSWORD = "lomo81818";
		
		try {
			System.out.println("Prepare to get another connection");
    		Class.forName(JDBC_DRIVER);
    		Connection con = DriverManager.getConnection(DB_URL, USER, PASSWORD);
    		ctx.setAttribute("current_db", con);
//    		con.close();
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
	}

}

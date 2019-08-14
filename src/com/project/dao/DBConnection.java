package com.project.dao;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Application Lifecycle Listener implementation class DBConnection
 *
 */
public class DBConnection implements ServletContextListener {
//	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
//	static final String DB_URL = "jdbc:mysql://localhost:3306/demo?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=CST";
//	static final String USER = "root";
//	static final String PASSWORD = "lomo81818";
	
	String url = "jdbc:mysql://localhost:3306/test_users?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=CST";
    String driver = "com.mysql.jdbc.Driver";
    String userName = "user";
    String password = "lomo81818";

    /**
     * Default constructor. 
     */
    public DBConnection() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    	System.out.println("MySQL JDBC Example.");
    	try {
    		Class.forName(driver);
    		Connection con = DriverManager.getConnection(url, userName, password);
    		ServletContext ctx = sce.getServletContext();
    		ctx.setAttribute("users_db", con);
    		con.close();
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    }
	
}

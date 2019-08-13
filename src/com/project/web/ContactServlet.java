package com.project.web;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.dao.ContactDAO;
import com.project.model.Contact;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

/**
 * Servlet implementation class ContactServlet
 */
@WebServlet({"/new_contact", "/edit_contact"})
public class ContactServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ContactDAO contactDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ContactServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init() {
    	contactDAO = new ContactDAO();
    }
 
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getServletPath();
//		ServletContext ctx = this.getServletContext();
		HttpSession session = request.getSession();
		Connection con = (Connection)session.getAttribute("current_db");
		System.out.println("Connection: " + con);
		try {
			switch (action) {
			case "/new_contact":
				addNewContact(request, response, con);
				break;
			case "/edit_contact":
				editContact(request, response, con);
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
	
	protected void addNewContact(HttpServletRequest request, HttpServletResponse response, Connection con) throws SQLException, IOException {
		String contactName = request.getParameter("contactName");
		String relationship = request.getParameter("relationship");
		String contactEmail = request.getParameter("contactEmail");
		String phoneNumber = request.getParameter("phoneNumber");
		// Putting data into database
		Contact newContact = new Contact(contactName, relationship, phoneNumber, contactEmail);
		contactDAO.insertContact(con, newContact);
		HttpSession session = request.getSession();
		String dbname = (String) session.getAttribute("current_dbname");
//		socketToFall(dbname);
//		socketToDoor(dbname);
//		socketToEnv(dbname);
		response.sendRedirect("setting_medicines");
	}
	
	protected void editContact(HttpServletRequest request, HttpServletResponse response, Connection con) throws SQLException, IOException {
		int contactID = Integer.parseInt(request.getParameter("contact_id"));
		HttpSession session = request.getSession();
		String contactName = request.getParameter("contactName");
		String relationship = request.getParameter("relationship");
		String phoneNumber = request.getParameter("phoneNumber");
		String contactEmail = request.getParameter("contactEmail");
		Contact contact = new Contact(contactID, contactName, relationship, phoneNumber, contactEmail);
		contactDAO.updateContact(con, contact);
		response.sendRedirect("dashboard");
	}

	protected void socketToFall(String dbname) {
		try {
			io.socket.client.Socket socket ;
			socket = IO.socket("http://192.168.21.37:3000");
			socket.connect();
			String argument = "-s 192.168.21.54 -u user -p lomo81818 -d test_" + dbname;
			socket.emit("wakeup", argument);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected void socketToDoor(String dbname) {
		try {
			io.socket.client.Socket socket ;
			socket = IO.socket("http://192.168.21.36:3000");
			socket.connect();
			String argument = "-s 192.168.21.54 -u user -p lomo81818 -d test_" + dbname;
			socket.emit("wakeup", argument);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected void socketToEnv(String dbname) {
		try {
			io.socket.client.Socket socket ;
			socket = IO.socket("http://192.168.21.36:3000");
			socket.connect();
			String argument = "-s 192.168.21.54 -u user -p lomo81818 -d test_" + dbname;
			socket.emit("wakeup", argument);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

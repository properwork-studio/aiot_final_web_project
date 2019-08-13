package com.project.web;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
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

import com.project.dao.MedicineDAO;
import com.project.model.Medicine;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

/**
 * Servlet implementation class MedicineServlet
 */
@WebServlet({"/new_medicine", "/next_medicine", "/init_medicine", "/edit_medicine", "/init_edit_medicine", "/delete_medicine", "/init_delete_medicine"})
public class MedicineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MedicineDAO medicineDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MedicineServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init() {
    	medicineDAO = new MedicineDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String action = request.getServletPath();
//		ServletContext ctx = this.getServletContext();
		HttpSession session = request.getSession();
		Connection con = (Connection)session.getAttribute("current_db");
		String dbname = (String) session.getAttribute("current_dbname");
		System.out.println("Connection: " + con);
		try {
			switch (action) {
			case "/new_medicine":
				addNewMedicine(request, response, con);
				request.getRequestDispatcher("dashboard?open_overlay=true").forward(request, response);
				// 於dashboard 點選用藥設定要新增時
				break;
			case "/next_medicine":
				addNewMedicine(request, response, con);
				response.sendRedirect("setting_medicines");
				// 繼續回到init_medicine.jsp
				break;
			case "/init_medicine":
//				addNewMedicine(request, response, con);
				socketToMedicine(dbname);
				response.sendRedirect("dashboard");
				// init 到dashboard
				break;
			case "/edit_medicine":
				updateMedicine(request, response, con);
				socketToMedicineUpdate(dbname);
				request.getRequestDispatcher("dashboard?open_overlay=true").forward(request, response);
				break;
			case "/init_edit_medicine":
				updateMedicine(request, response, con);
				response.sendRedirect("setting_medicines");
				break;
			case "/delete_medicine":
				deleteMedicine(request, response, con);
				socketToMedicineUpdate(dbname);
				request.getRequestDispatcher("dashboard?open_overlay=true").forward(request, response);
				// Delete medicine from dashboard
				break;
			case "/init_delete_medicine":
				deleteMedicine(request, response, con);
				response.sendRedirect("setting_medicines");
				// Delete medicine from dashboard
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

	protected void addNewMedicine(HttpServletRequest request, HttpServletResponse response, Connection con) throws SQLException, UnsupportedEncodingException {
		Medicine newMedicine = grabFormField(request);
		medicineDAO.insertMedicine(con, newMedicine);
	}
	
	protected void updateMedicine(HttpServletRequest request, HttpServletResponse response, Connection con) throws UnsupportedEncodingException, SQLException {
		String memberName = request.getParameter("memberName");
		String alertHour = request.getParameter("alertHour");
		String alertMinute = request.getParameter("alertMinute");
		String alertTime = alertHour + ":" + alertMinute;
		String box_1 = request.getParameter("box_1");
		String box_2 = request.getParameter("box_2");
		String box_3 = request.getParameter("box_3");
		String box_4 = request.getParameter("box_4");
		String box_5 = request.getParameter("box_5");
		box_1 = boxTransfer(box_1);
		box_2 = boxTransfer(box_2);
		box_3 = boxTransfer(box_3);
		box_4 = boxTransfer(box_4);
		box_5 = boxTransfer(box_5);
		String medicine_1 = request.getParameter("medicine_1");
		String medicine_2 = request.getParameter("medicine_2");
		String medicine_3 = request.getParameter("medicine_3");
		String medicine_4 = request.getParameter("medicine_4");
		String medicine_5 = request.getParameter("medicine_5");
		medicine_1 = medicineTransfer(medicine_1);
		medicine_2 = medicineTransfer(medicine_2);
		medicine_3 = medicineTransfer(medicine_3);
		medicine_4 = medicineTransfer(medicine_4);
		medicine_5 = medicineTransfer(medicine_5);
		int ruleId = Integer.parseInt(request.getParameter("record_id"));
		System.out.println(ruleId);
		Medicine medicine = new Medicine(ruleId, memberName, alertTime, box_1, box_2, box_3, box_4, box_5, medicine_1, medicine_2, medicine_3, medicine_4, medicine_5);
		medicineDAO.updateMedicine(con, medicine);
	}
	
	protected void deleteMedicine(HttpServletRequest request, HttpServletResponse response, Connection con) throws SQLException {
		int ruleId = Integer.parseInt(request.getParameter("medicine_id"));
		medicineDAO.deleteMedicine(con, ruleId);
	}
	
	private Medicine grabFormField(HttpServletRequest request) throws UnsupportedEncodingException {
		String memberName = request.getParameter("memberName");
		String alertHour = request.getParameter("alertHour");
		String alertMinute = request.getParameter("alertMinute");
		String alertTime = alertHour + ":" + alertMinute;
		String box_1 = request.getParameter("box_1");
		String box_2 = request.getParameter("box_2");
		String box_3 = request.getParameter("box_3");
		String box_4 = request.getParameter("box_4");
		String box_5 = request.getParameter("box_5");
		box_1 = boxTransfer(box_1);
		box_2 = boxTransfer(box_2);
		box_3 = boxTransfer(box_3);
		box_4 = boxTransfer(box_4);
		box_5 = boxTransfer(box_5);
		String medicine_1 = request.getParameter("medicine_1");
		String medicine_2 = request.getParameter("medicine_2");
		String medicine_3 = request.getParameter("medicine_3");
		String medicine_4 = request.getParameter("medicine_4");
		String medicine_5 = request.getParameter("medicine_5");
		medicine_1 = medicineTransfer(medicine_1);
		medicine_2 = medicineTransfer(medicine_2);
		medicine_3 = medicineTransfer(medicine_3);
		medicine_4 = medicineTransfer(medicine_4);
		medicine_5 = medicineTransfer(medicine_5);
		Medicine medicine = new Medicine(memberName, alertTime, box_1, box_2, box_3, box_4, box_5, medicine_1, medicine_2, medicine_3, medicine_4, medicine_5);
		return medicine;
	}
	
	private String boxTransfer(String box) {
		System.out.println(box);
		if(box == null) {
			box = "0";
		} else {
			box = "1";
		}
		return box;
	}
	
	private String medicineTransfer(String medicine) throws UnsupportedEncodingException {
		System.out.println(medicine);
		if(medicine == null) {
			medicine = "";
		} else {
			medicine = new String(medicine.getBytes("utf-8"), "utf-8");
		}
		return medicine;
	}
	
	protected void socketToMedicine(String dbname) {
		try {
			io.socket.client.Socket socket ;
			socket = IO.socket("http://192.168.21.38:3000");
//			socket.on(Socket.EVENT_MESSAGE, new Emitter.Listener() {
//			  @Override
//			  public void call(Object... args) {
//			    // where socket emit was be
//			    System.out.print("Connected to ubuntu test env");
//			  }
//			});
			socket.connect();
			String argument = "-s 192.168.21.54 -u user -p lomo81818 -d test_" + dbname;
			socket.emit("wakeup", argument);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected void socketToMedicineUpdate(String dbname) {
		try {
			io.socket.client.Socket socket ;
			socket = IO.socket("http://192.168.21.38:3000");
			socket.connect();
			socket.emit("changedb", "There is some changes in database");
		    System.out.print("Connected to Medicine alert Rpi");
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

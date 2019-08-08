package com.project.web;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.dao.MedicineDAO;
import com.project.model.Medicine;

/**
 * Servlet implementation class MedicineServlet
 */
@WebServlet({"/new_medicine", "/next_medicine", "/init_medicine", "/edit_medicine", "/delete_medicine"})
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
		ServletContext ctx = this.getServletContext();
		Connection con = (Connection)ctx.getAttribute("current_db");
		System.out.println("Connection: " + con);
		try {
			switch (action) {
			case "/new_medicine":
				addNewMedicine(request, response, con);
				// 於dashboard 點選用藥設定要新增時
				break;
			case "/next_medicine":
				addNewMedicine(request, response, con);
				response.sendRedirect("setting_medicines");
				// 繼續回到init_medicine.jsp
				break;
			case "/init_medicine":
//				addNewMedicine(request, response, con);
				response.sendRedirect("dashboard");
				// init 到dashboard
				break;
			case "/edit_medicine":
				updateMedicine(request, response, con);
				break;
			case "/delete_medicine":
				deleteMedicine(request, response, con);
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
		// Putting data into database
		Medicine newMedicine = new Medicine(memberName, alertTime, box_1, box_2, box_3, box_4, box_5, medicine_1, medicine_2, medicine_3, medicine_4, medicine_5);
		medicineDAO.insertMedicine(con, newMedicine);
	}
	
	private String boxTransfer(String box) {
		if(box == null) {
			box = "0";
		} else {
			box = "1";
		}
		return box;
	}
	
	private String medicineTransfer(String medicine) throws UnsupportedEncodingException {
		if(medicine == null) {
			medicine = "";
		} else {
			medicine = new String(medicine.getBytes("utf-8"), "utf-8");
		}
		return medicine;
	}
	
	protected void updateMedicine(HttpServletRequest request, HttpServletResponse response, Connection con) {
		
	}
	
	protected void deleteMedicine(HttpServletRequest request, HttpServletResponse response, Connection con) {
		
	}
}

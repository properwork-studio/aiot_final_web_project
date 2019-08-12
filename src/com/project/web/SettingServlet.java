package com.project.web;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.dao.MedicineDAO;
import com.project.dao.MemberDAO;
import com.project.model.Medicine;
import com.project.model.Member;

/**
 * Servlet implementation class SettingServlet
 */
@WebServlet({"/setting_account", "/setting_member", "/setting_contact", "/setting_medicines"})
public class SettingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberDAO memberDAO;
	private MedicineDAO medicineDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SettingServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init() {
    	memberDAO = new MemberDAO();
    	medicineDAO = new MedicineDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getServletPath();
		ServletContext ctx = getServletContext();
		HttpSession session = request.getSession();
		Connection con = (Connection) session.getAttribute("current_db");
		try {
			switch (action) {
			case "/setting_account":
				showAccountInitForm(request, response);
				break;
			case "/setting_member":
				showMemberInitForm(request, response);
				break;
			case "/setting_contact":
				showContactInitForm(request, response);
				break;
			case "/setting_medicines":
				showMedicineInitForm(request, response, con);
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
	
	private void showAccountInitForm(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.getRequestDispatcher("init_account.jsp").forward(request, response);
	}
	
	private void showContactInitForm(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.getRequestDispatcher("init_contact.jsp").forward(request, response);
	}
	
	private void showMemberInitForm(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.getRequestDispatcher("init_member.jsp").forward(request, response);
	}
	
	private void showMedicineInitForm(HttpServletRequest request, HttpServletResponse response, Connection con) throws IOException, ServletException {
		List<Member> listMembers = memberDAO.selectAllMembers(con);
		request.setAttribute("listAllMembers", listMembers);
		List<Medicine> listMedicines = medicineDAO.selectAllMedicines(con);
		request.setAttribute("listAllMedicines", listMedicines);
		request.getRequestDispatcher("init_medicine.jsp").forward(request, response);
	}

}

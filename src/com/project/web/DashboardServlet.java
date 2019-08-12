package com.project.web;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.dao.*;
import com.project.model.*;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

/**
 * Servlet implementation class DashboardServlet
 */
@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberDAO memberDAO;
	private ContactDAO contactDAO;
	private MedicineDAO medicineDAO;
	private MedicineRecordDAO medicineRecordDAO;
	private DoorRecordDAO doorRecordDAO;
	private FallRecordDAO fallRecordDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DashboardServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init() {
    	memberDAO = new MemberDAO();
    	contactDAO = new ContactDAO();
    	medicineDAO = new MedicineDAO();
    	medicineRecordDAO = new MedicineRecordDAO();
    	doorRecordDAO = new DoorRecordDAO();
    	fallRecordDAO = new FallRecordDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		HttpSession session = request.getSession();
		if(session.getAttribute("currentUser") != null) {
			response.setContentType("text/html;charset=utf-8");
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
//			String action = request.getServletPath();
			ServletContext ctx = this.getServletContext();
			Connection con = (Connection)session.getAttribute("current_db");
			grabMembers(request, response, con);
			grabContact(request, response, con);
			grabMedicine(request, response, con);
			grabAllMedicineRecords(request, response, con);
			grabMedicineRecords(request, response, con);
			grabFallRecord(request, response, con);
			grabAllDoorRecords(request, response, con);
			grabDoorRecords(request, response, con);
			String dbname = (String) session.getAttribute("current_dbname");
			String photoPath = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/dataimages/" + dbname + "/";
			request.setAttribute("realPath", photoPath);
			doSocket();
			socketToMedicine();
			socketToDoor();
			socketToFall();
			request.getRequestDispatcher("dashboard.jsp").forward(request, response);
		} else {
			response.sendRedirect(request.getContextPath());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	protected void grabMembers(HttpServletRequest request, HttpServletResponse response, Connection con) {
		List<Member> listMembers = memberDAO.selectAllMembers(con);
		request.setAttribute("listMembers", listMembers);
	}
	
	protected void grabContact(HttpServletRequest request, HttpServletResponse response, Connection con) {
		Contact contact = contactDAO.selectLastContact(con);
		request.setAttribute("contact", contact);
	}
	
	protected void grabMedicine(HttpServletRequest request, HttpServletResponse response, Connection con) {
		List<Medicine> listMedicines = medicineDAO.selectAllMedicines(con);
		request.setAttribute("listMedicines", listMedicines);
	}
	
	protected void grabAllMedicineRecords(HttpServletRequest request, HttpServletResponse response, Connection con) {
		List<MedicineRecord> listMedicineRecords = medicineRecordDAO.selectAllRecords(con);
		request.setAttribute("listMedicineRecords", listMedicineRecords);
	}
	
	protected void grabMedicineRecords(HttpServletRequest request, HttpServletResponse response, Connection con) {
		List<MedicineRecord> listMedicineRecords = medicineRecordDAO.selectFiveRecords(con);
		request.setAttribute("listFiveMedicineRecords", listMedicineRecords);
	}
	
	protected void grabFallRecord(HttpServletRequest request, HttpServletResponse response, Connection con) {
		List<FallRecord> listFallRecords = fallRecordDAO.selectAllRecords(con);
		FallRecord latestRecord = listFallRecords.get(0);
		System.out.println(listFallRecords.size());
		request.setAttribute("latestRecord", latestRecord);
	}
	
	protected void grabAllDoorRecords(HttpServletRequest request, HttpServletResponse response, Connection con) {
		List<DoorRecord> listDoorRecords = doorRecordDAO.selectAllRecords(con);
		request.setAttribute("listDoorRecords", listDoorRecords);
	}
	
	protected void grabDoorRecords(HttpServletRequest request, HttpServletResponse response, Connection con) {
		List<DoorRecord> listDoorRecords = doorRecordDAO.selectThreeRecords(con);
		request.setAttribute("listThreeDoorRecords", listDoorRecords);
	}

	protected void doSocket() {
		try {
			io.socket.client.Socket socket ;
			socket = IO.socket("http://192.168.21.54:3000");
			socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
			  @Override
			  public void call(Object... args) {
			    socket.emit("connection", "hello from the other side");
			    System.out.print("Connected to ubuntu test env");
			  }
			});
			socket.connect();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected void socketToFall() {
		try {
			io.socket.client.Socket socket ;
			socket = IO.socket("http://192.168.21.37:3000");
			socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
			  @Override
			  public void call(Object... args) {
			    socket.emit("connection", "hello from the other side");
			    System.out.print("Connected to Fall detection Rpi");
			  }
			});
			socket.connect();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected void socketToDoor() {
		try {
			io.socket.client.Socket socket ;
			socket = IO.socket("http://192.168.21.36:3000");
			socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
			  @Override
			  public void call(Object... args) {
			    socket.emit("connection", "hello from the other side");
			    System.out.print("Connected to Door detection Rpi");
			  }
			});
			socket.connect();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected void socketToMedicine() {
		try {
			io.socket.client.Socket socket ;
			socket = IO.socket("http://192.168.21.38:3000");
			socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
			  @Override
			  public void call(Object... args) {
			    socket.emit("connection", "hello from the other side");
			    System.out.print("Connected to Medicine alert Rpi");
			  }
			});
			socket.connect();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

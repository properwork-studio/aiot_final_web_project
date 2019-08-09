package com.project.web;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.FileItemIterator;
import org.apache.tomcat.util.http.fileupload.FileItemStream;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.util.Streams;

import com.project.dao.MemberDAO;
import com.project.model.Member;

/**
 * Servlet implementation class MemberServlet
 */
@WebServlet({"/next_member", "/new_member"})
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberDAO memberDAO;
	String memberName = null;
	String nameForTraining = null;
	String birthday = null;
	String idNumber = null;
	String filePath = null;
	
	File tmpDir = null;//初始化上傳檔案的臨時存放目錄 
	File saveDir = null;//初始化上傳檔案後的儲存目錄 
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init() {
    	memberDAO = new MemberDAO();
//    	photoUpload = new PhotoUpload();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getServletPath();
//		HttpSession session = request.getSession();
		ServletContext ctx = this.getServletContext();
		Connection con = (Connection)ctx.getAttribute("current_db");
		System.out.println("Connection: " + con);
		try {
			switch (action) {
			case "/next_member":
				addNewMember(request, response, con);
				response.sendRedirect("setting_member?add_next=false");
				break;
			case "/new_member":
				addNewMember(request, response, con);
				response.sendRedirect("setting_contact");
				break;
			default:
				request.getRequestDispatcher("error.jsp").forward(request, response);
				break;
			}
		} catch (Exception ex) {
			throw new ServletException(ex);
		}
		
	}

	private void addNewMember(HttpServletRequest request, HttpServletResponse response, Connection con) throws SQLException, IOException, ServletException {
		// Reading new member's data
		String fileName = fileUpload(request, response);
		System.out.println(fileName);
		// Putting data into database
		Member newMember = new Member(memberName, nameForTraining, birthday, idNumber, fileName);
		memberDAO.insertMember(con, newMember);
		
	}
	
	
	protected String fileUpload(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		ServletContext ctx = this.getServletContext();
		initFileUpload();
		String fileName = null;
		try{ 
			String pp=null; 
			String upto=null; 
			if(ServletFileUpload.isMultipartContent(request)){ 
				DiskFileItemFactory dff = new DiskFileItemFactory();//建立該物件 
				dff.setRepository(tmpDir);//指定上傳檔案的臨時目錄 
				dff.setSizeThreshold(10240000);//指定在記憶體中快取資料大小,單位為byte 
				ServletFileUpload sfu = new ServletFileUpload(dff);//建立該物件 
				sfu.setFileSizeMax(50000000);//指定單個上傳檔案的最大尺寸 
				sfu.setSizeMax(10000000);//指定一次上傳多個檔案的總尺寸 
				FileItemIterator fii = sfu.getItemIterator(request);//解析request 請求,並返回FileItemIterator集合 
				while(fii.hasNext()){ 
						FileItemStream fis = fii.next();//從集合中獲得一個檔案流 
						if(!fis.isFormField() && fis.getName().length()>0){//過濾掉表單中非檔案域 
							//String fileName = fis.getName().substring(fis.getName().lastIndexOf("/"));//獲得上傳檔案的檔名
							fileName = fis.getName();//獲得上傳檔案的檔名
//							System.out.println("request.getRealPath()=="+request.getRealPath("/")); 
							String uploadPath = request.getRealPath("/")+"dataimages/";//選定上傳的目錄此處為當前目錄 
//							String uploadPath = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()+"/dataimages/";
							if(!new File(uploadPath).isDirectory())//選定上傳的目錄此處為當前目錄,沒有則建立 
								new File(uploadPath).mkdirs(); 
							System.out.println("uploadPath="+uploadPath); 
							ctx.setAttribute("uploadPath", uploadPath);
							fileName=fileName.substring(fileName.lastIndexOf("."));//獲取從.開始到最後的字元 
							//將時間轉化為字串用於給檔案或者資料夾改名,防止傳上來的圖片名稱相同 
							Date time=new Date(); 
							String dirTime=String.valueOf(time.getTime()); 
							// 
							BufferedInputStream in = new BufferedInputStream(fis.openStream());//獲得檔案輸入流 
							//BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File(saveDir+"/"+dirTime+fileName)));//獲得檔案輸出流 
							BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File(uploadPath+"/"+dirTime+fileName)));//獲得檔案輸出流 
							//pp為已經上傳的檔案 
							pp=uploadPath+dirTime+fileName; 
							System.out.println("pp="+pp);
							pp = dirTime+fileName;
							//upto為上傳的路徑 
							upto=uploadPath; 
							Streams.copy(in, out, true);//開始把檔案寫到你指定的上傳資料夾 
						} else {
							InputStream stream = fis.openStream();
							String fieldname = fis.getFieldName();
							String value = Streams.asString(stream);
							switch (fieldname) {
								case "memberName":
									memberName = value;
									break;
								case "nameForTraining":
									nameForTraining = value;
									break;
								case "birthday":
									birthday = value;
									break;
								case "idNumber":
									idNumber = value;
									break;
							}
						}
				} 
//				response.getWriter().println("File upload successfully!!!");//終於成功了,還不到你的上傳檔案中看看,你要的東西都到齊了嗎 
				System.out.println("File upload successfully!");
				return pp;
			} 
		}catch(Exception e){ 
			e.printStackTrace();
		}
		
		return fileName;
	}
	
	public void initFileUpload() throws ServletException {//初始化,沒什麼意義 
		
		String tmpPath ="/Users/LaurenChen\\ 1/Desktop/aiot02_final_project/final_project_web/aiot_final_project/WebContent/tmpdir"; 
		String savePath ="/Users/LaurenChen\\ 1/Desktop/aiot02_final_project/final_project_web/aiot_final_project/WebContent/updir"; 
		tmpDir = new File(tmpPath); 
		saveDir = new File(savePath); 
		if(!tmpDir.isDirectory()) 
		tmpDir.mkdir(); 
		if(!saveDir.isDirectory()) 
		saveDir.mkdir(); 
	}
}

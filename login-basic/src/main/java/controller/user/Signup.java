package controller.user;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.io.File;
import java.util.*;

import org.mindrot.jbcrypt.BCrypt;


import model.user.User;
import model.user.UserDAO;
import model.user.UserMySQL;
import util.Base64Img;
import util.Config;
import util.FileUtil;
/**
 * Servlet implementation class Signup
 */
@WebServlet("/signup")
@MultipartConfig(
		  fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
		  maxFileSize = 1024 * 1024 * 5,      // 5 MB
		  maxRequestSize = 1024 * 1024 * 10   // 10 MB
		)
public class Signup extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO;
	private String uploadPath; 
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Signup() {
        super();
        //this.userDAO = new UserMySQL();
        // TODO Auto-generated constructor stub
    }
    @Override
    public void init() {
    	userDAO = new UserMySQL();
    	uploadPath = Config.getConfig().get("UPLOADS");
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatch = request.getRequestDispatcher("signup.jsp");
		Map<String, String> formErr = new HashMap<>();
		request.setAttribute("formErr", formErr);
		

		
     String imgName= uploadPath+"/default-avatar.jpg";
     
     String base64Image = Base64Img.base64Img(imgName);
        

		request.setAttribute("base64Image", base64Image);
		dispatch.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		Part filePart = request.getPart("avatar");
	    String fileName = filePart.getSubmittedFileName();
	    
	    
	    
	    for (Part part : request.getParts()) {
	    	if(part.getName().equals("avatar")) {
	    		String uniqueID = UUID.randomUUID().toString();
		        fileName = uniqueID+FileUtil.getExtension(fileName);
		        part.write(uploadPath + File.separator + fileName);
	    	}
	    }
	    
		RequestDispatcher dispatch = request.getRequestDispatcher("signup.jsp");
		Map<String, String> formErr = new HashMap<>();
		try {

			if(this.userDAO.findByEmail(email)!=null) {
				formErr.put("email", "This email is already used. Choose another email or login.");
				request.setAttribute("formErr", formErr);
				dispatch.forward(request, response);
				return;				
			}
			
			this.userDAO.insert(new User(email, BCrypt.hashpw(password,BCrypt.gensalt(9)),name,fileName ));
			response.sendRedirect("login");	
		}
		catch(Exception ex) {
			ex.printStackTrace();
			formErr.put("header", "Error on signup!");
			request.setAttribute("formErr", formErr);
			dispatch.forward(request, response);
		}		
	}

	
	       
	     
	

}

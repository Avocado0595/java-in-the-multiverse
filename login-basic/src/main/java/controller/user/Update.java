package controller.user;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import model.user.User;
import model.user.UserDAO;
import model.user.UserJWT;
import model.user.UserMySQL;
import util.Base64Img;
import util.Config;
import util.CookieUtil;
import util.FileUtil;
import util.JWTUtil;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.mindrot.jbcrypt.BCrypt;

@WebServlet("/update")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
		maxFileSize = 1024 * 1024 * 5, // 5 MB
		maxRequestSize = 1024 * 1024 * 10 // 10 MB
)
public class Update extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO;
	private final String uploadPath;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Update() {
		super();
		userDAO = new UserMySQL();
		uploadPath = Config.getConfig().get("UPLOADS");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		

		Cookie token = CookieUtil.isCookieExist(request);

		try {
			if (token == null)
				throw new Exception("null token");

			UserJWT userJWT = JWTUtil.verify(token.getValue());

			if (userJWT == null)
				throw new Exception("verify token fail");

			User user = userDAO.findByEmail(userJWT.getEmail());
			if (user == null)
				throw new Exception("null user");

			RequestDispatcher dispatcher = request.getRequestDispatcher("update.jsp");
			Map<String, String> formErr = new HashMap<>();
			request.setAttribute("formErr", formErr);
			
			request.setAttribute("user", user);
			String imgName = uploadPath + "/" + user.getAvatar();
			String base64Image = Base64Img.base64Img(imgName);

			request.setAttribute("base64Image", base64Image);
			
			dispatcher.forward(request, response);
			return;

		} catch (Exception e) {
			if (token != null) {
				token.setMaxAge(0);
				token.setValue("");
				response.addCookie(token);
			}
		}
		response.sendRedirect("login");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// TODO Auto-generated method stub
				String name = request.getParameter("name");
				String email = request.getParameter("email");
				Part filePart = request.getPart("avatar");
			    String fileName = filePart.getSubmittedFileName();
	
				RequestDispatcher dispatch = request.getRequestDispatcher("update.jsp");
				Map<String, String> formErr = new HashMap<>();
				try {
					User user = this.userDAO.findByEmail(email);
					if(user ==null)
						throw new Exception("Invalid user");
					
					user.setName(name);
					
					for (Part part : request.getParts()) {
				    	if(part.getName().equals("avatar")) {
				    		if(part.getSize()!=0)
				    			
				    			part.write(uploadPath + File.separator + user.getAvatar());
				    	}
				    }
					
					this.userDAO.update(user);
					response.sendRedirect("login");	
				}
				catch(Exception ex) {
					ex.printStackTrace();
					formErr.put("header", "Error on update!");
					request.setAttribute("formErr", formErr);
					dispatch.forward(request, response);
				}	
	}

}

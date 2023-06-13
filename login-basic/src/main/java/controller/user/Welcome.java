package controller.user;


import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


import model.user.*;
import util.Base64Img;
import util.Config;
import util.CookieUtil;
import util.JWTUtil;

/**
 * Servlet implementation class Welcome
 */
@WebServlet(urlPatterns= {"/welcome"})
public class Welcome extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Welcome() {
        super();
        // TODO Auto-generated constructor stub
        this.userDAO = new UserMySQL();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie token = CookieUtil.isCookieExist(request);
		
		try {
			if(token==null)
				throw new Exception("null token");
			
			UserJWT userJWT = JWTUtil.verify(token.getValue());
			
			if(userJWT==null)
				throw new Exception("verify token fail");
		
			User user = userDAO.findByEmail(userJWT.getEmail());
			if(user==null)
				throw new Exception("null user");

			request.setAttribute("user",user);
			String imgName= Config.getConfig().get("UPLOADS")+"/"+user.getAvatar(); 
			String base64Image = Base64Img.base64Img(imgName);
			        
			request.setAttribute("base64Image", base64Image);
			RequestDispatcher dispatcher = request.getRequestDispatcher("welcome.jsp");
			dispatcher.forward(request, response);
			return;
			
		} catch (Exception  e) {
			if(token!=null) {
				token.setMaxAge(0);
				token.setValue("");
				response.addCookie(token);
			}
		}
		response.sendRedirect("login");	
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie token = CookieUtil.isCookieExist(request);
		if(token!=null) {
			token.setMaxAge(0);
			token.setValue("");
			response.addCookie(token);
		}
		
		response.sendRedirect("login");
	}

}

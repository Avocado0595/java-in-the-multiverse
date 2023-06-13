package controller.user;


import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

import org.mindrot.jbcrypt.BCrypt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import model.user.*;
import util.AlgorithmJWT;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        userDAO = new UserMySQL();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie[] c = request.getCookies();
		if(c!=null) {
			for(Cookie i:c) {
				if(i.getName().equals("token")&&i.getValue()!=""){ 
					response.sendRedirect("welcome");
					return;
				}
			}	
		}
		Map<String,String> formErr = new HashMap<>();
		request.setAttribute("formErr", formErr);
		RequestDispatcher dispatch = request.getRequestDispatcher("login.jsp");
		dispatch.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		RequestDispatcher dispatch = request.getRequestDispatcher("login.jsp");
		Map<String,String> formErr = new HashMap<>();
		
		User user = null;
		try {
			user = userDAO.findByEmail(email);
			if(user==null) {
				formErr.put("email", "Email not exist!");
				request.setAttribute("formErr", formErr);
				dispatch.forward(request, response);
				return;
			}
			else {
				if(BCrypt.checkpw(password, user.getPassword())) {
					Algorithm algorithm = AlgorithmJWT.getAlgorithm();	
					String name = user.getName();
					Map<String,String> payload = new HashMap<String, String>();
					payload.put("email",email);
					payload.put("name",name);
					String token = JWT.create()
					       .withIssuer("https://login.com")
					       .withPayload(payload)
					       .sign(algorithm);
					response.addCookie(new Cookie("token", token));			
					response.sendRedirect("welcome");
					return;								
				}
				else {
					formErr.put("password", "wrong password");
					request.setAttribute("formErr", formErr);
					dispatch.forward(request, response);
					return;
				}
			}
		} catch (Exception e) {
		e.printStackTrace();
			formErr.put("header", "Error on login!");
			request.setAttribute("formErr", formErr);
			dispatch.forward(request, response);
		}
		
		
	
		
	}
	

}

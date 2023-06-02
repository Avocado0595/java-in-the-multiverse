

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie[] c = request.getCookies();
		if(c!=null) {
			for(Cookie i:c) {
				if(i.getName().equals("name")&&i.getValue()!="")
				{
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
		String name = request.getParameter("name");
		String password = request.getParameter("password");

		RequestDispatcher dispatch = request.getRequestDispatcher("login.jsp");
		Map<String,String> formErr = new HashMap<>();
		
		User user = UserMockup.findUserByName(name);
		if(user==null) {
			formErr.put("name", "user not exist!");
			request.setAttribute("formErr", formErr);
			dispatch.forward(request, response);
			return;
		}
		if(user!=null) {
			if(user.getPassword().equals(password)) {
				response.addCookie(new Cookie("name", name));				
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
	
		
	}
	

}

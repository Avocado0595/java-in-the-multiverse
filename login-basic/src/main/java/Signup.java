

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
 * Servlet implementation class Signup
 */
@WebServlet("/signup")
public class Signup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Signup() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatch = request.getRequestDispatcher("signup.jsp");
		Map<String, String> formErr = new HashMap<>();
		request.setAttribute("formErr", formErr);
		dispatch.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name = request.getParameter("name");
		String fullname = request.getParameter("fullname");
		int age = request.getParameter("age")!=""?Integer.parseInt(request.getParameter("age")):-1;
		String password = request.getParameter("password");
		
		RequestDispatcher dispatch = request.getRequestDispatcher("signup.jsp");
		Map<String, String> formErr = new HashMap<>();
		
			if(UserMockup.findUserByName(name)!=null) {
				formErr.put("name", "This user name is existed. Choose another name.");
				request.setAttribute("formErr", formErr);
				dispatch.forward(request, response);
				return;				
			}
			UserMockup.addUser(new User(name, password, fullname, age));
			response.addCookie(new Cookie("name", name));
			response.sendRedirect("welcome");
		
		
	}

}

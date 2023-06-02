

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

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
		
		response.setContentType("text/html");
		PrintWriter out =  response.getWriter();
		loginForm(out);
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		PrintWriter out =  response.getWriter();
		response.setContentType("text/html");
		
		if(name==null || name =="" || password==null || password=="") {			
			out.println("<p style='color:red'>Can not leave anything blank!</p>");
			loginForm(out);
			out.close();
			
			return;
		}
		
			if(UserMockup.findUser(new User(name, password))) {
				response.addCookie(new Cookie("name", name));
				response.sendRedirect("welcome");
				return;				
			}
		
		out.println("<p style='color:red'>Login fail!\nLogin again!</p>");
		loginForm(out);
		out.close();
		
	}
	private void loginForm(PrintWriter out) {
		out.println("<h1>Login form</h1>");
		out.println("<form action='' method='post'>"+
				"name: <input name='name' type='text'/>"+
				"password: <input name='password' type='password'/>"+
				"<button type='submit'>Login</button>"
				+ "</form>");
		out.println("<p>Don't have any acount? <a href='signup'>Sign up here</a></p>");
	}

}

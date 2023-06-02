

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class Welcome
 */
@WebServlet(urlPatterns= {"/","/welcome"})
public class Welcome extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Welcome() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie[] c = request.getCookies();
		if(c==null || c.length==0) {
			response.sendRedirect("login");
			return;
		}
		for(Cookie i:c) {
			if(i.getName().equals("name")&&i.getValue()!="")
			{
				response.setContentType("text/html");
				PrintWriter out =  response.getWriter();
				out.println("<h1>Welcome "+i.getValue()+"!</h1>");
				out.println("<form action='' method='post'><button type='submit'>Logout</button></form>");
				out.close();
				return;
			}
		}
		response.sendRedirect("login");

		
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie[] c = request.getCookies();
		if(c==null || c.length==0) {
			response.sendRedirect("login");
			return;
		}
		for(Cookie i:c) {
			if(i.getName().equals("name"))
			{
				
				i.setMaxAge(0);
				i.setValue("");
				response.addCookie(i);
			}
		}
		response.sendRedirect("login");

	}

}

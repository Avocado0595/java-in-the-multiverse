
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		signupForm(out);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");

		if (name == null || name == "" || password == null || password == "") {
			out.println("<p style='color:red'>Can not leave anything blank!</p>");
			signupForm(out);
			out.close();

			return;
		}

		if (UserMockup.findUserByName(name)) {
			out.println("<p style='color:red'>User existed! Choose an other name!</p>");
			signupForm(out);
			out.close();
			return;
		}
		UserMockup.addUser(new User(name, password));
		response.addCookie(new Cookie("name", name));
		response.sendRedirect("welcome");

	}

	private void signupForm(PrintWriter out) {
		out.println("<h1>SignUp form</h1>");
		out.println("<form action='' method='post'>" +
				"name: <input name='name' type='text'/>" +
				"password: <input name='password' type='password'/>" +
				"<button type='submit'>Create my account</button>"
				+ "</form>");
	}

}

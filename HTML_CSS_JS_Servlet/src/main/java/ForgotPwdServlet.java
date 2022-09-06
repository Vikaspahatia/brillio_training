

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;

/**
 * Servlet implementation class ForgotPwdServlet
 */
public class ForgotPwdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ForgotPwdServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		String pass = request.getParameter("password");
		System.out.println(id);
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/prescripty", "root", "0000");
			
			String sql = "update login set password = ?, firstLogin = 0 where id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, pass);
			stmt.setString(2, id);
			
			int rs = stmt.executeUpdate();
			PrintWriter out = response.getWriter();
			System.out.println(rs);
			if(rs == 1) {
				out.print("Password changed successfuly!");
			}
			else {
				out.print("Failed!");
			}

		}
		catch(Exception e) {
			System.err.println(e.getMessage());
		}
	}

}

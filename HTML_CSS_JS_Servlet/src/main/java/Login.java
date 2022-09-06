
import java.time.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
/**
 * Servlet implementation class Login
 */
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
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
		String pass = request.getParameter("password");
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/prescripty", "root", "0000");
			
			
			
			String sql = "select * from login where username = ? and password = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, username);
			stmt.setString(2, pass);
			
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				System.out.println(rs.getString(1) + " " + rs.getString(2) +  " " + rs.getString(3));
				
				String changePwdLink = "<a href='changePwd.html?id=" + rs.getString(1) +  "'>Click here to change your password.</a>";
				LocalDateTime dateTime = LocalDateTime.now();
				
				if(rs.getInt(4) == 1) {
					response.setContentType("text/html");
					response.getWriter().append("Congratulations! You are now logged in.<br>Current login time: " + dateTime +  "<br>You have logged in for the first time.<br>" + changePwdLink);					
				}
				else {
					response.getWriter().append("Congratulations! You are now logged in. \nCurrent login time: " + dateTime );
				}
			}
			else {
				response.getWriter().append("Email and passwords do not match to the database.");
			}
			
		}
		catch(Exception e) {
			System.err.println(e.getMessage());
		}
		
	}

}

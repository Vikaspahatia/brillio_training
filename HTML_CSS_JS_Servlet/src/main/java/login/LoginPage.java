package login;
import java.sql.*;

public class LoginPage {
	String id;
	String username;
	String password;
	
	public LoginPage(String id, String username, String password) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public int saveToDB() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/prescripty", "root", "0000");
			
			String sql = "insert into login values(?, ?, ?, 1)";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, this.id);
			stmt.setString(2, this.username);
			stmt.setString(3, this.password);
			
			return stmt.executeUpdate();	
		} catch(Exception e) {
			System.err.println(e.getMessage());
			return 0;
		}	
	}	
}

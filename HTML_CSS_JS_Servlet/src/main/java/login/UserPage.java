package login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Date;
import java.text.SimpleDateFormat;

public class UserPage {
	String firstName;
	String lastName;
	String email;
	String idp;
	String mobile;
	String dateOfBirth;
	Date dob;
	
	
	
	public UserPage(String firstName, String lastName, String email, String idp, String mobile, String dob) throws Exception {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.idp = idp;
		this.mobile = mobile;
		this.dateOfBirth = dob;
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
		java.util.Date date = sdf1.parse(this.dateOfBirth);
		this.dob = new java.sql.Date(date.getTime()); 
		
	}

	public String getfirstName() {
		return firstName;
	}
	public void setfirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getlastName() {
		return lastName;
	}
	public void setlastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getIdp() {
		return idp;
	}
	public void setIdp(String idp) {
		this.idp = idp;
	}

	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	
	public String getmobile() {
		return mobile;
	}
	public void setmobile(String mobile) {
		this.mobile = mobile;
	}
	
	private static String getVowels(String s) {
		String vow="";
		for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'a' || s.charAt(i) == 'e'
                || s.charAt(i) == 'i' || s.charAt(i) == 'o'
                || s.charAt(i) == 'u' || s.charAt(i) == 'A'
                || s.charAt(i) == 'E' || s.charAt(i) == 'I'
                || s.charAt(i) == 'O' || s.charAt(i) == 'U') {
                vow+=s.charAt(i);
            } else {
                continue;
            }
        }
		return vow;
	}
	
	private static String getOdd(String s) {
		String odd = "";
		for(int i=0; i<s.length(); i+=2) {
			odd += s.charAt(i);
		}
		
		return odd;
	}
	
	private static int getSumOfDigits(String s) {
		int phoneSum = 0; 
	    for(int i=0;i<s.length();i++) {
	    	if(Character.isDigit(s.charAt(i)))
	    		phoneSum+=s.charAt(i)-'0';
	    }
	    return phoneSum;
	}
	
	public LoginPage generateCred() {
		String username = this.firstName + this.mobile.substring(this.mobile.length() - 4);
		
		String vowels = UserPage.getVowels(this.idp);
		String oddmobile = UserPage.getOdd(this.mobile);
		int dobSum = UserPage.getSumOfDigits(this.dateOfBirth);
		int idpSum = UserPage.getSumOfDigits(this.idp);
		
		String password = this.lastName.substring(0, 1) + this.firstName.substring(this.firstName.length() - 1) + oddmobile + vowels + (dobSum + idpSum);
		
		return new LoginPage(this.idp, username, password);
		
	}
	
	public int saveToDB() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/prescripty", "root", "0000");
			
			String sql = "insert into user values(?, ?, ?, ?, ?, ?)";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, this.firstName);
			stmt.setString(2, this.lastName);
			stmt.setString(3, this.email);
			stmt.setString(4, this.idp);
			stmt.setString(5, this.mobile);
			stmt.setDate(6, this.dob);
			
			return stmt.executeUpdate();
			
			
		}
		catch(Exception e) {
			System.err.println(e.getMessage());
			return 0;
		}
		
	}
	
	
	
	
}

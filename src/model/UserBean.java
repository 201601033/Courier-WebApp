package model;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletContext;

import java.sql.Blob;
import java.sql.Connection;

public class UserBean {
	private String email;
	private String password; //while we do not have AD access yet
	private String firstName;
	private String lastName;
	private Blob profileImage;
	private int roleID;
	private ServletContext context;
	private static Connection connection;
	/*
	 *  1 - student
	 *  2 - professor
	 *  3 - administrator
	 *  4 - system administrator
	 */
	public UserBean()
	{
		
	}

	public UserBean(String email, String password, String firstName, String lastName, Blob profileImage, int roleID) {
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.profileImage = profileImage;
		this.roleID = roleID;
		
	}
	
	public UserBean(String email, String password, String firstName, String lastName, int roleID) {
		super();
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.roleID = roleID;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Blob getProfileImage() {
		return profileImage;
	}
	public void setProfileImage(Blob profileImage) {
		this.profileImage = profileImage;
	}
	public int getRoleID() {
		return roleID;
	}
	public void setRoleID(int roleID) {
		this.roleID = roleID;
	}
	public void setServletContext(ServletContext servletContext) {
		this.context = servletContext;
	}
	private static void getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/courierportaldb", "root", "");
			if(connection != null) {
				//context.setAttribute("dbConn", connection);
			}
		}catch(ClassNotFoundException cfne) {
		//	context.log(cfne.getMessage());
		}catch(SQLException sqle) {
		//	context.log(sqle.getMessage());
		}
	}
	public static void initializeConn() {
		if(connection == null)
		{
			getConnection();
		}
	}
	public static boolean validation(String email, String password)throws SQLException{
			PreparedStatement ps = connection.prepareStatement("select * from users where email = ? and password = ?");
			boolean isAuthenticated = false;
			//UserBean authenticatedUser = null;
			ps.setString(1, email);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
		
				/*this.setEmail(rs.getString("email"));
				this.setPassword(rs.getString("password"));
				this.setFirstName(rs.getString("firstname"));
				this.setLastName(rs.getString("lastname"));
				this.setProfileImage(rs.getBlob("profileImage"));
				this.setRoleID(rs.getInt("roleid"));*/
				isAuthenticated = rs.next();
				//System.out.println(isAuthenticated);
		
			System.out.println(isAuthenticated);
			return isAuthenticated;
			
			
	}
	public  UserBean convertRowToUser(String email, String password) throws SQLException{
		UserBean user = null;
		PreparedStatement ps = connection.prepareStatement("select * from users where email = ? and password = ?");
		ps.setString(1, email);
		ps.setString(2,password);
		ResultSet rs = ps.executeQuery();
		while(rs.next())
		{
			user = new UserBean();
			user.setEmail(rs.getString("email"));
			user.setPassword(rs.getString("password"));
			user.setFirstName(rs.getString("firstname"));
			user.setLastName(rs.getString("lastname"));
			user.setProfileImage(rs.getBlob("profileImage"));
			user.setRoleID(rs.getInt("roleid"));
			System.out.println(user.getEmail());
		}
		connection.close();
		return user;
	}
	public static UserBean retrieveUser(String email)throws SQLException {
		UserBean retrievedUser = null;
		ResultSet record = null;
		String sql = "select * from users where email= ?";
		Connection conn = connection;
		if(conn == null)
		{
			initializeConn();
		}
		try {
			PreparedStatement pstmnt = 
				conn.prepareStatement(sql);
			
			pstmnt.setString(1, email);
			record = pstmnt.executeQuery();
			
			while (record.next()) {
				retrievedUser = new UserBean();
				retrievedUser.setEmail(record.getString("email"));
				retrievedUser.setFirstName(record.getString("firstname"));
				retrievedUser.setLastName(record.getString("lastname"));
				retrievedUser.setProfileImage(record.getBlob("profileImage"));
				retrievedUser.setRoleID(record.getInt("roleID"));
				System.out.println("User found!");
			}	
		} catch (SQLException sqle) {
			System.err.println(sqle.getMessage());
			System.err.println("User not found!");
		}
		return retrievedUser;
	}
}

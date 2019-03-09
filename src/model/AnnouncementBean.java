package model;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;

public class AnnouncementBean {
	private String email;
	private String announcementBody;
	private Blob file;
	private ArrayList<String> tags;
	private Date dateposted;
	private Time timeposted;
	private static Connection connection;
	public AnnouncementBean()
	{
		
	}
	public AnnouncementBean(String email, String announcementBody,  ArrayList<String> tags, Date dateposted)
	{
		this.email = email;
		this.announcementBody = announcementBody;
		this.tags = tags;
		this.dateposted = dateposted;
	}
	public AnnouncementBean(String email, String announcementBody, Blob file, ArrayList<String> tags,Date dateposted)
	{
		this.email = email;
		this.announcementBody = announcementBody;
		this.file = file;
		this.tags = tags;
		this.dateposted = dateposted;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAnnouncementBody() {
		return announcementBody;
	}
	public void setAnnouncementBody(String announcementBody) {
		this.announcementBody = announcementBody;
	}
	public Blob getFile() {
		return file;
	}
	public void setFile(Blob file) {
		this.file = file;
	}
	public ArrayList<String> getTags() {
		return tags;
	}
	public void setTags(ArrayList<String> tags) {
		this.tags = tags;
	}
	public Date getDatePosted() {
		return dateposted;
	}
	public void setDatePosted(Date dateposted) {
		this.dateposted = dateposted;
	}
	
	public Time getTimeposted() {
		return timeposted;
	}
	public void setTimeposted(Time timeposted) {
		this.timeposted = timeposted;
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
	public void submitAnnouncement()throws SQLException{
		String sql = "INSERT INTO `announcements` (`email`, `announcementBody`,`file`, `tags`, `dateposted`, `timeposted`) VALUES (?, ?, ?, ?, ?,?);";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setString(1, this.email);
		ps.setString(2, this.announcementBody);
		ps.setBlob(3, this.file);
		StringBuilder allTags = new StringBuilder();
		for(String s : this.tags)
		{
			allTags.append(s);
			allTags.append(",");
			
		}
		String allTagsCSV = allTags.toString();
		allTagsCSV = allTagsCSV.substring(0, allTagsCSV.length() -  ",".length());
		ps.setString(4, allTagsCSV);
		ps.setDate(5, utilToSQLDate(new java.util.Date()));
		ps.setTime(6, utilToSQLTime(new java.util.Date()));
		ps.executeUpdate();
	}
	private static java.sql.Date utilToSQLDate(java.util.Date date){
		java.sql.Date sqldate  = new java.sql.Date(date.getTime());
		return sqldate;
	}
	private static java.sql.Time utilToSQLTime(java.util.Date date){
		java.sql.Time sqltime= new java.sql.Time(System.currentTimeMillis());
		return sqltime;
	}
	
}

package model;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.sql.ResultSet;

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
	public AnnouncementBean(String email, String announcementBody,  ArrayList<String> tags)
	{
		this.email = email;
		this.announcementBody = announcementBody;
		this.tags = tags;
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
		ArrayList<String>tempTags = new ArrayList<String>();
		ps.setString(1, this.email);
		ps.setString(2, this.announcementBody);
		ps.setBlob(3, this.file);
		if((this.tags).size() >=1) {
			tempTags.add((this.tags).get(0).toString());
			
		}
		for(int i = 1; i < tempTags.size(); i++) {
			tempTags.add(", " + tempTags.get(i));
		}
		String arrayToString = "";
		for(String s : tempTags)
		{
			arrayToString += s;
		}
		ps.setString(4, arrayToString);
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
	public static ResultSet getAllAnnouncements()throws SQLException{
		ResultSet rs = null;
		String sql = "SELECT a.id AS \"id\",a.email AS \"email\", CONCAT(u.firstName,  \" \", u.lastName) as \"name\", a.announcementBody AS \"announcementBody\", a.file AS \"FILE\", a.tags AS \"TAGS\", a.dateposted AS \"datePosted\", a.timeposted AS \"timePosted\"\r\n" + 
				"FROM announcements a, users u\r\n" + 
				"WHERE a.email = u.email\r\n" + 
				"ORDER BY dateposted, timeposted;";
		PreparedStatement ps = connection.prepareStatement(sql);
		rs = ps.executeQuery();
		return rs;
	}
	public static ResultSet getAnnouncement(int id){
		AnnouncementBean announcement = null;
		ResultSet record = null;
		String sql = "SELECT a.id AS \"id\",a.email AS \"email\", CONCAT(u.firstName,  \" \", u.lastName) as \"name\", a.announcementBody AS \"announcementBody\", a.file AS \"FILE\", a.tags AS \"TAGS\", a.dateposted AS \"datePosted\", a.timeposted AS \"timePosted\"\r\n" + 
				"FROM announcements a, users u\r\n" + 
				"WHERE a.email = u.email\r\n AND id = ?";
		try{
			PreparedStatement pstmnt = connection.prepareStatement(sql);
			pstmnt.setInt(1, id);
			record = pstmnt.executeQuery();
			
			/*while(record.next()){
				announcement = new AnnouncementBean();
				
				announcement.setAnnouncementBody(record.getString("announcementBody"));
				announcement.setEmail(record.getString("email"));
				announcement.setDatePosted(record.getDate("datePosted"));
				announcement.setTimeposted(record.getTime("timePosted"));
			}*/
		}catch(SQLException sqle)
		{
			System.err.println(sqle.getMessage());
		}
		return record;
		
	}
	
}

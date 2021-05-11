package com.tweetApp.service;

import java.io.IOException;
import java.sql.Connection;
//import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import com.tweetApp.App;
import com.tweetApp.dao.TwitterDao;
import com.tweetApp.model.TweetModel;
import com.tweetApp.model.UserModel;


public class TwitterService {

	static Scanner scan=new Scanner(System.in);
	static UserModel user=new UserModel();	
	Statement stmt=null;
	static Connection connection = null;
	TweetModel tweets1=new TweetModel();
	
	
	public void insert(UserModel user) throws SQLException,ClassNotFoundException, IOException, ParseException {
		connection = TwitterDao.getConnection();
		
		System.out.println("Enter the details");
		System.out.println("Enter UserId/EmailId");
		String userid=user.getUserid();
		if(scan.hasNext("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$"	)) {
				userid=scan.nextLine();
		}
	else {
			System.out.println("please enter a valid mail id");
			insert(user);
		}
		
		System.out.println("Enter Firstname ");
		String fname=user.getFirstName();
		if(scan.hasNext("[A-Za-z]*")) {
			fname=scan.nextLine();
		}else {
			System.out.println("enter valid name");
			
		}
		
		System.out.println("Enter Lastname ");
		String lname=user.getLastName();
		if(scan.hasNext("[A-Za-z]*")) {
			lname=scan.nextLine();
		}else {
			System.out.println("enter valid last name");
		}

		System.out.println("Enter gender ");
		String gender=user.getGender();
		gender=scan.nextLine();

		System.out.println("Enter Password ");
		String pwd=user.getPassword();
//		if(scan.hasNext("^(?=.*\\d)(?=\\S+$)(?=.*[@#$%^&+=])(?=.*[a-z])(?=.*[A-Z]).{8,10}$")) {
		pwd=scan.nextLine();
//		}
//		else {
//			System.out.println("enter strong password");
//		}
		
		System.out.println("Enter Date of birth");
		String dob1=scan.next();
		Date dob=(Date) new SimpleDateFormat("dd-mm-yyyy").parse(dob1);
		
		System.out.println("Inserting records into the table");
		String sql="insert into user(userid,fname,lname,gender,pwd,dob) values(?,?,?,?,?,?)";

		PreparedStatement ps=connection.prepareStatement(sql);		
		ps.setString(1,userid);
		ps.setString(2,fname);
		ps.setString(3,lname);
		ps.setString(4,gender);
		ps.setString(5,pwd);
		ps.setString(6,dob1);

		int rowInserted=ps.executeUpdate();
		if(rowInserted>0) {
			System.out.println("New user has been added successfully");
		}

	}
	
	
	public void postNewTweet(String userid2, String tweet) throws SQLException,ClassNotFoundException, IOException {
//		
		connection=TwitterDao.getConnection();
		System.out.println("Post your tweet");
		tweet=tweets1.getTweet();
		tweet=scan.nextLine();
		
//		String sql=";
		PreparedStatement ps=connection.prepareStatement("insert into tweeter(userid,tweets) values (?,?)");	
		ps.setString(1,userid2);
		ps.setString(2,tweet);
		int result=ps.executeUpdate();
		System.out.println("Posted successfully");

		
	}
	public void getAllTweet() throws SQLException,ClassNotFoundException, IOException {
		connection=TwitterDao.getConnection();
		PreparedStatement ps=connection.prepareStatement("Select * from tweeter");
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
			tweets1.setUserid(rs.getString("userid"));
			tweets1.setTweet(rs.getString("tweets"));
			System.out.println(tweets1.getUserid());
			System.out.println(tweets1.getTweet());
			
	}
	
	}
	
	public void viewMyTweet(String userid) throws SQLException,ClassNotFoundException, IOException {
		
		connection=TwitterDao.getConnection();
		PreparedStatement ps=connection.prepareStatement("Select * from tweeter where userid=?");
		ps.setString(1,userid);
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
			
			tweets1.setUserid(rs.getString("userid"));
			tweets1.setTweet(rs.getString("tweets"));
			System.out.println(tweets1.getTweet());
		}
	}
            
	
	

	public void viewAllUsers() throws SQLException,ClassNotFoundException, IOException {
		connection=TwitterDao.getConnection();
		PreparedStatement ps=connection.prepareStatement("Select * from user");
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
			user.setUserid(rs.getString("userid"));
			user.setFirstName(rs.getString("fname"));
			user.setLastName(rs.getString("lname"));
			System.out.print((user.getUserid())+ "  ");
			System.out.println(user.getFirstName());
			System.out.println(user.getLastName());
		}
		
	}
	
	public void logout(String userid)  throws SQLException,ClassNotFoundException, IOException {
		connection=TwitterDao.getConnection();
		System.out.println("you logged out successfully");
		
		
	}
	public void forgotPassword(String userid) throws SQLException,ClassNotFoundException, IOException {
		connection=TwitterDao.getConnection();
		System.out.println("Enter your email/userid");
		userid=scan.nextLine();
		PreparedStatement ps=connection.prepareStatement("Select * from user where userid=?");
		ps.setString(1, userid);
		ResultSet rs=ps.executeQuery();
		if(rs.next()) {
			user.setUserid(rs.getString("userid"));
			System.out.println("Enter your old password");
			String oldPassword=scan.nextLine();
			System.out.println("Enter your new password");
			String newPassword=scan.nextLine();
			PreparedStatement ps2=connection.prepareStatement("update user set pwd=? where userid=? ");
			ps2.setString(1, newPassword);
			ps2.setString(2,userid);
			int rs2=ps2.executeUpdate();
			System.out.println("password updated successfully");
			
		}
		
	}
	

}


//
//	try (Connection connection= TwitterDao.getConnection()) {
////		String sql="Select * from tweeter where userid=?";
//		try (PreparedStatement ps=connection.prepareStatement("Select * from tweeter where userid=?")){
//			 SQL.stream(stmt, Unchecked.function(rs ->new Schema(
//					rs.getString("userid"),
//					rs.getString("tweets")
//					)
//				))
//			 .forEach(System.out::println);
//		}		 
//		}
//	}


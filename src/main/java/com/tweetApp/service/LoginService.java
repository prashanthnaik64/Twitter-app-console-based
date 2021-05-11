package com.tweetApp.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.tweetApp.App;
import com.tweetApp.dao.TwitterDao;
import com.tweetApp.model.UserModel;

public class LoginService {

	public boolean validateUser(String userid, String password) {
		Scanner scan=new Scanner(System.in);
		UserModel user=new UserModel();	
		Statement stmt=null;
		PreparedStatement ps=null;
		Connection connection = null;
		TwitterService service=new TwitterService();
		try {
			connection = TwitterDao.getConnection();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Enter Email/userId");
		userid=scan.nextLine();
		System.out.println("Enter password");
		password=scan.nextLine();
		boolean userIdExists=true;
		try {
			PreparedStatement st = connection.prepareStatement("select * from user where userid=? and pwd=?");
			st.setString(1,userid);
			st.setString(2, password);
			ResultSet rs=st.executeQuery();
			if(rs.next()) {
				user.setFirstName(rs.getString("fname"));
				user.setLastName(rs.getString("lname"));
				user.setUserid(rs.getString("userid"));				
				System.out.println("User logged successfully");
				System.out.println("Enter your choice");
				while(true) {
    			System.out.println("1.Post a tweet");
    			System.out.println("2.get all tweets");
    			System.out.println("3.View my tweets");
    			System.out.println("4.View all users");
    			System.out.println("5.Reset password");
    			System.out.println("6.Logout");
    			int ch=scan.nextInt();
    			switch(ch) {
    			case 1:String tweet=null;
    				service.postNewTweet(userid, tweet);
    				break;
    			
    			case 2:service.getAllTweet();
    				break;
    			
    			case 3:service.viewMyTweet(userid);
    				break;
    				
    			case 4:service.viewAllUsers();
    				break;
    				
    			case 5:service.forgotPassword(userid);
    				break;
    			
    			case 6:service.logout(userid);
    			App a=new App();
    			a.main(null);
    				break;
    			}
    			
			}
			}
			
			else {
				System.out.println("Invalid user");
				System.out.println("Enter valid credentials or Register");
				validateUser(userid,password);	
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
        
		return userIdExists;
		
	}
}

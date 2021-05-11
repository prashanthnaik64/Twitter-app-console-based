package com.tweetApp;

import java.util.Scanner;

import com.tweetApp.model.TweetModel;
import com.tweetApp.model.UserModel;
import com.tweetApp.service.LoginService;
import com.tweetApp.service.TwitterService;

public class App 
{
    public static void main( String[] args ) throws Exception
    {
    	Scanner scan=new Scanner(System.in);
    	TwitterService service=new TwitterService();
    	UserModel user=new UserModel();
    	TweetModel tweets=new TweetModel();
    	LoginService ls=new LoginService();

    	while(true) {
    	System.out.println("Select you Choice");
    	System.out.println("______________________");
    	System.out.println("1.REGISTER");
    	System.out.println("2.LOGIN");
    	System.out.println("3.FORGOT PASSWORD");
    	int option=scan.nextInt();
    	switch(option) {
    	case 1:System.out.println("register");
    		service.insert(user);
    		break;
    		
    	case 2:System.out.println("login");
    		boolean isLogin=ls.validateUser(user.getUserid(),user.getPassword());
    		break;	
    		
    	case 3:System.out.println("Forgot Password");
    		service.forgotPassword(user.getUserid());
    		break;
    	}
    
    	}
    }
}




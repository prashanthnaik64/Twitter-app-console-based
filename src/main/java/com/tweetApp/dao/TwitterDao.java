package com.tweetApp.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class TwitterDao {
	
	private static final String DB_DRIVER_CLASS="jdbc.driver";
	private static final String DB_USERNAME="jdbc.username";
	private static final String DB_PASSWORD="jdbc.password";
	private static final String DB_URL="jdbc.url";
	
	private static Connection connection=null;
	private static Properties properties=null;
	
	public static Connection getConnection() throws ClassNotFoundException, FileNotFoundException, IOException {
		try {
			properties=new Properties();
			properties.load(new FileInputStream("C:/Users/hp/Documents/p/TweetApp-main/Twitter/src/main/java/db.properties"));
//			
			Class.forName(properties.getProperty(DB_DRIVER_CLASS));
			connection=DriverManager.getConnection(properties.getProperty(DB_URL),properties.getProperty(DB_USERNAME),properties.getProperty(DB_PASSWORD));
			
		}catch(SQLException e){
			throw new RuntimeException("Error while connecting to db",e);
		}
		return connection;
	}
	
}

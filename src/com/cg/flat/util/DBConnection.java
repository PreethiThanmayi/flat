package com.cg.flat.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {

	public static Connection getConnection() throws ClassNotFoundException,SQLException,FileNotFoundException {
		try {
		FileInputStream fis=new FileInputStream("resources/db.properties");
		Properties p=new Properties();
		p.load(fis);
		
		String s1=p.getProperty("url");
		String s2=p.getProperty("username");
		String s3=p.getProperty("password");
		String s4=p.getProperty("driver");
		
		 Class.forName(s4);
		 Connection con=DriverManager.getConnection(s1,s2,s3);
		 return con;	
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return getConnection();
			
	}
}

package com.createiq.sms.Util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class ConnectionUtil {
	private static Properties properties = null;
	static {
		properties = new Properties();

		try {
			properties.load(ConnectionUtil.class.getClassLoader().getResourceAsStream("db.properties"));
			Class.forName(properties.getProperty("driver"));
		} catch (IOException | ClassNotFoundException e) {

		}

	}

	public static Connection getConnection() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(properties.getProperty("url"), properties.getProperty("uname"),
					properties.getProperty("pwd"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;

	}
	private static void close(Connection connection) {
		if(connection!=null) {
			try {
				connection.close();
				close(connection);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	private static void close(Statement statement , Connection connection) {
		if(statement!=null) {
			try {
				statement.close();
				close(connection);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
       private static void close(ResultSet rs , Statement statement , Connection connection) {
    	   if(rs!=null) {
    		   try {
				rs.close();
				close(statement,connection);
			} 
    		  
    		   catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	   }
       }
       
	public static void main(String args[]) {
		System.out.println(getConnection());
	}
}

package com.uttara.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCHelper {
	
	public static void close(ResultSet rs) {
		try {
			if(rs!=null)
				rs.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(Connection con) {
		try {
			if(con!=null)
				con.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(Statement stmt) {
		try {
			if(stmt!=null)
				stmt.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public static Connection getConnection() {
		try {
			Class.forName(Constants.DRIVER);
			
			Connection con = DriverManager.getConnection(Constants.URL,Constants.UID,Constants.PWD);
			return con;
		}
		catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}

package com.uttara.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class MovieMenu {

	public static void main(String[] args) {

		//we are trying to understand jdbc and creating a movie menu app performing crud operations.	
		Scanner sc1 = new Scanner(System.in);
		Scanner sc2 = new Scanner(System.in);
		
		
		int ch=0;
		String name, reason;
		double rating;
		
		Connection con = null;
		PreparedStatement ps_ins=null, ps_sel=null,ps_del=null,ps_upd=null;
		ResultSet rs = null;
		
		String sql;
		try {
		con = JDBCHelper.getConnection();
		if(con!=null) {
		
		sql = "insert into gabb_movies(name,reason,rating) values(?,?,?) ";
		ps_ins = con.prepareStatement(sql);
		
		sql = "update gabb_movies set reason = ?, rating = ? where name = ?";
		ps_upd = con.prepareStatement(sql);
		
		sql = "delete from gabb_movies where name like ?";
		ps_del = con.prepareStatement(sql);
		
		sql = "select * from gabb_movies";
		ps_sel = con.prepareStatement(sql);
		
		
		while(ch!=5) {
			System.out.println("Press 1 to insert gabb movie");
			System.out.println("Press 2 to update gabb movie");
			System.out.println("Press 3 to delete gabb movie");
			System.out.println("Press 4 to list gabb movie");
			System.out.println("Press 5 to exit");
			
			ch=sc1.nextInt();
			
			switch(ch)
			{
			case 1:
				System.out.println("enter gabb movie name");
				name = sc2.nextLine();
				System.out.println("enter reason of why you hate"+name);
				reason = sc2.nextLine();
                System.out.println("rate the movie");
                rating = sc2.nextDouble();
                
                ps_ins.setString(1,name);
                ps_ins.setString(2, reason);
                ps_ins.setDouble(3, rating);
                
                ps_ins.execute();
                
				break;
			case 2:
				System.out.println("enter gabb movie name you want ot update");
				name = sc2.nextLine();
				System.out.println("enter updated reason of why you hate "+name);
				reason = sc2.nextLine();
                System.out.println("rate the movie again");
                rating = sc2.nextDouble();
                
                ps_upd.setString(3,name);
                ps_upd.setString(1, reason);
                ps_upd.setDouble(2, rating);
                
                ps_upd.execute();
				break;
			case 3:
				System.out.println("enter gabb movie name you want to delete");
				name = sc2.nextLine();
				ps_del.setString(1, "%"+name+"%");
				ps_del.execute();
				
				break;
			case 4:
				ps_sel.execute();
				rs = ps_sel.getResultSet();
				System.out.println("the result set is ");
				while(rs.next()) {
					name = rs.getString("name");
					reason = rs.getString("reason");
					rating = rs.getDouble("rating");
					
					System.out.println("name : "+name+" reason : "+reason+" rating : "+rating);
				}
				break;
			case 5:
				break;
			default:
				break;	
				
			}
		}
		}
		else {
			System.out.println("oops no connection to Db");
		}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		catch(Exception e) {
			System.out.println("oops"+e.getMessage());
		}
		finally {
			JDBCHelper.close(rs);
			JDBCHelper.close(ps_ins);
			JDBCHelper.close(ps_sel);
			JDBCHelper.close(ps_upd);
			JDBCHelper.close(ps_del);
			JDBCHelper.close(con);
		}

	}

}

package com.uttara.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class StartCRUD {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Connection con = null;
		PreparedStatement ps_Sins=null,ps_marksIns=null,ps_selS=null;
		ResultSet rs = null;
		Scanner sc1 = new Scanner(System.in);
		Scanner sc2 = new Scanner(System.in);
		String name,email;
		String strDt;
		int marks;
		String std;
		try {
			con = JDBCHelper.getConnection();
			con.setAutoCommit(false);
			
			ps_Sins = con.prepareStatement("insert into student(name,email,dob) values (?,?,?)",Statement.RETURN_GENERATED_KEYS);
			ps_marksIns = con.prepareStatement("insert into student_marks(student_sl,std,marks) values(?,?,?)");
			
			System.out.println("Enter the name of student");
			name = sc2.nextLine();
			
			System.out.print("enter email");
			email = sc2.nextLine();
			
			System.out.println("enter the start date");
			strDt = sc2.nextLine();
			
			//date is taken as string and we cannot set string directly.
			//so while setting it should java.sql date and not string
			//and to do this, we should do it String to java.util. date and then further convert to java.sql.date
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			java.util.Date dt = sdf.parse(strDt);
			java.sql.Date sqlDt = new java.sql.Date(dt.getTime());//dt.getTime() give time in milliseconds from epoch time
			
			ps_Sins.setString(1,  name);
			ps_Sins.setString(2, email);
			ps_Sins.setDate(3, sqlDt);
			
			ps_Sins.execute();
			
			rs = ps_Sins.getGeneratedKeys();
			rs.next();
			int sl_no = rs.getInt(1);
			
			
			
			int ch = 0;
					while(ch !=2) {
						System.out.println("Press 1 to enter std marks");
						System.out.println("2 to go back");
						ch = sc1.nextInt();
						
						switch(ch) {
						case 1:
							System.out.println("enter standard");
							std = sc2.nextLine();
							System.out.println("enter marks of student as integer");
							marks = sc1.nextInt();
							
							ps_marksIns.setInt(1, sl_no);
							ps_marksIns.setString(2, std);
							ps_marksIns.setInt(3,marks);
							
							ps_marksIns.execute();
						case 2:
							
							break;
						}
					}
					con.commit();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			JDBCHelper.close(rs);
			JDBCHelper.close(ps_marksIns);
			JDBCHelper.close(ps_selS);
			JDBCHelper.close(ps_Sins);
			JDBCHelper.close(con);
			
		}

	}

}

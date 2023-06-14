package com.yedam;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectEx {
	public static void main(String[] args) {
		//jdbc driver 체크
		String url = "jdbc:oracle:thin:@localhost:1521/xe";
		String user = "proj";
		String pass ="proj";
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			Connection conn = DriverManager.getConnection(url,user,pass);
			
			//db의 쿼리를 실행하고 데이터를 처리한 결과를 조회 
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from tbl_users");//결과 담기
			while(rs.next()) {//next메소드 - 읽어들이는 역할 반복역할 
				System.out.println(rs.getString("user_id")+","+rs.getString("user_name")+","+rs.getDate("user_birth")+","+rs.getString("user_addr"));
			}
			System.out.println("end of data");
			conn.close();
			rs.close();
			stmt.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			
		}
	}
}

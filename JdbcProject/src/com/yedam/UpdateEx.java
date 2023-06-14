package com.yedam;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class UpdateEx {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		System.out.print("id> ");
		String id = sc.nextLine();
		System.out.print("pw> ");
		String pw = sc.nextLine();
		System.out.print("addr> ");
		String addr = sc.nextLine();
		
		
		// jdbc driver 체크
		String url = "jdbc:oracle:thin:@localhost:1521/xe";
		String user = "proj";
		String pass = "proj";
		
		Connection conn = null;
		PreparedStatement psmt =null;//Statement랑 같은데 더 편함
		String sql = "update tbl_users"
					 +"  set user_pw = ?,"//preparedStatement 지정방법
					 +"user_addr=?"
					 +"where user_id = ?";
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			conn=DriverManager.getConnection(url,user,pass);
			
			psmt = conn.prepareStatement(sql);
			
			//preparedStatement 지정방법
			psmt.setString(1, pw);
			psmt.setString(2, addr);
			psmt.setString(3, id);
			//---------------set타입(물음표 찍은곳 위치,입력값이름)
			int r = psmt.executeUpdate();
			if(r>0) {
				System.out.println("처리성공");
			}else {
				System.out.println("처리실패");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				conn.close();
				psmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
				System.out.println("end of prog");						

	}

}

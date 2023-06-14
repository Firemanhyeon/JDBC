package com.yedam;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class InsertEx {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("id> ");
		String id = sc.nextLine();
		System.out.print("pw> ");
		String pw = sc.nextLine();
		System.out.print("name> ");
		String name = sc.nextLine();
		// jdbc driver 체크
		String url = "jdbc:oracle:thin:@localhost:1521/xe";
		String user = "proj";
		String pass = "proj";

		Connection conn = null;
		Statement stmt = null;
		String sql = "insert into tbl_users(user_id,user_pw,user_name)" + "values('" + id + "','" + pw + "','" + name + "')";
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			conn = DriverManager.getConnection(url, user, pass);
			stmt = conn.createStatement();
			int r = stmt.executeUpdate(sql);// insert update delete 를통해 처리된 건수만큼 반환해줌
			if (r > 0) {
				System.out.println("처리성공");
			} else {
				System.out.println("처리실패");
			}

		} catch (Exception e) {
			System.out.println("처리중 에러발생");
			e.printStackTrace();//어디서 에러가 났는지 보여줌
			
		} finally {//정상적으로실행되건 , 예외가 발생하건 무조건실행되는 코드가들어감
			try {
				conn.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		System.out.println("end of prog");
	}

}

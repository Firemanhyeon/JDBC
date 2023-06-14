package com.yedam.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yedam.Dao;

//입력,수정,삭제,목록 
public class UserDao {
	Connection conn;
	PreparedStatement psmt;
	ResultSet rs;
	String sql;
	//로그인메소드
	public boolean login(String id , String pw) {
		sql = "select * from tbl_users where user_id = ? and user_pw = ?";
		conn = Dao.getConnect();
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			psmt.setString(2, pw);
			
			rs = psmt.executeQuery();
			if(rs.next()){
				return true;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return false;
	}

	public void close() {// 객체들의 정보를 끊어주는 메소드
		try {
			if (conn != null) {
				conn.close();
			}
			if (psmt != null) {
				psmt.close();
			}
			if (rs != null) {
				rs.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	// 입력메소드
	public boolean add(UserVo user) {
		sql = "insert into tbl_users (user_id,user_pw,user_name) " 
	 + "values(?,?,?) ";
		conn = Dao.getConnect();
		try {
			psmt = conn.prepareStatement(sql);// = new UserDao();
			psmt.setString(1, user.getUserId());
			psmt.setString(2, user.getUserPw());
			psmt.setString(3, user.getUserName());

			int r = psmt.executeUpdate();// 쿼리 실행
			if (r > 0) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return false;
	}

	// 조회메소드
	public UserVo search(String userId) {
		sql = "select * from tbl_users where user_id = ? ";
		conn = Dao.getConnect();
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, userId);
			rs = psmt.executeQuery();//
			if (rs.next()) {// 조회된결과가 있으면
				UserVo user = new UserVo();// 인스턴스생성
				user.setUserId(rs.getString("user_id"));
				user.setUserPw(rs.getString("user_pw"));
				user.setUserName(rs.getString("user_name"));
				user.setUserBirth(rs.getString("user_birth"));
				user.setUserPhone(rs.getString("user_phone"));
				user.setUserAddr(rs.getString("user_addr"));
				return user;

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return null;// 조회된결과없으면 null반환
	}

	// 수정메소드
	public boolean modify(UserVo user) {
		sql = "update tbl_users" + " set user_pw = nvl(?,user_pw),"// 값이 null이면 그값을 대체하기위한 함수
				+ " user_name = nvl(?,user_name)," + " user_birth=nvl(?,user_birth),"
				+ " user_phone =nvl(?,user_phone)," + " user_addr =nvl(?,user_addr)" + " where user_id = ?";
		conn = Dao.getConnect();
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, user.getUserPw());
			psmt.setString(2, user.getUserName());
			psmt.setString(3, user.getUserBirth());
			psmt.setString(4, user.getUserPhone());
			psmt.setString(5, user.getUserAddr());
			psmt.setString(6, user.getUserId());

			int r = psmt.executeUpdate();// 쿼리실행시점

			if (r > 0) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return false;
	}
	
	//삭제메소드
	public boolean delete(String id) {
		sql = "delete from tbl_users" + " where user_id = ?";
		conn = Dao.getConnect();
		try {
//			conn.setAutoCommit(false);//예금같은경우 A 에서 B로 송금을 한다 A계좌에서 금액을 빼내고 (update) B에게 입금되었다는정보를 꺼내야한다
//			//A는 빠졌는데 B는 안들어왔으면 안되니까 업데이트 된걸 확인하고 B한테 들어오게한다
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			int r = psmt.executeUpdate();
			if (r > 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return false;
	}

	// 목록메소드
	public List<UserVo> list() {
		List<UserVo> list = new ArrayList<>();

		sql = "select * from tbl_users";
		conn = Dao.getConnect();
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();

			while (rs.next()) {
				UserVo user = new UserVo();
				user.setUserId(rs.getString("user_id"));
				user.setUserPw(rs.getString("user_pw"));
				user.setUserName(rs.getString("user_name"));
				user.setUserBirth(rs.getString("user_birth"));
				user.setUserPhone(rs.getString("user_phone"));
				user.setUserAddr(rs.getString("user_addr"));
				list.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}
}

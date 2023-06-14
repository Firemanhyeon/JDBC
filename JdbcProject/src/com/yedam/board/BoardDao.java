package com.yedam.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yedam.Dao;

public class BoardDao {
	Connection conn;
	PreparedStatement psmt;
	PreparedStatement psmt2;
	ResultSet rs;
	String sql;

	// 클로즈 메소드
	public void close() {
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 글등록메소드
	public boolean add(BoardVo user) {
		sql = "insert into tbl_board (brd_no,brd_title,brd_writer,brd_content)" + " values(board_seq.nextval,?,?,?) ";
		conn = Dao.getConnect();
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, user.getTitle());
			psmt.setString(2, user.getWriter());
			psmt.setString(3, user.getContent());

			int r = psmt.executeUpdate();
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

	// 삭제메소드
	public boolean delete(int number) {
		sql = "delete from tbl_Board"+" where brd_no = ?";
		conn=Dao.getConnect();
		try {
			psmt =conn.prepareStatement(sql);
			psmt.setInt(1, number);
			int r = psmt.executeUpdate();
			if(r>0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return false;
	}

	// 글내용수정메소드
	public boolean modify(BoardVo number) {
		sql = "update tbl_Board set brd_content = nvl(?,brd_content)"
				+" where brd_no = ?";
		conn = Dao.getConnect();
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, number.getContent());
			psmt.setInt(2, number.getNumber());
			
			int r = psmt.executeUpdate();
			if(r>0) {
				return true;
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return false;
	}

	// 목록메소드
	public List<BoardVo> list() {
		List<BoardVo>list = new ArrayList<>();
		sql = "select * from tbl_board order by brd_no";
		conn = Dao.getConnect();
		try {
			psmt = conn.prepareStatement(sql);
			rs=psmt.executeQuery();
			
			while(rs.next()) {
				BoardVo user = new BoardVo();
				user.setNumber(rs.getInt("brd_no"));
				user.setTitle(rs.getString("brd_title"));
				user.setWriter(rs.getString("brd_writer"));
				user.setContent(rs.getString("brd_content"));
				user.setDate(rs.getDate("create_date"));
				user.setCount(rs.getInt("click_cnt"));
				list.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return list;
	}

//	 상세보기 글번호입력시 내용 뜨게하고 조회수 +1
	public BoardVo search(int num) {
		sql = "select * from tbl_board "+" where brd_no = ?";
		String sql2 = "update tbl_board set click_cnt = click_cnt+1 where brd_no = ?";
		conn = Dao.getConnect();
		try {
			psmt = conn.prepareStatement(sql);
			psmt2 = conn.prepareStatement(sql2);
			
			psmt.setInt(1, num);
			psmt2.setInt(1, num);
			int rs2 = psmt2.executeUpdate();
			
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				BoardVo user = new BoardVo();
				user.setNumber(rs.getInt("brd_no"));
				user.setTitle(rs.getString("brd_title"));
				user.setWriter(rs.getString("brd_writer"));
				user.setContent(rs.getString("brd_content"));
				user.setDate(rs.getDate("create_date"));
				user.setCount(rs.getInt("click_cnt"));
				return user;
				
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return null;
	}
}
package model.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import model.board.DataSourceManager;

public class MemberDAO {
	private static MemberDAO dao = new MemberDAO();	
	private DataSource dataSource;
	private MemberDAO() {
		dataSource = DataSourceManager.getInstance().getDataSource();
	}
	public static MemberDAO getInstance(){
		return dao;
	}
	public Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}
	public void closeAll(PreparedStatement pstmt, Connection con) throws SQLException {
		if (pstmt != null)
			pstmt.close();
		if (con != null)
			con.close(); 
	}
	public void closeAll(ResultSet rs, PreparedStatement pstmt, Connection con) throws SQLException {
		if (rs != null)
			rs.close();
		closeAll(pstmt, con);
	}	
//	public MemberVO loginCheck(String id, String password) throws SQLException {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		MemberVO vo = null;
//		try {
//			con = getConnection();
//			String sql = "select name from board_member where id=? and password=?";
//			pstmt = con.prepareStatement(sql);
//			pstmt.setString(1, id);
//			pstmt.setString(2, password);
//			rs = pstmt.executeQuery();
//			if (rs.next()) {
//				vo = new MemberVO(id, password, rs.getString(1));
//			}
//		} finally {
//			closeAll(rs, pstmt, con);
//		}
//		return vo;
//	}
	public MemberVO login(String id, String password) throws SQLException{
		MemberVO vo=null;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;    
		try{
			con=getConnection();
			String sql= "select id, mem_name, password, birth_date, gender, mem_type from member where id=? and password=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1,id);
			pstmt.setString(2, password);
			rs=pstmt.executeQuery();
			if (rs.next()) {
				vo = new MemberVO(id, password, rs.getString("mem_name"), rs.getString("birth_date"),
						rs.getString("gender"), rs.getString("mem_type"));
			}
		}finally{
			closeAll(rs, pstmt, con);
		}
		return vo;
	}
}

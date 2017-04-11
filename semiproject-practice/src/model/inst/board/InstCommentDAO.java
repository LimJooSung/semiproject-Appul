package model.inst.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import model.board.DataSourceManager;

public class InstCommentDAO {
	private static InstCommentDAO dao = new InstCommentDAO();
	private DataSource dataSource;

	private InstCommentDAO() {
		dataSource = DataSourceManager.getInstance().getDataSource();
	}

	public static InstCommentDAO getInstance() {
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

	public ArrayList<InstCommentVO> getInstPostingCommentList(int boardNo) throws SQLException {
		ArrayList<InstCommentVO> cvo = new ArrayList<InstCommentVO>();
		int no = boardNo;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append(
					"select inst_comment_no,inst_board_no,id,name,time_posted,content,parent from inst_comment where inst_board_no = ?");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				InstCommentVO vo = new InstCommentVO();
				vo.setCommentNo(rs.getInt("inst_comment_no"));
				vo.setBoardNo(rs.getInt("inst_board_no"));
				vo.getMember().setId(rs.getString("id"));
				vo.getMember().setName(rs.getString("name"));
				vo.setTimePosted(rs.getString("time_posted"));
				vo.setContent(rs.getString("content"));
				vo.setParent(rs.getInt("parent"));
				cvo.add(vo);
			}
		} finally {
			closeAll(rs, pstmt, con);
		}

		return cvo;
	}

	public void postingInstComment(InstCommentVO vo) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append(
					"insert into inst_comment(inst_comment_no, inst_board_no, id, name, time_posted, content, parent) ");
			sql.append("values(inst_comment_seq.nextval, ?, ?, ?, sysdate, ?, ?)");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, vo.getBoardNo());
			pstmt.setString(2, vo.getMember().getId());
			pstmt.setString(3, vo.getMember().getName());
			pstmt.setString(4, vo.getContent());
			pstmt.setInt(5, vo.getParent());
			pstmt.executeUpdate();
		} finally {
			closeAll(rs, pstmt, con);
		}
	}

	public void deletingInstComment(int commentNo) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = getConnection();
			String sql = "delete from inst_comment where inst_comment_no=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, commentNo);
			pstmt.executeUpdate();
		} finally {
			closeAll(pstmt, con);
		}
	}

	public InstCommentVO getInstPostingCommentByNo(int commentNo) throws SQLException {
		InstCommentVO cvo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select inst_comment_no, inst_board_no, id, name, time_posted, content, parent from ");
			sql.append("inst_comment where inst_comment_no = ?");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, commentNo);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				cvo = new InstCommentVO();
				cvo.setCommentNo(rs.getInt("inst_comment_no"));
				cvo.setBoardNo(rs.getInt("inst_board_no"));
				cvo.getMember().setId(rs.getString("id"));
				cvo.getMember().setName(rs.getString("name"));
				cvo.setTimePosted(rs.getString("time_posted"));
				cvo.setContent(rs.getString("content"));
				cvo.setParent(rs.getInt("parent"));
			}
		} finally {
			closeAll(rs, pstmt, con);
		}
		return cvo;
	}

	public void updatePostingComment(InstCommentVO cvo) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = getConnection();
			String sql = "update inst_comment set content=?, time_posted=sysdate where inst_comment_no=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, cvo.getContent());
			pstmt.setInt(2, cvo.getCommentNo());
			pstmt.executeUpdate();
		} finally {
			closeAll(pstmt, con);
		}
	}

}

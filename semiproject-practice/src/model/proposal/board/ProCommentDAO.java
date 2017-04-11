package model.proposal.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import model.board.DataSourceManager;

public class ProCommentDAO {
	private static ProCommentDAO dao = new ProCommentDAO();
	private DataSource dataSource;

	private ProCommentDAO() {
		dataSource = DataSourceManager.getInstance().getDataSource();
	}

	public static ProCommentDAO getInstance() {
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

	public ArrayList<ProCommentVO> getProPostingCommentList(int boardNo) throws SQLException {
		ArrayList<ProCommentVO> cvo = new ArrayList<ProCommentVO>();
		int no = boardNo;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append(
					"select proposal_comment_no, proposal_board_no,id,name,time_posted,content,parent from proposal_comment where proposal_board_no = ?");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ProCommentVO vo = new ProCommentVO();
				vo.setCommentNo(rs.getInt("proposal_comment_no"));
				vo.setBoardNo(rs.getInt("proposal_board_no"));
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

	public void postingProComment(ProCommentVO vo) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append(
					"insert into proposal_comment(proposal_comment_no, proposal_board_no, id, name, time_posted, content, parent) ");
			sql.append("values(proposal_comment_seq.nextval, ?, ?, ?, sysdate, ?, ?)");
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

	public void deletingProComment(int commentNo) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = getConnection();
			String sql = "delete from proposal_comment where proposal_comment_no=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, commentNo);
			pstmt.executeUpdate();
		} finally {
			closeAll(pstmt, con);
		}
	}

	public ProCommentVO getProPostingCommentByNo(int commentNo) throws SQLException {
		ProCommentVO cvo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select proposal_comment_no, proposal_board_no, id, name, time_posted, content, parent from ");
			sql.append("proposal_comment where proposal_comment_no = ?");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, commentNo);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				cvo = new ProCommentVO();
				cvo.setCommentNo(rs.getInt("proposal_comment_no"));
				cvo.setBoardNo(rs.getInt("proposal_board_no"));
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

	public void updatePostingComment(ProCommentVO cvo) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = getConnection();
			String sql = "update proposal_comment set content=?, time_posted=sysdate where proposal_comment_no=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, cvo.getContent());
			pstmt.setInt(2, cvo.getCommentNo());
			pstmt.executeUpdate();
		} finally {
			closeAll(pstmt, con);
		}
	}

}

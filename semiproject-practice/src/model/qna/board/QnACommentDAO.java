package model.qna.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import model.board.DataSourceManager;

public class QnACommentDAO {
	private static QnACommentDAO dao = new QnACommentDAO();
	private DataSource dataSource;

	private QnACommentDAO() {
		dataSource = DataSourceManager.getInstance().getDataSource();
	}

	public static QnACommentDAO getInstance() {
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

	public ArrayList<QnACommentVO> getQnAPostingCommentList(int boardNo) throws SQLException {
		ArrayList<QnACommentVO> cvo = new ArrayList<QnACommentVO>();
		int no = boardNo;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append(
					"select qna_comment_no, qna_board_no, id, name, time_posted, content, parent from qna_comment where qna_board_no = ?");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				QnACommentVO vo = new QnACommentVO();
				vo.setCommentNo(rs.getInt("qna_comment_no"));
				vo.setBoardNo(rs.getInt("qna_board_no"));
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

	public void postingQnAComment(QnACommentVO vo) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append(
					"insert into qna_comment(qna_comment_no, qna_board_no, id, name, time_posted, content, parent) ");
			sql.append("values(qna_comment_seq.nextval, ?, ?, ?, sysdate, ?, ?)");
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

	public void deletingQnAComment(int commentNo) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = getConnection();
			String sql = "delete from qna_comment where qna_comment_no=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, commentNo);
			pstmt.executeUpdate();
		} finally {
			closeAll(pstmt, con);
		}
	}

	public QnACommentVO getQnAPostingCommentByNo(int commentNo) throws SQLException {
		QnACommentVO cvo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select qna_comment_no, qna_board_no, id, name, time_posted, content, parent from ");
			sql.append("qna_comment where qna_comment_no = ?");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, commentNo);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				cvo = new QnACommentVO();
				cvo.setCommentNo(rs.getInt("qna_comment_no"));
				cvo.setBoardNo(rs.getInt("qna_board_no"));
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

	public void updatePostingComment(QnACommentVO cvo) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = getConnection();
			String sql = "update qna_comment set content=?, time_posted=sysdate where qna_comment_no=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, cvo.getContent());
			pstmt.setInt(2, cvo.getCommentNo());
			pstmt.executeUpdate();
		} finally {
			closeAll(pstmt, con);
		}
	}

	public int getTotalCommentCount(int boardNo) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int totalCommentCount = 0;
		try {
			con = getConnection();
			String sql = "select count(*) from qna_comment where qna_board_no=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				totalCommentCount = rs.getInt(1);
			}
		} finally {
			closeAll(rs, pstmt, con);
		}
		return totalCommentCount;
	}
}

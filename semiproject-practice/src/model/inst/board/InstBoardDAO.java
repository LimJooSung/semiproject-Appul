package model.inst.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import model.board.BoardVO;
import model.board.DataSourceManager;
import model.board.PagingBean;

public class InstBoardDAO {
	private static InstBoardDAO dao = new InstBoardDAO();	
	private DataSource dataSource;
	private InstBoardDAO() {
		dataSource = DataSourceManager.getInstance().getDataSource();
	}
	public static InstBoardDAO getInstance() {
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
		closeAll(pstmt,con);
	}	
	
	/**
	 * 리스트에 게시물을 출력하는 함수
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<BoardVO> getInstPostingList(PagingBean pagingBean) throws SQLException {	// 매개변수 PagingBean
		Connection con = null;																	// 컨트롤러에서! 총게시물수, 현재페이지번호
		PreparedStatement pstmt = null;													// getTotalCount
		ResultSet rs = null;
		ArrayList<BoardVO> list = new ArrayList<BoardVO>();
		try {
			con = getConnection();
	        StringBuilder sql = new StringBuilder();
            sql.append("select ib.inst_board_no, ib.title, ib.id, ib.hit, ib.time_posted, m.mem_name from(");
            sql.append("select row_number() over(order by inst_board_no desc) rnum, inst_board_no, title, id, ");
            sql.append("hit, to_char(time_posted, 'YYYY.MM.DD') as time_posted ");
            sql.append("from inst_board");
            sql.append(") ib, member m where ib.id = m.id and rnum between ? and ? order by rnum asc");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, pagingBean.getStartRowNumber());
			pstmt.setInt(2, pagingBean.getEndRowNumber());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				BoardVO vo = new InstBoardVO();
				int totalCommentCount = InstCommentDAO.getInstance().getTotalCommentCount(rs.getInt("inst_board_no"));
				vo.setBoardNo(rs.getInt("inst_board_no"));
				vo.setTitle(rs.getString("title"));
				vo.getMember().setId(rs.getString("id"));
				vo.getMember().setName(rs.getString("mem_name"));
				vo.setHits(rs.getInt("hit"));
				vo.setTimePosted(rs.getString("time_posted"));
				vo.setTotalCommentCount(totalCommentCount);
				list.add(vo);
			}
		} finally {
			closeAll(rs, pstmt, con);
		}
		return list;
	}
	/**
	 * 제목으로 게시물을 검색하여 출력하는 함수
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<BoardVO> getSearchedInstPostingListByTitle(PagingBean pagingBean, String searchTxt) throws SQLException{	// 매개변수 PagingBean
		Connection con = null;																	// 컨트롤러에서! 총게시물수, 현재페이지번호
		PreparedStatement pstmt = null;													// getTotalCount
		ResultSet rs = null;
		ArrayList<BoardVO> list = new ArrayList<BoardVO>();
		try {
			con = getConnection();
	        StringBuilder sql = new StringBuilder();
	        sql.append("select ib.rnum,ib.inst_board_no, ib.title, ib.id, ib.hit, ib.time_posted, m.mem_name from(");
            sql.append("select row_number() over(order by inst_board_no desc) rnum, inst_board_no, title, id, ");
            sql.append("hit, to_char(time_posted, 'YYYY.MM.DD') as time_posted ");
            sql.append("from inst_board where title like ?");
            sql.append(") ib, member m where ib.id = m.id and rnum between ? and ? order by rnum asc");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, "%" + searchTxt + "%");
			pstmt.setInt(2, pagingBean.getStartRowNumber());
			pstmt.setInt(3, pagingBean.getEndRowNumber());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				BoardVO vo = new InstBoardVO();
				int totalCommentCount = InstCommentDAO.getInstance().getTotalCommentCount(rs.getInt("inst_board_no"));
				vo.setBoardNo(rs.getInt("inst_board_no"));
				vo.setTitle(rs.getString("title"));
				vo.getMember().setId(rs.getString("id"));
				vo.getMember().setName(rs.getString("mem_name"));
				vo.setHits(rs.getInt("hit"));
				vo.setTimePosted(rs.getString("time_posted"));
				vo.setTotalCommentCount(totalCommentCount);
				list.add(vo);
			}
		} finally {
			closeAll(rs, pstmt, con);
		}
		return list;
	}
	/**
	 * 제목&내용으로 게시물을 검색하여 출력하는 함수
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<BoardVO> getSearchedInstPostingListByTitleAndContent(PagingBean pagingBean, String searchTxt) throws SQLException{	// 매개변수 PagingBean
		Connection con = null;																	// 컨트롤러에서! 총게시물수, 현재페이지번호
		PreparedStatement pstmt = null;													// getTotalCount
		ResultSet rs = null;
		ArrayList<BoardVO> list = new ArrayList<BoardVO>();
		try {
			con = getConnection();
	        StringBuilder sql = new StringBuilder();
	        sql.append("select ib.rnum,ib.inst_board_no, ib.title, ib.id, ib.hit, ib.time_posted, m.mem_name from(");
            sql.append("select row_number() over(order by inst_board_no desc) rnum, inst_board_no, title, id, ");
            sql.append("hit, to_char(time_posted, 'YYYY.MM.DD') as time_posted ");
            sql.append("from inst_board where title like ? or content like ?");
            sql.append(") ib, member m where ib.id = m.id and rnum between ? and ? order by rnum asc");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, "%" + searchTxt + "%");
			pstmt.setString(2, "%" + searchTxt + "%");
			pstmt.setInt(3, pagingBean.getStartRowNumber());
			pstmt.setInt(4, pagingBean.getEndRowNumber());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				BoardVO vo = new InstBoardVO();
				int totalCommentCount = InstCommentDAO.getInstance().getTotalCommentCount(rs.getInt("inst_board_no"));
				vo.setBoardNo(rs.getInt("inst_board_no"));
				vo.setTitle(rs.getString("title"));
				vo.getMember().setId(rs.getString("id"));
				vo.getMember().setName(rs.getString("mem_name"));
				vo.setHits(rs.getInt("hit"));
				vo.setTimePosted(rs.getString("time_posted"));
				vo.setTotalCommentCount(totalCommentCount);
				list.add(vo);
			}
		} finally {
			closeAll(rs, pstmt, con);
		}
		return list;
	}
	/**
	 * 작성자로 게시물을 검색하여 출력하는 함수
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<BoardVO> getSearchedInstPostingListByWriter(PagingBean pagingBean, String searchTxt) throws SQLException{	// 매개변수 PagingBean
		Connection con = null;																	// 컨트롤러에서! 총게시물수, 현재페이지번호
		PreparedStatement pstmt = null;													// getTotalCount
		ResultSet rs = null;
		ArrayList<BoardVO> list = new ArrayList<BoardVO>();
		try {
			con = getConnection();
	        StringBuilder sql = new StringBuilder();
	        sql.append("select tb.rnum, tb.inst_board_no, tb.title, tb.content, tb.id, tb.hit, tb.time_posted, tb.mem_name from(");
            sql.append("select row_number() over(order by inst_board_no desc) rnum, ib.inst_board_no, ib.title, ib.content, ib.id, ");
            sql.append("ib.hit, to_char(time_posted, 'YYYY.MM.DD') as time_posted, m.mem_name ");
            sql.append("from inst_board ib, member m where ib.id = m.id and m.mem_name like ? order by rnum asc");
            sql.append(") tb where rnum between ? and ?");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, "%" + searchTxt + "%");
			pstmt.setInt(2, pagingBean.getStartRowNumber());
			pstmt.setInt(3, pagingBean.getEndRowNumber());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				BoardVO vo = new InstBoardVO();
				int totalCommentCount = InstCommentDAO.getInstance().getTotalCommentCount(rs.getInt("inst_board_no"));
				vo.setBoardNo(rs.getInt("inst_board_no"));
				vo.setTitle(rs.getString("title"));
				vo.getMember().setId(rs.getString("id"));
				vo.getMember().setName(rs.getString("mem_name"));
				vo.setHits(rs.getInt("hit"));
				vo.setTimePosted(rs.getString("time_posted"));
				vo.setTotalCommentCount(totalCommentCount);
				list.add(vo);
			}
		} finally {
			closeAll(rs, pstmt, con);
		}
		return list;
	}
	/**
	 * 게시글 쓰는 함수
	 * @param vo
	 * @throws SQLException
	 */
	public void postingInstBoard(BoardVO vo) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement("select inst_board_seq.nextval from dual");
			rs = pstmt.executeQuery();
			if (rs.next()) {
				vo.setBoardNo(rs.getInt(1));
			}
			pstmt.close();
			StringBuilder sql = new StringBuilder();
			sql.append("insert into inst_board(inst_board_no, id, title, content, time_posted, file_name) ");
			sql.append("values(?, ?, ?, ?, sysdate, ?)");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, vo.getBoardNo());
			pstmt.setString(2, vo.getMember().getId());
			pstmt.setString(3, vo.getTitle());
			pstmt.setString(4, vo.getContent());
			pstmt.setString(5, vo.getAttachedFile());
			pstmt.executeUpdate();
		} finally {
			closeAll(rs, pstmt, con);
		}
	}	
    /**
     * Sequence 글번호로 게시물을 검색하는 메서드 
     * @param no
     * @return
     * @throws SQLException
     */
	public BoardVO getInstPostingByNo(int no) throws SQLException{
		InstBoardVO vo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con=getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select ib.inst_board_no, ib.title, ib.id, ib.content, ib.file_name, ");
			sql.append("to_char(time_posted,'YYYY.MM.DD HH24:MI:SS') as time_posted, ib.hit, ib.avg_rating, m.mem_name ");
			sql.append("from inst_board ib, member m where ib.id = m.id and ib.inst_board_no=?");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();		
			if (rs.next()) {
				vo = new InstBoardVO();
				vo.setBoardNo(rs.getInt("inst_board_no"));
				vo.setTitle(rs.getString("title"));
				vo.getMember().setId(rs.getString("id"));
				vo.getMember().setName(rs.getString("mem_name"));
				vo.setContent(rs.getString("content"));
				vo.setHits(rs.getInt("hit"));
				vo.setTimePosted(rs.getString("time_posted"));
				vo.setAttachedFile(rs.getString("file_name"));
				vo.setAvgRating(rs.getDouble("avg_rating"));
			}
		} finally {
			closeAll(rs, pstmt, con);
		}
		return vo;
	}	
	/**
	 * 조회수 증가 
	 * @param no
	 * @throws SQLException
	 */
	public void updateHit(int no) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = getConnection(); 
			pstmt = con.prepareStatement("update inst_board set hit=hit+1 where inst_board_no=?");
			pstmt.setInt(1, no);	
			pstmt.executeUpdate();			
		} finally {
			closeAll(pstmt, con);
		}
	}
	/**
	 * 글번호에 해당하는 게시물을 삭제하는 메서드
	 * @param no
	 * @throws SQLException
	 */
	public void deleteInstPosting(int boardNo) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con=getConnection(); 
			String sql = "delete from inst_board where inst_board_no=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, boardNo);		
			pstmt.executeUpdate();			
		} finally {
			closeAll(pstmt, con);
		}
	}
	/**
	 * 게시물 정보 업데이트하는 메서드 
	 * @param vo
	 * @throws SQLException
	 */
	public void updatePosting(BoardVO vo) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con=getConnection();
			String sql = "update inst_board set title=?, content=?, file_name=? where inst_board_no=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getAttachedFile());
			pstmt.setInt(4, vo.getBoardNo());		
			pstmt.executeUpdate();			
		} finally {
			closeAll(pstmt, con);
		}
	}
	/**
	 * 총 게시물 수를 조회하는 메서드
	 * @return
	 * @throws SQLException 
	 */
	public int getTotalContent() throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int totalContent = 0;
		try {
			con=getConnection(); 
			String sql = "select count(*) from inst_board";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				totalContent = rs.getInt(1);
			}
		} finally {
			closeAll(rs, pstmt, con);
		}
		return totalContent;
	}
	/**
	 * 검색된 게시물 수를 조회하는 메서드
	 * @return
	 * @throws SQLException 
	 */
	public int getTotalSearchedContent(String type, String searchTxt) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int totalContent = 0;
		StringBuilder sql = new StringBuilder();
		try {
			con=getConnection(); // SELECT count(*) FROM inst_board WHERE title LIKE '%연습%'
			if (type.equals("title")) {
				sql.append("select count(*) from(");
	            sql.append("select row_number() over(order by inst_board_no desc) rnum, inst_board_no, title, id, ");
	            sql.append("hit, to_char(time_posted, 'YYYY.MM.DD') as time_posted ");
	            sql.append("from inst_board where title like ?");
	            sql.append(") ib, member m where ib.id = m.id");
				pstmt = con.prepareStatement(sql.toString());
				pstmt.setString(1, "%" + searchTxt + "%");
				rs = pstmt.executeQuery();
				if (rs.next()) {
					totalContent = rs.getInt(1);
				}
			} else if (type.equals("titleAndContent")) {
				sql.append("select count(*) from(");
	            sql.append("select row_number() over(order by inst_board_no desc) rnum, inst_board_no, title, id, ");
	            sql.append("hit, to_char(time_posted, 'YYYY.MM.DD') as time_posted ");
	            sql.append("from inst_board where title like ? or content like ?");
	            sql.append(") ib, member m where ib.id = m.id");
				pstmt = con.prepareStatement(sql.toString());
				pstmt.setString(1, "%" + searchTxt + "%");
				pstmt.setString(2, "%" + searchTxt + "%");
				rs = pstmt.executeQuery();
				if (rs.next()) {
					totalContent = rs.getInt(1);
				}
			} else if (type.equals("writer")) {
				sql.append("select count(*) from(");
				sql.append("select row_number() over(order by inst_board_no desc) rnum, ib.inst_board_no, ib.title, ib.content, ib.id, ");
				sql.append("ib.hit, to_char(time_posted, 'YYYY.MM.DD') as time_posted, m.mem_name ");
				sql.append("from inst_board ib, member m where ib.id = m.id and m.mem_name like ?");
				sql.append(") tb");
				pstmt = con.prepareStatement(sql.toString());
				pstmt.setString(1, "%" + searchTxt + "%");
				rs = pstmt.executeQuery();
				if (rs.next()) {
					totalContent = rs.getInt(1);
				}
			}
		} finally {
			closeAll(rs, pstmt, con);
		}
		return totalContent;
	}
}

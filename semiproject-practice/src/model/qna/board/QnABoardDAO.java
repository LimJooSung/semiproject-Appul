package model.qna.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import model.board.BoardVO;
import model.board.DataSourceManager;
import model.board.PagingBean;
import model.inst.board.InstBoardVO;
import model.member.MemberVO;

public class QnABoardDAO {
	private static QnABoardDAO dao = new QnABoardDAO();
	private DataSource dataSource;

	private QnABoardDAO() {
		dataSource = DataSourceManager.getInstance().getDataSource();
	}

	public static QnABoardDAO getInstance() {
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

	/**
	 * 페이지 번호에 해당하는 게시물 목록 리스트를 반환하는 메서드
	 * 
	 * @param pageNo
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<BoardVO> getPostingList(PagingBean pagingBean) throws SQLException {
		ArrayList<BoardVO> list = new ArrayList<BoardVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT b.qna_board_no, b.title, b.time_posted, b.hit, b.id, m.mem_name FROM(");
			sql.append("SELECT row_number() over(order by to_number(qna_board_no) desc) as rnum, qna_board_no, title, hit,");
			sql.append("to_char(time_posted,'YYYY.MM.DD') as time_posted, id FROM ");
			sql.append("qna_board ");
			sql.append(") b, member m where b.id=m.id and rnum between ? and ? ");
			sql.append(" order by qna_board_no desc");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, pagingBean.getStartRowNumber());
			pstmt.setInt(2, pagingBean.getEndRowNumber());
			rs = pstmt.executeQuery();
			// 목록에서 게시물 content는 필요없으므로 null로 setting
			// select no,title,time_posted,hits,id,name
			while (rs.next()) {
				BoardVO bvo = new QnABoardVO();
				bvo.setBoardNo(rs.getInt(1));
				bvo.setTitle(rs.getString(2));
				bvo.setTimePosted(rs.getString(3));
				bvo.setHits(rs.getInt(4));
				MemberVO mvo = new MemberVO();
				mvo.setId(rs.getString(5));
				mvo.setName(rs.getString(6));
				bvo.setMember(mvo);
				list.add(bvo);
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
	public ArrayList<BoardVO> getSearchedQnAPostingListByTitle(PagingBean pagingBean, String searchTxt) throws SQLException{	// 매개변수 PagingBean
		Connection con = null;																	// 컨트롤러에서! 총게시물수, 현재페이지번호
		PreparedStatement pstmt = null;													// getTotalCount
		ResultSet rs = null;
		ArrayList<BoardVO> list = new ArrayList<BoardVO>();
		try {
			con = getConnection();
	        StringBuilder sql = new StringBuilder();
	        sql.append("select qb.inst_board_no, qb.title, qb.id, qb.hit, qb.time_posted, m.mem_name from(");
            sql.append("select row_number() over(order by to_number(qna_board_no) desc) rnum, qna_board_no, title, id, ");
            sql.append("hit, to_char(time_posted, 'YYYY.MM.DD') as time_posted ");
            sql.append("from qna_board where title like ?");
            sql.append(") qb, member m where qb.id = m.id and rnum between ? and ?");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, "%" + searchTxt + "%");
			pstmt.setInt(2, pagingBean.getStartRowNumber());
			pstmt.setInt(3, pagingBean.getEndRowNumber());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				BoardVO vo = new QnABoardVO();
				vo.setBoardNo(rs.getInt("qna_board_no"));
				vo.setTitle(rs.getString("title"));
				vo.getMember().setId(rs.getString("id"));
				vo.getMember().setName(rs.getString("mem_name"));
				vo.setHits(rs.getInt("hit"));
				vo.setTimePosted(rs.getString("time_posted"));
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
	public ArrayList<BoardVO> getSearchedQnAPostingListByTitleAndContent(PagingBean pagingBean, String searchTxt) throws SQLException{	// 매개변수 PagingBean
		Connection con = null;																	// 컨트롤러에서! 총게시물수, 현재페이지번호
		PreparedStatement pstmt = null;													// getTotalCount
		ResultSet rs = null;
		ArrayList<BoardVO> list = new ArrayList<BoardVO>();
		try {
			con = getConnection();
	        StringBuilder sql = new StringBuilder();
	        sql.append("select qb.inst_board_no, qb.title, qb.id, qb.hit, qb.time_posted, m.mem_name from(");
            sql.append("select row_number() over(order by to_number(qna_board_no) desc) rnum, qna_board_no, title, id, ");
            sql.append("hit, to_char(time_posted, 'YYYY.MM.DD') as time_posted ");
            sql.append("from qna_board where title like ? or content like ?");
            sql.append(") qb, member m where qb.id = m.id and rnum between ? and ?");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, "%" + searchTxt + "%");
			pstmt.setString(2, "%" + searchTxt + "%");
			pstmt.setInt(3, pagingBean.getStartRowNumber());
			pstmt.setInt(4, pagingBean.getEndRowNumber());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				BoardVO vo = new QnABoardVO();
				vo.setBoardNo(rs.getInt("qna_board_no"));
				vo.setTitle(rs.getString("title"));
				vo.getMember().setId(rs.getString("id"));
				vo.getMember().setName(rs.getString("mem_name"));
				vo.setHits(rs.getInt("hit"));
				vo.setTimePosted(rs.getString("time_posted"));
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
	public ArrayList<BoardVO> getSearchedQnAPostingListByWriter(PagingBean pagingBean, String searchTxt) throws SQLException{	// 매개변수 PagingBean
		Connection con = null;																	// 컨트롤러에서! 총게시물수, 현재페이지번호
		PreparedStatement pstmt = null;													// getTotalCount
		ResultSet rs = null;
		ArrayList<BoardVO> list = new ArrayList<BoardVO>();
		try {
			con = getConnection();
	        StringBuilder sql = new StringBuilder();
	        sql.append("select ib.inst_board_no, ib.title, ib.id, ib.hit, ib.time_posted, m.mem_name from(");
            sql.append("select row_number() over(order by to_number(inst_board_no) desc) rnum, inst_board_no, title, id, ");
            sql.append("hit, to_char(time_posted, 'YYYY.MM.DD') as time_posted ");
            sql.append("from inst_board where id like ?");
            sql.append(") ib, member m where ib.id = m.id and rnum between ? and ?");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, "%" + searchTxt + "%");
			pstmt.setInt(2, pagingBean.getStartRowNumber());
			pstmt.setInt(3, pagingBean.getEndRowNumber());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				BoardVO vo = new InstBoardVO();
				vo.setBoardNo(rs.getInt("inst_board_no"));
				vo.setTitle(rs.getString("title"));
				vo.getMember().setId(rs.getString("id"));
				vo.getMember().setName(rs.getString("mem_name"));
				vo.setHits(rs.getInt("hit"));
				vo.setTimePosted(rs.getString("time_posted"));
				list.add(vo);
			}
		} finally {
			closeAll(rs, pstmt, con);
		}
		return list;
	}
	
	/**
	 * 전체 게시물 수를 조회하는 메서드(페이징 처리를 위해 사용)
	 * 
	 * @return
	 * @throws SQLException
	 */
	public int getTotalContentCount() throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = -1;
		try {
			con = getConnection();
			String sql = "select count(*) from qna_board";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} finally {
			closeAll(rs, pstmt, con);
		}
		return count;
	}

	/**
	 * Sequence 글번호로 게시물을 검색하는 메서드
	 * 
	 * @param no
	 * @return
	 * @throws SQLException
	 */
	public BoardVO getPostingByNo(int no) throws SQLException {
		BoardVO bvo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select b.title,to_char(b.time_posted,'YYYY.MM.DD  HH24:MI:SS') as time_posted");
			sql.append(",b.content,b.hit,b.id,m.mem_name");
			sql.append(" from qna_board b,member m");
			sql.append(" where b.id=m.id and b.qna_board_no=?");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				bvo = new QnABoardVO();
				bvo.setBoardNo(no);
				bvo.setTitle(rs.getString("title"));
				bvo.setContent(rs.getString("content"));
				bvo.setHits(rs.getInt("hit"));
				bvo.setTimePosted(rs.getString("time_posted"));
				MemberVO mvo = new MemberVO();
				mvo.setId(rs.getString("id"));
				mvo.setName(rs.getString("mem_name"));
				bvo.setMember(mvo);
			}
		} finally {
			closeAll(rs, pstmt, con);
		}
		return bvo;
	}

	/**
	 * 조회수 증가
	 * 
	 * @param no
	 * @throws SQLException
	 */
	public void updateHit(int no) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = getConnection();
			String sql = "update qna_board set hit=hit+1 where qna_board_no=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, no);
			pstmt.executeUpdate();
		} finally {
			closeAll(pstmt, con);
		}
	}

	/**
	 * 게시물 등록 메서드 게시물 등록 후 생성된 시퀀스를 BoardVO에 setting 한다.
	 * 
	 * @param vo
	 * @throws SQLException
	 */
	public void posting(BoardVO vo) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			// insert into board_login_inst(no,title,content,id,time_posted)
			// values(board_login_inst_seq.nextval,?,?,?,sysdate)
			StringBuilder sql = new StringBuilder();
			sql.append("insert into qna_board(qna_board_no,title,content,id,time_posted)");
			System.out.println(vo);
			System.out.println(vo);
			sql.append("values(qna_board_seq.nextval,?,?,?,sysdate)");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getMember().getId());
			pstmt.executeUpdate();
			pstmt.close();
			pstmt = con.prepareStatement("select qna_board_seq.currval from dual");
			rs = pstmt.executeQuery();
			if (rs.next())
				vo.setBoardNo(rs.getInt(1));
		} finally {
			closeAll(rs, pstmt, con);
		}
	}

	/**
	 * 글번호에 해당하는 게시물을 삭제하는 메서드
	 * 
	 * @param no
	 * @throws SQLException
	 */
	public void deletePosting(int no) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement("delete from qna_board where qna_board_no=?");
			pstmt.setInt(1, no);
			pstmt.executeUpdate();
		} finally {
			closeAll(pstmt, con);
		}
	}

	/**
	 * 게시물 정보 업데이트하는 메서드
	 * 
	 * @param vo
	 * @throws SQLException
	 */
	public void updatePosting(BoardVO vo) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement("update qna_board set title=?,content=? where qna_board_no=?");
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setInt(3, vo.getBoardNo());
			pstmt.executeUpdate();
		} finally {
			closeAll(pstmt, con);
		}
	}
}

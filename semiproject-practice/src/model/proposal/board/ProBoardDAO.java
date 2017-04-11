package model.proposal.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import model.board.BoardVO;
import model.board.DataSourceManager;
import model.board.PagingBean;
import model.member.MemberVO;

public class ProBoardDAO {
	private static ProBoardDAO dao=new ProBoardDAO();	
	private DataSource dataSource;
	private ProBoardDAO(){
		dataSource=DataSourceManager.getInstance().getDataSource();
	}
	public static ProBoardDAO getInstance(){
		return dao;
	}
	public Connection getConnection() throws SQLException{
		return dataSource.getConnection();
	}
	public void closeAll(PreparedStatement pstmt,Connection con) throws SQLException{
		if(pstmt!=null)
			pstmt.close();
		if(con!=null)
			con.close(); 
	}
	public void closeAll(ResultSet rs,PreparedStatement pstmt,Connection con) throws SQLException{
		if(rs!=null)
			rs.close();
		closeAll(pstmt,con);
	}	
	/**
	 * 페이지 번호에 해당하는 게시물 목록 리스트를 반환하는 메서드 
	 * @param pageNo
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<BoardVO> getPostingList(PagingBean pagingBean) throws SQLException{
		ArrayList<BoardVO> list=new ArrayList<BoardVO>();
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			con=getConnection(); 
			StringBuilder sql = new StringBuilder();
	         sql.append("select b.proposal_board_no, b.title, b.id, b.hit, b.time_posted, m.mem_name,b.secret from(");
	         sql.append("select row_number() over(order by to_number(proposal_board_no) desc) rnum, proposal_board_no, title, id,secret, ");
	         sql.append("hit, to_char(time_posted, 'YYYY.MM.DD') as time_posted ");
	         sql.append("from proposal_board");
	         sql.append(") b, member m where b.id = m.id and rnum between ? and ?");
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setInt(1, pagingBean.getStartRowNumber());
			pstmt.setInt(2, pagingBean.getEndRowNumber());
			rs=pstmt.executeQuery();	
			//목록에서 게시물 content는 필요없으므로 null로 setting
			//select no,title,time_posted,hits,id,name
			while(rs.next()){		
				ProBoardVO bvo=new ProBoardVO();
				bvo.setBoardNo(rs.getInt(1));
				bvo.setTitle(rs.getString(2));
				bvo.setTimePosted(rs.getString(5));
				bvo.setHits(rs.getInt(4));
				bvo.setSecret(rs.getString(7));
				MemberVO mvo=new MemberVO();
				mvo.setId(rs.getString(3));
				mvo.setName(rs.getString(6));
				bvo.setMember(mvo);
				list.add(bvo);			
			}		
			//System.out.println(list);
		}finally{
			closeAll(rs,pstmt,con);
		}
		return list;
	}
	/**
	 * 전체 게시물 수를 조회하는 메서드(페이징 처리를 위해 사용)
	 * @return
	 * @throws SQLException
	 */
	public int getTotalContentCount() throws SQLException {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int count=-1;
		try{
			con=getConnection(); 
			String sql="select count(*) from proposal_board";
			pstmt=con.prepareStatement(sql);			
			rs=pstmt.executeQuery();
			if(rs.next()){
				count=rs.getInt(1);
			}
		}finally{
			closeAll(rs,pstmt,con);
		}
		return count;
	}
	
    /**
     * Sequence 글번호로 게시물을 검색하는 메서드 
     * @param no
     * @return
     * @throws SQLException
     */
	public ProBoardVO getProPostingByNo(int no) throws SQLException{
		ProBoardVO bvo=null;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			con=getConnection();
			StringBuilder sql=new StringBuilder();
			sql.append("select b.title,to_char(b.time_posted,'YYYY.MM.DD  HH24:MI:SS') as time_posted");
			sql.append(",b.content,b.hit,b.id,m.MEM_NAME,b.secret");
			sql.append(" from proposal_board b,member m");
			sql.append(" where b.id=m.id and b.PROPOSAL_BOARD_NO=?");		
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setInt(1, no);
			rs=pstmt.executeQuery();
		
			if(rs.next()){
				bvo=new ProBoardVO();
				bvo.setBoardNo(no);
				bvo.setTitle(rs.getString("title"));
				bvo.setContent(rs.getString("content"));				
				bvo.setHits(rs.getInt("hit"));
				bvo.setTimePosted(rs.getString("time_posted"));
				bvo.setSecret(rs.getString("secret"));
				MemberVO mvo=new MemberVO();
				mvo.setId(rs.getString("id"));
				mvo.setName(rs.getString("MEM_NAME"));
				bvo.setMember(mvo);
			}
			//System.out.println("dao getContent:"+bvo);
		}finally{
			closeAll(rs,pstmt,con);
		}
		return bvo;
	}
	
	/**
	 * 조회수 증가 
	 * @param no
	 * @throws SQLException
	 */
	public void updateHit(int no) throws SQLException{
		Connection con=null;
		PreparedStatement pstmt=null;
		try{
			con=getConnection(); 
			String sql="update proposal_board set hit=hit+1 where PROPOSAL_BOARD_NO=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, no);	
			pstmt.executeUpdate();			
		}finally{
			closeAll(pstmt,con);
		}
	}
	/**
	 * 게시물 등록 메서드  
	 * 게시물 등록 후 생성된 시퀀스를 BoardVO에 setting 한다. 
	 * @param vo
	 * @throws SQLException
	 */
	public void posting(ProBoardVO vo) throws SQLException{
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			con=getConnection();
			//insert into board_login_inst(no,title,content,id,time_posted) values(board_login_inst_seq.nextval,?,?,?,sysdate)
			StringBuilder sql=new StringBuilder();
			sql.append("insert into proposal_board(PROPOSAL_BOARD_NO,title,content,id,time_posted,secret)");
			sql.append(" values(proposal_board_seq.nextval,?,?,?,sysdate,?)");			
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getMember().getId());
			pstmt.setString(4, vo.getSecret());
			pstmt.executeUpdate();			
			pstmt.close();
			pstmt=con.prepareStatement("select proposal_board_seq.currval from dual");
			rs=pstmt.executeQuery();
			if(rs.next())
			vo.setBoardNo(rs.getInt(1));			
		}finally{
			closeAll(rs,pstmt,con);
		}
	}	

	/**
	 * 글번호에 해당하는 게시물을 삭제하는 메서드
	 * @param no
	 * @throws SQLException
	 */
	public void deletePosting(int no) throws SQLException{
		Connection con=null;
		PreparedStatement pstmt=null;
		try{
			con=getConnection(); 
			pstmt=con.prepareStatement("delete from proposal_board where PROPOSAL_BOARD_NO=?");
			pstmt.setInt(1, no);		
			pstmt.executeUpdate();			
		}finally{
			closeAll(pstmt,con);
		}
	}
	/**
	 * 게시물 정보 업데이트하는 메서드 
	 * @param vo
	 * @throws SQLException
	 */
	public void updatePosting(ProBoardVO vo) throws SQLException{
		Connection con=null;
		PreparedStatement pstmt=null;
		try{
			con=getConnection();
			pstmt=con.prepareStatement("update proposal_board set title=?,content=?,secret=? where PROPOSAL_BOARD_NO=?");
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getSecret());
			pstmt.setInt(4, vo.getBoardNo());	
			pstmt.executeUpdate();			
		}finally{
			closeAll(pstmt,con);
		}
	}
	/**
	 * 검색된 게시물 수를 조회하는 메서드
	 * @param type
	 * @param searchTxt
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
	               sql.append("select row_number() over(order by to_number(proposal_board_no) desc) rnum, proposal_board_no, title, id, ");
	               sql.append("hit, to_char(time_posted, 'YYYY.MM.DD') as time_posted ");
	               sql.append("from proposal_board where title like ?");
	               sql.append(") ib, member m where ib.id = m.id");
	            pstmt = con.prepareStatement(sql.toString());
	            pstmt.setString(1, "%" + searchTxt + "%");
	            rs = pstmt.executeQuery();
	            if (rs.next()) {
	               totalContent = rs.getInt(1);
	            }
	         } else if (type.equals("titleAndContent")) {
	            sql.append("select count(*) from(");
	               sql.append("select row_number() over(order by to_number(proposal_board_no) desc) rnum, proposal_board_no, title, id, ");
	               sql.append("hit, to_char(time_posted, 'YYYY.MM.DD') as time_posted ");
	               sql.append("from proposal_board where title like ? or content like ?");
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
	            sql.append("select row_number() over(order by to_number(proposal_board_no) desc) rnum, ib.proposal_board_no, ib.title, ib.content, ib.id, ");
	            sql.append("ib.hit, to_char(time_posted, 'YYYY.MM.DD') as time_posted, m.mem_name ");
	            sql.append("from proposal_board ib, member m where ib.id = m.id and m.mem_name like ?");
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
	/**
	 * 제목으로 검색
	 * @param pagingBean
	 * @param searchTxt
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<BoardVO> getSearchedProPostingListByTitle(PagingBean pagingBean, String searchTxt) throws SQLException {
		 Connection con = null;                                                   // 컨트롤러에서! 총게시물수, 현재페이지번호
	      PreparedStatement pstmt = null;                                       // getTotalCount
	      ResultSet rs = null;
	      ArrayList<BoardVO> list = new ArrayList<BoardVO>();
	      try {
	         con = getConnection();  
	         String sql="select ib.proposal_board_no, ib.title, ib.id, ib.hit, ib.time_posted, m.mem_name,ib.secret from("
	         		+ "select row_number() over(order by to_number(proposal_board_no) desc) rnum, proposal_board_no, title, id,hit,secret, to_char("
	         		+ "time_posted, 'YYYY.MM.DD') as time_posted from proposal_board where title like ?) ib, member m "
	         		+ "where ib.id = m.id and rnum between ? and ?";
            pstmt = con.prepareStatement(sql);
	         pstmt.setString(1, "%" + searchTxt + "%");
	         pstmt.setInt(2, pagingBean.getStartRowNumber());
	         pstmt.setInt(3, pagingBean.getEndRowNumber());
	         rs = pstmt.executeQuery();
	         while (rs.next()) {
	            ProBoardVO vo = new ProBoardVO();
	            vo.setBoardNo(rs.getInt("proposal_board_no"));
	            vo.setTitle(rs.getString("title"));
	            vo.getMember().setId(rs.getString("id"));
	            vo.getMember().setName(rs.getString("mem_name"));
	            vo.setHits(rs.getInt("hit"));
	            vo.setTimePosted(rs.getString("time_posted"));
	            vo.setSecret(rs.getString("secret"));
	            list.add(vo);
	         }
	      } finally {
	         closeAll(rs, pstmt, con);
	      }
	      return list;
	}
	/**
	 * 제목과내용으로 검색
	 * @param pagingBean
	 * @param searchTxt
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<BoardVO> getSearchedProPostingListByTitleAndContent(PagingBean pagingBean, String searchTxt) throws SQLException {
		Connection con = null;                                                   // 컨트롤러에서! 총게시물수, 현재페이지번호
	      PreparedStatement pstmt = null;                                       // getTotalCount
	      ResultSet rs = null;
	      ArrayList<BoardVO> list = new ArrayList<BoardVO>();
	      try {
	         con = getConnection();
	           StringBuilder sql = new StringBuilder();
	           sql.append("select ib.proposal_board_no, ib.title, ib.id, ib.hit, ib.time_posted, m.mem_name,ib.secret from(");
	            sql.append("select row_number() over(order by to_number(proposal_board_no) desc) rnum, proposal_board_no, title, id,secret, ");
	            sql.append("hit, to_char(time_posted, 'YYYY.MM.DD') as time_posted ");
	            sql.append("from proposal_board where title like ? or content like ?");
	            sql.append(") ib, member m where ib.id = m.id and rnum between ? and ?");
	         pstmt = con.prepareStatement(sql.toString());
	         pstmt.setString(1, "%" + searchTxt + "%");
	         pstmt.setString(2, "%" + searchTxt + "%");
	         pstmt.setInt(3, pagingBean.getStartRowNumber());
	         pstmt.setInt(4, pagingBean.getEndRowNumber());
	         rs = pstmt.executeQuery();
	         while (rs.next()) {
	        	 ProBoardVO vo = new ProBoardVO();
	            vo.setBoardNo(rs.getInt("proposal_board_no"));
	            vo.setTitle(rs.getString("title"));
	            vo.getMember().setId(rs.getString("id"));
	            vo.getMember().setName(rs.getString("mem_name"));
	            vo.setHits(rs.getInt("hit"));
	            vo.setTimePosted(rs.getString("time_posted"));
	            vo.setSecret(rs.getString("secret"));
	            list.add(vo);
	         }
	      } finally {
	         closeAll(rs, pstmt, con);
	      }
	      return list;
	}
	/**
	 * 게시자 검색을통한 게시글 수
	 * @param pagingBean
	 * @param searchTxt
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<BoardVO> getSearchedProPostingListByWriter(PagingBean pagingBean, String searchTxt) throws SQLException {
		  Connection con = null;                                                   // 컨트롤러에서! 총게시물수, 현재페이지번호
	      PreparedStatement pstmt = null;                                       // getTotalCount
	      ResultSet rs = null;
	      ArrayList<BoardVO> list = new ArrayList<BoardVO>();
	      try {
	         con = getConnection();
	           StringBuilder sql = new StringBuilder();
	           sql.append("select tb.rnum, tb.proposal_board_no, tb.title, tb.content, tb.id, tb.hit, tb.time_posted, tb.mem_name,tb.secret from(");
	            sql.append("select row_number() over(order by to_number(proposal_board_no) desc) rnum, ib.proposal_board_no, ib.title, ib.content, ib.id,ib.secret, ");
	            sql.append("ib.hit, to_char(time_posted, 'YYYY.MM.DD') as time_posted, m.mem_name ");
	            sql.append("from proposal_board ib, member m where ib.id = m.id and m.mem_name like ?");
	            sql.append(") tb where rnum between ? and ?");
	         pstmt = con.prepareStatement(sql.toString());
	         pstmt.setString(1, "%" + searchTxt + "%");
	         pstmt.setInt(2, pagingBean.getStartRowNumber());
	         pstmt.setInt(3, pagingBean.getEndRowNumber());
	         rs = pstmt.executeQuery();
	         while (rs.next()) {
	        	ProBoardVO vo = new ProBoardVO();
	            vo.setBoardNo(rs.getInt("proposal_board_no"));
	            vo.setTitle(rs.getString("title"));
	            vo.getMember().setId(rs.getString("id"));
	            vo.getMember().setName(rs.getString("mem_name"));
	            vo.setHits(rs.getInt("hit"));
	            vo.setTimePosted(rs.getString("time_posted"));
	            vo.setSecret(rs.getString("secret"));
	            list.add(vo);
	         }
	      } finally {
	         closeAll(rs, pstmt, con);
	      }
	      return list;
	}

}




















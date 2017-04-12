package model.selectingpresenter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import model.board.DataSourceManager;
import model.member.MemberVO;

public class SelectingPresenterDAO {
	private static SelectingPresenterDAO dao=new SelectingPresenterDAO();
	private DataSource dataSource;
	
	private SelectingPresenterDAO() {
		dataSource=DataSourceManager.getInstance().getDataSource();
	}

	public static SelectingPresenterDAO getInstance(){
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
	public ArrayList<SelectingPresenterVO> getPresenterList() throws SQLException{
		ArrayList<SelectingPresenterVO> list=new ArrayList<SelectingPresenterVO>();
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		MemberVO vo = null;
		
		try{
			con=getConnection(); 
			StringBuilder sql=new StringBuilder();
			sql.append("select m.id, m.mem_name, s.cnt_presentation, m.mem_number from member m, selecting_presenter s ");
			sql.append("where m.id = s.id  and s.cnt_presentation=(select min(cnt_presentation) from selecting_presenter)");
			
			pstmt=con.prepareStatement(sql.toString());			
			rs=pstmt.executeQuery();	
			while(rs.next()){					
				String id = rs.getString("id");
				String memberName = rs.getString("mem_name");
				int cntPresentation = rs.getInt("cnt_presentation");
				int memNumber = rs.getInt("mem_number");
				vo = new MemberVO();
				vo.setId(id);
				vo.setName(memberName);
				list.add(new SelectingPresenterVO(vo, cntPresentation, memNumber));
			}		
		}finally{
			closeAll(rs,pstmt,con);
		}
		return list;
	}
	
	public void updateCntPresentation (String id, int cntPresentation) throws SQLException {
		PreparedStatement pstmt = null;
		Connection con = null;
		
		try {
			con = getConnection();
			
			String sql = "UPDATE selecting_presenter SET cnt_presentation = ? WHERE id = ?";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, ++cntPresentation);
			pstmt.setString(2, id);
			
			pstmt.executeUpdate();
			
		} finally {
			closeAll(pstmt, con);
		}
	}
	
	public ArrayList<SelectingPresenterVO> getAllPresenterList() throws SQLException{
		ArrayList<SelectingPresenterVO> list=new ArrayList<SelectingPresenterVO>();
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			con=getConnection(); 
			StringBuilder sql=new StringBuilder();
			sql.append("select mem_name, cnt_presentation, mem_number  from member m, selecting_presenter s where m.id=s.id");
			pstmt=con.prepareStatement(sql.toString());			
			rs=pstmt.executeQuery();	
			while(rs.next()){					
				String memberName = rs.getString("mem_name");
				int cntPresentation = rs.getInt("cnt_presentation");
				int memNumber = rs.getInt("mem_number");
				MemberVO vo = new MemberVO();
				vo.setName(memberName);
				list.add(new SelectingPresenterVO(vo, cntPresentation, memNumber));
			}		
		}finally{
			closeAll(rs,pstmt,con);
		}
		return list;
	}	
}






















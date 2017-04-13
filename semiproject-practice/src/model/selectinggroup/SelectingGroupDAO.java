package model.selectinggroup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import model.board.DataSourceManager;
import model.member.MemberVO;

public class SelectingGroupDAO {
	private static SelectingGroupDAO dao=new SelectingGroupDAO();	
	private DataSource dataSource;
	
	private SelectingGroupDAO(){
		dataSource = DataSourceManager.getInstance().getDataSource();
	}
	public static SelectingGroupDAO getInstance(){
		return dao;
	}
	public Connection getConnection() throws SQLException{
		return dataSource.getConnection();
		//return DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","scott","tiger");
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
	
	public int getSelectingGroupCount() throws SQLException{
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int count = 0;
		
		try
		{
			con=getConnection(); 
			StringBuilder sql=new StringBuilder();
			//sql.append("select DISTINCT to_char(selecting_date, 'YYYY-MM-DD') as selecting_date from selecting_group order by selecting_date");
			sql.append("select distinct max(selecting_group_count) from selecting_group");
			
			pstmt=con.prepareStatement(sql.toString());
			rs=pstmt.executeQuery();
			
			if (rs.next()){
				count= rs.getInt(1);
			}
		}
		finally
		{
			closeAll(rs,pstmt,con);
		}
		return count;
	}
	
	public int getStudentNumber() throws SQLException{
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int number = 0;
		
		try
		{
			con=getConnection(); 
			StringBuilder sql=new StringBuilder();
			sql.append("select count(*) from member where mem_type like '%학생'");
			
			pstmt=con.prepareStatement(sql.toString());
			rs=pstmt.executeQuery();
			
			if (rs.next()){
				number = rs.getInt(1);
			}
		}
		finally
		{
			closeAll(rs,pstmt,con);
		}
		return number;
	}
	
	public int[] getSelectingGroupRecordList(int selectingGroupCount, int groupNo) throws SQLException{
		int[] previousMemberNumberArr = new int[6];
		int previousMemberNumberIndex = 0;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
			
		try
		{
			con=getConnection(); 
			StringBuilder sql=new StringBuilder();
			
			sql.append("select m.mem_number ");
			sql.append("from selecting_group s, member m ");
			sql.append("where s.id = m.id and m.mem_type like '%학생' and selecting_group_count = ? and s.group_no = ?");	
		
		
			
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setInt(1, selectingGroupCount);
			pstmt.setInt(2, groupNo);
			rs=pstmt.executeQuery();
			
			while (rs.next()){
				int memNumber = rs.getInt("mem_number");
				previousMemberNumberArr[previousMemberNumberIndex++] = memNumber;
			}
		}
		finally
		{
			closeAll(rs,pstmt,con);
		}

		return previousMemberNumberArr;
	}
	
	public void insertSelectingGroupRecord(int[][] group, ArrayList<MemberVO> memberList, int selectingGroupCount, final int ROW, final int COL) throws SQLException {
		PreparedStatement pstmt = null;
		Connection con = null;	
		
		System.out.println("인서트전");
		
		for (int i = 0; i < 6; i++)
		{
			for (int j = 0; j < 6; j++)
				System.out.print(group[i][j] + ", ");
			System.out.println();
		}
		
		try {
			con = getConnection();
			
			for (int i = 0; i < ROW; i++)
			{
				for (int j = 0; j < COL; j++)
				{
					String id = memberList.get(group[i][j] - 1).getId();
					System.out.println("아이디" + id);
					String sql = "insert into selecting_group (id, selecting_group_count, group_no) values (?, ?, ?)";
					pstmt = con.prepareStatement(sql);
					
					pstmt.setString(1, id);
					pstmt.setInt(2, selectingGroupCount);
					pstmt.setInt(3, i + 1);
					
					pstmt.executeUpdate();
				}
			}
		} finally {
			closeAll(pstmt, con);
		}
	}
	
	public void clearSelectingGroupRecord() throws SQLException{
		PreparedStatement pstmt = null;
		Connection con = null;
		
		try {
			con = getConnection();
			
			String sql = "delete from selecting_group";
			pstmt = con.prepareStatement(sql);
			pstmt.executeUpdate();
			
		} finally {
			closeAll(pstmt, con);
		}
	}
}

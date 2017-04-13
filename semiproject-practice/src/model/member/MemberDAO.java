package model.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import model.board.DataSourceManager;


public class MemberDAO {
	private static MemberDAO dao=new MemberDAO();
	private DataSource dataSource;
	private MemberDAO(){
		dataSource=DataSourceManager.getInstance().getDataSource();
	}
	public static MemberDAO getInstance(){		
		return dao;
	}	
	public void closeAll(PreparedStatement pstmt,
			Connection con) throws SQLException{
		closeAll(null,pstmt,con);
	}
	public void closeAll(ResultSet rs,PreparedStatement pstmt,
			Connection con) throws SQLException{
		if(rs!=null)
			rs.close();
		if(pstmt!=null)
			pstmt.close();
		if(con!=null)
			con.close();
	}
	
	
	public MemberVO login(String id,String password) throws SQLException{
		MemberVO vo=null;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			con=dataSource.getConnection();
			String sql="select id,password,mem_name,gender,birth_date,mem_type from member where id=? and password=? and getout='N'";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, password);
			rs=pstmt.executeQuery();
			if(rs.next()){
				vo=new MemberVO(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6));
			}
		}finally{
			closeAll(rs, pstmt,con);
		}
		return vo;
	}
	
	public void register(MemberVO vo) throws SQLException{
		Connection con=null;
		PreparedStatement pstmt=null;		
		try{
			con=dataSource.getConnection();
			String sql = "insert into member (id, password, mem_name, gender, birth_date, mem_type, mem_number) VALUES (?, ?, ?, ?, ?, ?, mem_number_seq.nextval)";
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPassword());
			pstmt.setString(3, vo.getName());
			pstmt.setString(4, vo.getGender());
			pstmt.setString(5, vo.getDateOfBirth());			
			pstmt.setString(6, vo.getMemberType());
			pstmt.executeUpdate();								
		}finally{
			closeAll(pstmt,con);
		}
	}
	public boolean idcheck(String id) throws SQLException{
		boolean flag=false;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;

		try{
			con=dataSource.getConnection();
			String sql="select count(*) from member where id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1,id);
			rs=pstmt.executeQuery();
			if(rs.next()&&(rs.getInt(1)>0)){				
				flag=false;
			}else{				
				flag=true;
			}
		}finally{
			closeAll(rs,pstmt,con);
		}
		return flag;
	}
	
	public void update(MemberVO vo) throws SQLException{
		Connection con=null;
		PreparedStatement pstmt=null;
		try{
			con=dataSource.getConnection();
			String sql=
				"update member set password=?, mem_name=?, gender=?, birth_date=?, mem_type=? where id=? and getout='N'";
			pstmt=con.prepareStatement(sql);			
			
			/*System.out.println("password" + vo.getPassword());
			System.out.println("name" + vo.getName());
			System.out.println("Date" + vo.getDateOfBirth());
			System.out.println("Gender" + vo.getGender());
			System.out.println("MemberType" + vo.getMemberType());
			System.out.println("id" + vo.getId());*/
			
			pstmt.setString(1,vo.getPassword());
			pstmt.setString(2,vo.getName());
			pstmt.setString(3,vo.getGender());
			pstmt.setString(4,vo.getDateOfBirth());	
			pstmt.setString(5,vo.getMemberType());
			pstmt.setString(6,vo.getId());
			pstmt.executeUpdate();			
		}finally{
			closeAll(pstmt,con);
		}
	}
	
	public void delete(String id) throws SQLException{
		System.out.println("delete " +  id);
		Connection con=null;
		PreparedStatement pstmt=null;
		try{
			con=dataSource.getConnection();
			String sql="update member set getout='Y' where id=?";
			pstmt=con.prepareStatement(sql);
					pstmt.setString(1,id);
			pstmt.executeUpdate();			
		}finally{
			closeAll(pstmt,con);
		}
	}
	
	public MemberVO findid(String name, String dateOfBirth) throws SQLException{
		MemberVO vo=null;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		//String id=null;
		try{
			con=dataSource.getConnection();
			String sql=
				"select id from member where mem_name=? and birth_date=?";
			pstmt=con.prepareStatement(sql);			

			pstmt.setString(1, name);
			pstmt.setString(2, dateOfBirth);	
			rs=pstmt.executeQuery();		
			while(rs.next()){ 
				vo=new MemberVO(rs.getString(1), name, dateOfBirth);
			   // id=rs.getString("id"); 
			   }
		}finally{
			closeAll(rs,pstmt,con);
		}
		//return id;
		return vo;
	}

	
	public ArrayList<MemberVO> getMemberList(String mode) throws SQLException{
	      ArrayList<MemberVO> memberList = new ArrayList<MemberVO>();
	      Connection con=null;
	      PreparedStatement pstmt=null;
	      ResultSet rs=null;
	      
	      try
	      {
	         con=dataSource.getConnection(); 
	         StringBuilder sql=new StringBuilder();
	         sql.append("select mem_number, id, mem_name, mem_type, gender ");
	         
	         if (mode == "all")
	         {   
	            sql.append("from member where mem_type like '%학생' and mem_number >= 1 and mem_number <= 36 order by mem_number");         
	            pstmt=con.prepareStatement(sql.toString());
	         }
	         else
	         {
	            sql.append("from member where mem_type = ? and mem_number >= 1 and mem_number <= 36 order by mem_number");
	            pstmt=con.prepareStatement(sql.toString());
	            
	            if (mode.equals("nomal"))
	               pstmt.setString(1, "일반학생");
	            else if (mode.equals("high"))
	               pstmt.setString(1, "우수학생");      
	         }
	   
	         rs=pstmt.executeQuery();
	         
	         while (rs.next()){
	            int memberNumber = rs.getInt("mem_number");
	            String id = rs.getString("id");
	            String name = rs.getString("mem_name");
	            String mem_type = rs.getString("mem_type");
	            String gender = rs.getString("gender");
	               
	            memberList.add(new MemberVO(memberNumber, id, name, mem_type, gender));
	         }
	      }
	      finally
	      {
	         closeAll(rs,pstmt,con);
	      }
	      return memberList;
	   }
}
















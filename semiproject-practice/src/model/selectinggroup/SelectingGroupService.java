package model.selectinggroup;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import model.member.MemberDAO;
import model.member.MemberVO;

public class SelectingGroupService {
	private ArrayList<MemberVO> memberList;
	private ArrayList<MemberVO> nomalMemberList;
	private ArrayList<MemberVO> highMemberList;
	
	private int selectingGroupCount;

	public static final int GROUP_SIZE = 6;
	public static final int ROW = 6;
	public static final int COL = 6;
	
	public int[][] prepare(){
		int[][] group = null;
		
		try {
			selectingGroupCount = SelectingGroupDAO.getInstance().getSelectingGroupCount();
			
			nomalMemberList = MemberDAO.getInstance().getMemberList("nomal");
			highMemberList = MemberDAO.getInstance().getMemberList("high");
			memberList = MemberDAO.getInstance().getMemberList("all");
			
			
			if  (selectingGroupCount == 0)
			{
				group = firstSelectingGroup();
			}
			else
			{
				for (int i = 0; i < selectingGroupCount; i++)
				{
					for (int j = 0; j < GROUP_SIZE; j++)
					{
						int[] previousMemberNumberArr = SelectingGroupDAO.getInstance().getSelectingGroupRecordList(i + 1, j + 1);
						
						for (int k = 0; k < previousMemberNumberArr.length; k++)
						{
							memberList.get(previousMemberNumberArr[k] - 1).addPreviousMemberNumberSet(previousMemberNumberArr);
						}
					}
				}
				
				for (int i = 0; i < memberList.size(); i++)
				{
					System.out.print(memberList.get(i).getMemberNumber() + " : ");
					Iterator<Integer> iterator = memberList.get(i).getPreviousMemberNumberSet().iterator();
					
					while (iterator.hasNext())
						System.out.print(iterator.next() + ", ");
					System.out.println();
				}
				group = selectingGroup();
			}				
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return group;
	}
	
	public boolean checkArrayIsFull(int[] arr){
		boolean isFull = true;
		
		for (int i = 0; i < arr.length; i++)
			if (arr[i] == 0)
			{
				isFull = false;
				break;
			}
				
				
		
		return isFull;
	}
	
	
	
	public int[][] firstSelectingGroup() {
		int[] highMemberIndexArr = getRandomIndexArr(GROUP_SIZE * 2);
		int[] nomalMemberIndexArr = getRandomIndexArr(GROUP_SIZE * 4);
		int[][] group = new int[ROW][COL];
		int count = 0;

		for (int i = 0; i < GROUP_SIZE; i++)
		{
			for (int j = 0; j < 2; j++)
			{
				int highMemberIndex = highMemberIndexArr[count++];
				group[i][j] = highMemberList.get(highMemberIndex).getMemberNumber();
			}
		}
		
		count = 0;
		
		for (int i = 0; i < GROUP_SIZE; i++)
		{
			for (int j = 2; j < 6; j++)
			{
				int nomalMemberIndex = nomalMemberIndexArr[count++];
				group[i][j] = nomalMemberList.get(nomalMemberIndex).getMemberNumber();
			}
		}
		
		try {
			SelectingGroupDAO.getInstance().insertSelectingGroupRecord(group, memberList, selectingGroupCount + 1, ROW, COL);
			System.out.println("배정 결과");
			for (int i = 0; i < ROW; i++)
			{
				for (int j = 0; j < COL; j++)
					System.out.print(group[i][j] + ", ");
				System.out.println();
			}	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return group;	
	}
	
	public void setHighMember(int[][] group, long start) throws SetMemberOverlapException {
		int[] highMemberIndexArr = getRandomIndexArr(GROUP_SIZE * 2);
		int highMemberIndexCount = 0;
		int[] groupIndexArr = getRandomIndexArr(GROUP_SIZE);
		ArrayList<Integer> groupIndexList = new ArrayList<Integer>();
		Random random = new Random();
			
		for (int i = 0; i < groupIndexArr.length; i++)
			groupIndexList.add(groupIndexArr[i]);
		

		for (int i = 0; i < GROUP_SIZE; i++) {
			int highMemberIndex = highMemberIndexArr[highMemberIndexCount++];
			group[i][0] = highMemberList.get(highMemberIndex).getMemberNumber();
		}
		
		for (int i = 0; i < GROUP_SIZE; i++) {
			int highMemberIndex = highMemberIndexArr[highMemberIndexCount++];
			int highMemberNumber = highMemberList.get(highMemberIndex).getMemberNumber();
			
			while (true) 
			{
				int groupIndex;
				int groupNumber;
				boolean isOverlap = false;
				
				while (true)
				{
					groupNumber = random.nextInt(GROUP_SIZE);
					groupIndex = COL - getGroupVacancyNumber(group, groupNumber);

					
					if (groupIndex == 1)
						break;
				}
				
				for (int j = 0; j < group[groupNumber].length; j++)
				{
					Iterator<Integer> iterator = memberList.get(highMemberNumber - 1).getPreviousMemberNumberSet().iterator();
					
					while (iterator.hasNext())
					{
						int previousMemberNumber = iterator.next();
						
						if (group[groupNumber][j] == previousMemberNumber)
						{
							isOverlap = true;
							break;
						}
					}
				}
				
				if (!isOverlap) {
					group[groupNumber][1] = highMemberNumber;
					isOverlap = false;
					break;
				}
				
				long end=System.nanoTime();
				
				if (((end - start) / 1000000.0) > 1000.0)
				{
				/*	System.out.println("--------------------------------");
					for (int x = 0; x < ROW; x++)
					{
						for (int y = 0; y < COL; y++)
							System.out.print(group[x][y] + ", ");
						System.out.println();
					}
					System.out.println("--------------------------------");*/
					
					
					int _groupNumber = getVacancyGroupNumber(group);
					group[_groupNumber][1] = highMemberNumber;
					break;
					
					/*System.out.print("자리배정되지 않은 우수학생 : ");
					System.out.println(highMemberNumber);
					for (int x = 0; x < ROW; x++)
					{
						for (int y = 0; y < COL; y++)
							System.out.print(group[x][y] + ", ");
						System.out.println();
					}
					throw new SetMemberOverlapException("우수학생 배정 시간 초과, 다시 실행하시오.");*/
				}
			}
		}
	}
	
	public void setNomalMember(int[][] group, long start) {
		int[] nomalMemberIndexArr = getRandomIndexArr(GROUP_SIZE * 4);
		int nomalMemberIndexCount = 0;
		int notAllocateMemberArr[] = new int[36];
		ArrayList<Integer> notAllocateMemberList = new ArrayList<Integer>();
		Random random = new Random();
		
		for (int i = 0; i < GROUP_SIZE * 4; i++)
		{
			int nomalMemberIndex = nomalMemberIndexArr[nomalMemberIndexCount++];
			int nomalMemberNumber = nomalMemberList.get(nomalMemberIndex).getMemberNumber();
						
			while (true)
			{
				int groupIndex;
				int groupNumber;
				boolean isOverlap = false;
				boolean isExit = false;
				
				if (isExit == true)
					break;
				
				
								
				while (true)
				{
					groupNumber = random.nextInt(GROUP_SIZE);
					groupIndex = COL - getGroupVacancyNumber(group, groupNumber);
					
					
					if (groupIndex != COL)
						break;
				}
				
				
				for (int j = 0; j < group[groupNumber].length; j++)
				{
					Iterator<Integer> iterator = memberList.get(nomalMemberNumber - 1).getPreviousMemberNumberSet().iterator();
					
					while (iterator.hasNext())
					{
						int previousMemberNumber = iterator.next();
						
						if (group[groupNumber][j] == previousMemberNumber)
						{
							isOverlap = true;
							break;
						}
					}
				}
				if (!isOverlap) 
				{
					group[groupNumber][groupIndex] = nomalMemberNumber;
					break;
				}
				
				long end=System.nanoTime();
					
				if (((end - start) / 1000000.0) > 1000.0)
				{
					
					System.out.println("자리배정되지 않은 일반학생");
					System.out.println("--------------------------");
					

					for (int x = 0; x < ROW; x++)
					{
						for (int y = 0; y < COL; y++)
						{
							System.out.print(group[x][y] + ", ");
							if (group[x][y] != 0)
							{
								notAllocateMemberArr[group[x][y] -1] = group[x][y];
							}
								
						}
						System.out.println();
					}
					System.out.println("--------------------------");
					System.out.println();
					System.out.println("--------------------------");
					for (int x= 0; x < notAllocateMemberArr.length; x++)
					{
						if (notAllocateMemberArr[x] == 0)
						{
							System.out.print(x + 1 + ", ");
							notAllocateMemberList.add(x + 1);
						}
					}
					System.out.println();
					System.out.println("--------------------------");
					
					for (int x = 0; x < GROUP_SIZE; x++)
					{
						while (true)
						{
							int index = 6 - function(group, x);
							System.out.println("인덱스 : " + index);
							if (index == 6)
								break;
							
							int notAllocateMemberListIndex = random.nextInt(notAllocateMemberList.size());
							group[x][index] = notAllocateMemberList.get(notAllocateMemberListIndex);
							notAllocateMemberList.remove(notAllocateMemberListIndex);
							
							
						}
						isExit = true;
					}
					System.out.println("--------------------------");
					for (int x = 0; x < ROW; x++)
					{
						for (int y = 0; y < COL; y++)
						{
							System.out.print(group[x][y] + ", ");
							if (group[x][y] != 0)
							{
								notAllocateMemberArr[group[x][y] -1] = group[x][y];
							}
								
						}
						System.out.println();
					}
					System.out.println("--------------------------");
					
					return;
				}
			}
		}
	}
	
	/*for (int j = 0; j < group[groupNumber].length; j++)
	{
		Iterator<Integer> iterator = memberList.get(highMemberNumber - 1).getPreviousMemberNumberSet().iterator();
		
		while (iterator.hasNext())
		{
			int previousMemberNumber = iterator.next();
			
			if (group[groupNumber][j] == previousMemberNumber)
			{
				isOverlap = true;
				break;
			}
		}
	}*/
	
	public int function(int[][] group, int groupNumber){
		int count = 0;
		for (int y = 0; y < 6; y++)
		{
			if (group[groupNumber][y] == 0)
				count++;
		}
		return count;
	}

	
	public int getGroupVacancyNumber(int[][] group, int groupNumber){
		int vacancyNumber = 0;
		
		for (int i = 0; i < group[groupNumber].length; i++)
			if (group[groupNumber][i] == 0)
				vacancyNumber++;
		
		return vacancyNumber;
	}
	
	public int getVacancyGroupNumber(int[][] group){
		int vacancyGroupNumber = 0;
		

		for (int i = 0; i < ROW; i++)
		{
			for (int j = 0; j < 2; j++)
			{
				if (group[i][j] == 0)
				{
					vacancyGroupNumber = i;
					break;
				}
					
			}
		}
		return vacancyGroupNumber;
	}

	
	public int[][] selectingGroup() throws SQLException{
		int[][] group = new int[ROW][COL];
		
		 
			long highStart = System.nanoTime();
			try {
				setHighMember(group, highStart);
				
				for (int i = 0; i < ROW; i++)
				{
					for (int j = 0; j < COL; j++)
						System.out.print(group[i][j] + ", ");
					System.out.println();
				}
				
				long nomalStart = System.nanoTime();
				setNomalMember(group, nomalStart);
				
				SelectingGroupDAO.getInstance().insertSelectingGroupRecord(group, memberList, selectingGroupCount + 1, ROW, COL);
				
				System.out.println("배정 결과");
				for (int i = 0; i < ROW; i++)
				{
					for (int j = 0; j < COL; j++)
						System.out.print(group[i][j] + ", ");
					System.out.println();
				}
				
			} catch (SetMemberOverlapException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return group;	
	}
	
	public int[] getRandomIndexArr(int size){
		
		int randomIndexArr[] = new int[size];	
		
		Random random = new Random();
		
        for(int i = 0; i < randomIndexArr.length; i++)
        {
        	randomIndexArr[i] = random.nextInt(size);
            for(int j = 0; j < i; j++) 
            {                 
                if(randomIndexArr[i] == randomIndexArr[j])  
                    i--;
            }
        }
        return randomIndexArr;
	}
}

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@page import="model.member.MemberVO"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">
	function gbba() {
		
		mine = document.frm1.gbb.value;
		yours = you();
		
	        		
		if (mine == yours)
			alert(pae(mine, yours) + "${sessionScope.mvo.name}님DRAW 탈퇴할수 없습니다. DRAW");
		else if (mine == 2 && yours == 0)
		{
			alert(pae(mine, yours) + "${sessionScope.mvo.name}님WIN 탈퇴하세요. WIN" );
			
			location.href="${pageContext.request.contextPath}/DispatcherServlet?command=delete&id=${sessionScope.mvo.id}";
			
		}
			 
		
		else if (mine == 0 && yours == 2)
			alert(pae(mine, yours) + "${sessionScope.mvo.name}님LOSE 탈퇴할수 없습니다. LOSE");
		else if (mine < yours)
		{
			alert(pae(mine, yours) + "${sessionScope.mvo.name}님WIN 탈퇴하세요. WIN" );
			
			location.href="${pageContext.request.contextPath}/DispatcherServlet?command=delete&id=${sessionScope.mvo.id}";
			
		}
		
		else
			alert(pae(mine, yours) + "${sessionScope.mvo.name}님LOSE 탈퇴할수 없습니다. LOSE");
				
	}
	
	
	
	function pae(mine, yours) {
		//arr = ["가위", "바위", "보"];
		arr = ["바위", "가위", "보"];
		return "(나 : " + arr[mine] + ", 컴퓨터 : " +arr[yours] + ")";
	}
	
	function you() {
		/* Math.random()은 return 값은 실수값으로 0부터 1보다 작은수로 들어온다. */
		num = Math.floor(Math.random() * 3);
		//alert(num);
		return num;
	}
</script>
</head>

<body>

<% MemberVO vo=(MemberVO)session.getAttribute("mvo"); 
	if(vo!=null){
%>
<input type="hidden" name="command" value="delete">
<fieldset>
		<legend>가위바위보 게임을 이겨야만 탈퇴 할수 있습니다!</legend>
		<form name="frm1" method="post" action="${pageContext.request.contextPath}/DispatcherServlet">
			<label for="gawi">가위</label>
			<input type="radio" name="gbb" id="gawi" value="1" onclick="gbba();" />
			
			&nbsp;&nbsp;&nbsp;<label for="bawi">바위</label>
			<input type="radio" name="gbb" id="bawi" value="0" onclick="gbba();" />
			
			&nbsp;&nbsp;&nbsp;<label for="bo">보</label>
			<input type="radio" name="gbb" id="bo" value="2" onclick="gbba();" />
			<img src="game.png" height="100" width="200">
		</form>
	</fieldset>
	<%}else{ %>
		<script type="text/javascript">
			alert("로그인하세요!");
			location.href="${pageContext.request.contextPath}/login.jsp";
		</script>
<%} %>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@page import="model.member.MemberVO"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:choose>
<c:when test="${sessionScope.mvo==null}">
	<script type="text/javascript">
			alert("탈퇴 고맙습니다!");
			window.opener.location.href="${pageContext.request.contextPath}/login.jsp"; 
			window.close(); 	
		</script>
</c:when>
<c:otherwise>
		<script type="text/javascript">
			alert("탈퇴 실패!");
			window.opener.location.href="${pageContext.request.contextPath}/main.jsp"; 
			window.close(); 
		</script>		
</c:otherwise>
</c:choose>
</body>
</html>
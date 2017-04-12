
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
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
			alert(" 아이디 찾기를 실패하셨습니다");
			window.close();
		</script>
</c:when>
<c:otherwise>
		<script type="text/javascript">
		alert("아이디는 ${sessionScope.mvo.id} 입니다! ");
			window.close();
		</script>		
</c:otherwise>
</c:choose>
</body>
</html>
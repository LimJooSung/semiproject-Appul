<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/mystyle.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#deleteBtn").click(function() {
			return confirm("정말 삭제 하시겠습니까?");
		}); // click
	
	}); // ready
	
</script>
</head>
<body>
	<jsp:include page="/layout/header.jsp" />
	<div class="container">
		<div class="row">
			<div class="col-sm-2"></div>
			<div class="col-sm-8">
				<div class="panel panel-success">
					<div class="panel-heading" align="center">건의사항 게시글 상세보기</div>
					<div class="panel-body" align="center">
						<table class="table">
							<tbody>
								<tr>
									<td>글번호 | &nbsp;${requestScope.bvo.boardNo }</td>
									<td>제&nbsp;&nbsp;&nbsp;목 | &nbsp;${requestScope.bvo.title}
									</td>
									<td>조회수 | &nbsp;${requestScope.bvo.hits }</td>
								</tr>
								<tr>
									<td>작성자 | &nbsp;${requestScope.bvo.member.name }</td>
									<td colspan="2">작성일 | &nbsp;${requestScope.bvo.timePosted }</td>
								</tr>
								<tr>
									<td colspan="3"><pre>${requestScope.bvo.content}</pre></td>
								</tr>
							</tbody>
						</table>
					</div>
					<div class="panel-footer" align="right">
						<c:if test="${sessionScope.mvo.id == requestScope.bvo.member.id }">
							<a
								href="${pageContext.request.contextPath}/DispatcherServlet?command=proUpdateView&boardNo=${requestScope.bvo.boardNo }"><img
								src="${pageContext.request.contextPath}/img/modify_btn.jpg"
								border="0"> </a>
							<a
								href="${pageContext.request.contextPath}/DispatcherServlet?command=proDeletePosting&boardNo=${requestScope.bvo.boardNo }"
								id="deleteBtn"><img
								src="${pageContext.request.contextPath}/img/delete_btn.jpg"
								border="0"></a>
						</c:if>
						<a
							href="${pageContext.request.contextPath}/DispatcherServlet?command=proList"><img
							src="${pageContext.request.contextPath}/img/list_btn.jpg"
							border="0"></a>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="container">
		<div class="row"></div>
	</div>
	<jsp:include page="/layout/footer.jsp" />
</body>
</html>
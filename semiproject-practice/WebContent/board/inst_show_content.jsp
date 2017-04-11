<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Bootstrap Example</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/mystyle.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/board.css" >
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<meta charset="UTF-8">
<title>show content</title>
<script src="//code.jquery.com/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#deleteBtn").click(function() {
			return confirm("정말 삭제 하시겠습니까?");
		});	// click
		$("#fileCheck").click(function() {
			if (confirm("다운로드 하시겠습니까?")) {
				$("#fileCheck").submit();
			} else {
				return;
			}
		}); 
		/* $("#fileCheck").click(function() {
			$.ajax({
				type : "get",
				url : "DispatcherServlet",
				data : "command=instDownload&boardNo=" + ${requestScope.bvo.boardNo },
				success : function() {
					alert("hello jquery ajax");
				}	// success
			});	// ajax
		});	// click */
	});	// ready
</script>
</head>
<body>
	<c:import url="/layout/header.jsp"></c:import>
	<div class="container">
		<div class="row">
			<div class="col-sm-2"></div>
			<div class="col-sm-8" align="center">
				<div class="panel panel-warning">
					<div class="panel-heading" align="center">강사 게시글 상세보기</div>
					<div class="panel-body">
						<table class="table">
							<tbody>
								<tr>
									<td>제&nbsp;&nbsp;&nbsp;목 | &nbsp;${requestScope.bvo.title} </td>
									<td>조회수 | &nbsp;${requestScope.bvo.hits }</td>
									<td>평&nbsp;&nbsp;&nbsp;점 | &nbsp;${requestScope.bvo.avgRating } </td>
								</tr>
								<tr>
									<td>작성자 | &nbsp;${requestScope.bvo.member.name }</td>
									<td>작성일 | &nbsp;${requestScope.bvo.timePosted }</td>
									<td>
										<form action="${pageContext.request.contextPath}/DispatcherServlet" id="fileCheck" name="">
											<input type="hidden" name="command" value="instDownload">
											<input type="hidden" name="boardNo" value="${requestScope.bvo.boardNo }">
											파&nbsp;&nbsp;&nbsp;일 | <a href="#">&nbsp;${requestScope.bvo.attachedFile }</a>
										</form>
									</td>
								</tr>
								<tr>
									<td colspan="3">
										<pre>${requestScope.bvo.content}</pre>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
					<div class="panel-footer" align="right">
						<c:if test="${sessionScope.mvo.id == requestScope.bvo.member.id }">
							<a href="${pageContext.request.contextPath}/DispatcherServlet?command=instUpdateView&boardNo=${requestScope.bvo.boardNo }"><img src="${pageContext.request.contextPath}/img/modify_btn.jpg" border="0"></a>
							<a href="${pageContext.request.contextPath}/DispatcherServlet?command=instDeletePosting&boardNo=${requestScope.bvo.boardNo }" id="deleteBtn"><img src="${pageContext.request.contextPath}/img/delete_btn.jpg" border="0"></a>
						</c:if>
							<a href="${pageContext.request.contextPath}/DispatcherServlet?command=instList"><img src="${pageContext.request.contextPath}/img/list_btn.jpg" border="0"></a>
					</div>
				</div>
			</div>
			<div class="col-sm-2"></div>
		</div>
	</div>
	<c:import url="/layout/footer.jsp"></c:import>
</body>
</html>
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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<meta charset="UTF-8">
<title>show content</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/board.css" type="text/css">
<script type="text/javascript">
	$(document).ready(function() {
		$("#deleteBtn").click(function() {
			return confirm("정말 삭제 하시겠습니까?");
		});	// click
	});	// ready
</script>

</head>
<body>
	<c:import url="/layout/header.jsp"></c:import>
<div class="container">
	<div class="row">
		<div class="col-sm-2"></div>
		<div class="col-sm-8" align="center">
			<div class="panel panel-info">
				<div class="panel-heading" align="center">QnA 게시글 상세보기</div>
				<div class="panel-body">
					<table class="table">
							<tbody>
								<tr>
									<td>글번호 | &nbsp;${requestScope.bvo.boardNo } </td>
									<td>제&nbsp;&nbsp;&nbsp;목 | &nbsp;${requestScope.bvo.title} </td>
									<td>조회수 | &nbsp;${requestScope.bvo.hits }</td>
								</tr>
								<tr>
									<td>작성자 | &nbsp;${requestScope.bvo.member.name }</td>
									<td colspan="2">작성일 | &nbsp;${requestScope.bvo.timePosted }</td>
								</tr>
								<tr>
									<td colspan="3">
										<pre>${requestScope.bvo.content}</pre>
									</td>
								</tr>
							</tbody>
						</table>
						<%-- 	<!-- 이부분은 댓글 html -->
							<if test="${requestScope.commentList != null }">
							<c:forEach var ="comment" items="${requestScope.commentList}">
								<tr>
								<!-- 아이디, 작성날짜 -->
								<td width="150">
								<div>
									${comment.comment_id}<br>
									<font size="2" color="lightgray">${comment.comment_date}</font>
								</div>
								</td>
								<!-- 본문내용 -->
								<td width="550">
									<div class="text_wrapper">
										${comment.comment_content }
									</div>
								</td>
								<!-- 버튼 -->
								<td width="100">
									<div id="btn" style="text-align:center;">
									<a href="#">[답변]</a><br>
									<!-- 댓글 작성자만 수정, 삭제 가능하도록 -->
									<c:if test="${comment.comment_id == sessionScope.sessionID}">
										<a href="#">[수정]</a>
										<a href="#">[삭제]</a>
										</c:if>
										</div>
									</td>
								</tr>
						</c:forEach>
				</if>
				<!-- 로그인 했을 경우만 댓글 작성가능 -->
				<c:if test="${sessionScope.sessionID !=null }">
				<tr bgcolor ="#F5F5F5">
				<form id="writeCommentForm">
					<input type="hidden" name="comment_board" value="${board.board_num}">
					<input type="hidden" name="comment_id" value="${sessionScope.sessionID }">
					<!-- 아이디 -->
					<td width="150">
						<div>
							${sessionScope.sessionID }
						</div>
					</td>
					<!-- 본문 작성 -->
					<td width="550">
						<div>
							<textarea name="comment_content" rows="4" cols="70"></textarea>
						</div>
					</td>
					<!-- 댓글 등록 버튼 -->
					<td width="100">
						<div id="btn" style="text-align:center;">
							<p><a href="#" onclick="writeCmt()">[댓글등록]
				</form> --%>
				</div>
				<br>
				<br> 
				<div class="panel-footer" align="right">
						<c:if test="${sessionScope.mvo.id == requestScope.bvo.member.id }">
							<a href="${pageContext.request.contextPath}/DispatcherServlet?command=QNAupdatePostingView&boardNo=${requestScope.bvo.boardNo }"><img src="${pageContext.request.contextPath}/img/modify_btn.jpg" border="0"></a>
							<a href="${pageContext.request.contextPath}/DispatcherServlet?command=QNAdeletePosting&boardNo=${requestScope.bvo.boardNo }" id="deleteBtn"><img src="${pageContext.request.contextPath}/img/delete_btn.jpg" border="0"></a>
						</c:if>
							<a href="${pageContext.request.contextPath}/DispatcherServlet?command=QNAboardlist"><img src="${pageContext.request.contextPath}/img/list_btn.jpg" border="0"></a>
					</div>
			</div>
		</div>
		<div class="col-sm-2"></div>
	</div>
</div>
</body>
<c:import url="/layout/footer.jsp"></c:import>
</html>
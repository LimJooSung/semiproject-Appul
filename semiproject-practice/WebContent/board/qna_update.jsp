<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Update View</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/mystyle.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<meta charset="UTF-8">
<title>write</title>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/board.css" type="text/css">
<script type="text/javascript">
$(document).ready(function() {
	$("#test").click(function(){
		$("#update").serialize();
	}); //click
});//ready
	function content_submit() {
		var f = document.write_form;
		if (f.title.value == "") {
			alert("제목을 입력하세요!");
			f.title.focus();
			return;
		}
		if (f.content.value == "") {
			alert("내용을 입력하세요!");
			f.content.focus();
			return;
		}
		f.submit();
	}
	function cancel() {
		var f = document.update_form;
		f.reset();
	}
</script>
</head>
<body>
	<jsp:include page="/layout/header.jsp" />
	<div class="container">
		<div class="row">
			<div class="col-sm-2"></div>
			<div class="col-sm-8">
				<div class="panel panel-info">
					<div class="panel-heading" align="center">QnA 게시글 수정</div>
					<div class="panel-body" align="center">
						<form action="${pageContext.request.contextPath}/DispatcherServlet?command=qnaupdatePosting" method="post" name="write_form" enctype="multipart/form-data">
							<input type="hidden" name="boardNo" value="${requestScope.bvo.boardNo }">
							<table class="table">
								<tbody>
									<tr> 
										<td colspan="4">제&nbsp;&nbsp;&nbsp;목 | 
											<c:forEach begin="0" end="7">&nbsp;</c:forEach>
											<input type="text" name="title" value=${requestScope.bvo.title } size="71">
											<c:choose>
											<c:when test="${bvo.secret =='Y'}">
											<input type="checkbox" name="secret" value="Y" checked>비밀글
											</c:when>
											<c:otherwise>
											<input type="checkbox" name="secret" value="Y">비밀글
											</c:otherwise>											
											</c:choose>
										</td>
									</tr>
									<tr>
										<td>작성자 | 
											<c:forEach begin="0" end="7">&nbsp;</c:forEach>
											${requestScope.bvo.member.name}
										</td>
										<td></td>
										<td colspan="2"><input type="file"  name="attachedFile" ></td>
									</tr>
									<tr>
										<td colspan="4" align="center">&nbsp;&nbsp; 
											<textarea cols="70" rows="15" name="content">${requestScope.bvo.content }</textarea>
										</td>
									</tr>
									<tr>
										<td colspan="4" align="center">
											<a href="#"><img src="${pageContext.request.contextPath}/img/write_btn.jpg" alt="수정" onclick="content_submit()"></a>
											<a href="#"><img class="action" src="${pageContext.request.contextPath}/img/cancel.gif" onclick="cancel()"></a>
										</td>
									</tr>
								</tbody>
							</table>
						</form>
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





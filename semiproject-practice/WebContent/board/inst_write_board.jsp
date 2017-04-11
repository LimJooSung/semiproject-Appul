<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>write</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/mystyle.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript">
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
		var f = document.write_form;
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
				<div class="panel panel-warning">
					<div class="panel-heading" align="center">강사 게시글 작성</div>
					<div class="panel-body" align="center">
						<form action="${pageContext.request.contextPath}/DispatcherServlet?command=instWritePosting" method="post" name="write_form" enctype="multipart/form-data">
							<input type="hidden" name="command" value="instWritePosting">
							<table class="table">
								<tbody>
									<tr> 
										<td colspan="4">제&nbsp;&nbsp;&nbsp;목 | 
											<c:forEach begin="0" end="7">&nbsp;</c:forEach>
											<input type="text" name="title" size="71">
										</td>
									</tr>
									<tr>
										<td>작성자 | 
											<c:forEach begin="0" end="7">&nbsp;</c:forEach>
											${sessionScope.mvo.name}
										</td>
										<td></td>
										<td colspan="2"><span class="glyphicons glyphicons-file-plus"></span><input type="file" name="attachedFile"></td>
									</tr>
									<tr>
										<td colspan="4" align="center">&nbsp;&nbsp; 
											<textarea cols="70" rows="15" name="content"></textarea>
										</td>
									</tr>
									<tr>
										<td colspan="4" align="center">
											<a href="#"><img src="${pageContext.request.contextPath}/img/write_btn.jpg" alt="글입력" onclick="content_submit()"></a>
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




<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<title>write</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/board.css" type="text/css">
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
<c:import url="/layout/header.jsp"></c:import>
<br>
<div class="container">
	<div class="row">
		<div class="col-sm-2"></div>
		<div class="col-sm-8" align="center">
			<div class="panel panel-info">
				<div class="panel-heading" align="center">QnA 게시글 작성</div>
				<div class="panel-body">
 				<!-- </form> -->
				<form action="${pageContext.request.contextPath}/DispatcherServlet" method="post" name="write_form">
					<input type="hidden" name="command" value="QNAwrite">
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
								<td>
									<span class="glyphicons glyphicons-file-plus"></span><input type="file">
									<input type="checkbox">비밀글</td>
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
				<div class="panel-footer" align="center">
				</div>
			</div>
		</div>
		<div class="col-sm-2"></div>
	</div>
</div>
<c:import url="/layout/footer.jsp"></c:import>
</body>
</html>





<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../favicon.ico">

    <title>Sticky Footer Navbar Template for Bootstrap</title>
    
<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

    <!-- Bootstrap core CSS -->
    <link href="../../dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="sticky-footer-navbar.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="../../assets/js/ie-emulation-modes-warning.js"></script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
<style type="text/css">
/* Sticky footer styles
-------------------------------------------------- */
html {
  position: relative;
  min-height: 100%;
}
body {
  /* Margin bottom by footer height */
  margin-bottom: 60px;
}
.footer {
  position: absolute;
  bottom: 20px;
  width: 100%;
  /* Set the fixed height of the footer here */
  height: 70px;
  background-color: #f5f5f5;
  padding: 15px;
}


/* Custom page CSS
-------------------------------------------------- */
/* Not required for template or sticky footer method. */

body > .container {
  padding: 60px 15px 0;
}
.container .text-muted {
  margin: 20px 0;
}

.footer > .container {
  padding-right: 15px;
  padding-left: 15px;
}

code {
  font-size: 80%;
}
</style>
  </head>

<body>
	<jsp:include page="header_test.jsp" />

    <!-- Begin page content -->
    <div class="container">
		<div class="row">
			<div class="col-sm-4">
				<div class="panel panel-success">
					<div class="panel-heading" align="center">건의사항 게시판</div>
					<div class="panel-body">
						<table class="table table-condensed">
							<thead>
								<tr>
									<th>제 목</th>
									<th>작성자</th>
									<th>작성일</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>선생님 건의사항 있습니다!!!</td>
									<td align="center">오남준</td>
									<td align="center">2017. 4. 4</td>
								</tr>
								<tr>
									<td>선생님 건의사항 있습니다!!!</td>
									<td align="center">오남준</td>
									<td align="center">2017. 4. 4</td>
								</tr>
								<tr>
									<td>선생님 건의사항 있습니다!!!</td>
									<td align="center">오남준</td>
									<td align="center">2017. 4. 4</td>
								</tr>
								<tr>
									<td>선생님 건의사항 있습니다!!!</td>
									<td align="center">오남준</td>
									<td align="center">2017. 4. 4</td>
								</tr>
								<tr>
									<td>선생님 건의사항 있습니다!!!</td>
									<td align="center">오남준</td>
									<td align="center">2017. 4. 4</td>
								</tr>
								<tr>
									<td>선생님 건의사항 있습니다!!!</td>
									<td align="center">오남준</td>
									<td align="center">2017. 4. 4</td>
								</tr>
							</tbody>
						</table>
					</div>
					<div class="panel-footer" align="center">
						<a class="btn btn-default" href="#" role="button">게시판으로 이동
							&raquo;</a>
					</div>
				</div>
			</div>
			<div class="col-sm-4">
				<div class="panel panel-info">
					<div class="panel-heading" align="center">QnA 게시판</div>
					<div class="panel-body">
						<table class="table table-condensed">
							<thead>
								<tr>
									<th>제 목</th>
									<th>작성자</th>
									<th>작성일</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>선생님 질문 있습니다!!!</td>
									<td align="center">오남준</td>
									<td align="center">2017. 4. 4</td>
								</tr>
								<tr>
									<td>선생님 질문 있습니다!!!</td>
									<td align="center">오남준</td>
									<td align="center">2017. 4. 4</td>
								</tr>
								<tr>
									<td>선생님 질문 있습니다!!!</td>
									<td align="center">오남준</td>
									<td align="center">2017. 4. 4</td>
								</tr>
								<tr>
									<td>선생님 질문 있습니다!!!</td>
									<td align="center">오남준</td>
									<td align="center">2017. 4. 4</td>
								</tr>
								<tr>
									<td>선생님 질문 있습니다!!!</td>
									<td align="center">오남준</td>
									<td align="center">2017. 4. 4</td>
								</tr>
								<tr>
									<td>선생님 질문 있습니다!!!</td>
									<td align="center">오남준</td>
									<td align="center">2017. 4. 4</td>
								</tr>
							</tbody>
						</table>
					</div>
					<div class="panel-footer" align="center">
						<a class="btn btn-default" href="#" role="button">게시판으로 이동
							&raquo;</a>
					</div>
				</div>
			</div>
			<div class="col-sm-4">
				<div class="panel panel-warning">
					<div class="panel-heading" align="center">강의자료 게시판</div>
					<div class="panel-body">
						<table class="table table-condensed">
							<thead>
								<tr>
									<th>제 목</th>
									<th>작성자</th>
									<th>작성일</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="bvo" items="${requestScope.lvo.list}" begin="0" end="5">
									<tr>
										<td  align="center">
											<a href="${pageContext.request.contextPath}/DispatcherServlet?command=showContent&boardNo=${bvo.boardNo }">
											 ${bvo.title }</a>
										</td>
										<td  align="center">${bvo.member.name }</td>
										<td  align="center">${bvo.timePosted }</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					<div class="panel-footer" align="center">
						<a class="btn btn-default" href="DispatcherServlet?command=instList" role="button">게시판으로 이동
							&raquo;</a>
					</div>
				</div>
			</div>
		</div>
		
	</div>
	
	<jsp:include page="footer_test.jsp" />


    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script src="../../dist/js/bootstrap.min.js"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>
  </body>
</html>

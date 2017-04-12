<%@page import="model.member.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Bootstrap Example</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/mystyle.css">
<!-- Custom styles for this template -->
<link href="sticky-footer.css" rel="stylesheet">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<% MemberVO vo=(MemberVO)session.getAttribute("mvo"); 
	if(vo!=null){
%>
	<jsp:include page="/layout/header.jsp" />
	<div class="container">
		<div class="row">
			<div class="col-sm-4">
				<div class="panel panel-success">
					<div class="panel-heading" align="center">건의사항 게시판</div>
					<div class="panel-body">
						<table class="table table-condensed">
							<thead>
								<tr>
									<th width="50%">제 목</th>
									<th>작성자</th>
									<th>작성일</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="pvo" items="${requestScope.pvo.list}" begin="0"
									end="5">
									<tr>
										<td align="center">
											<%-- <a href="${pageContext.request.contextPath}/DispatcherServlet?command=proShowContent&boardNo=${pvo.boardNo }">
											 ${pvo.title }</a> --%> <c:choose>
												<c:when test="${pvo.secret =='Y'}">
													<c:choose>
														<c:when
															test="${pvo.member.id == sessionScope.mvo.id||sessionScope.mvo.memberType=='강사'}">
															<a
																href="${pageContext.request.contextPath}/DispatcherServlet?command=proShowContent&boardNo=${pvo.boardNo }">
																<img src="${pageContext.request.contextPath}/img/lock.jpg"
																width="18" height="18" />
																${pvo.title} 
																
															</a>
														</c:when>
														<c:otherwise>
											  <img src="${pageContext.request.contextPath}/img/lock.jpg" width="18" height="18" />
											 ${pvo.title}
											 </c:otherwise>
													</c:choose>
												</c:when>
												<c:otherwise>
													<a
														href="${pageContext.request.contextPath}/DispatcherServlet?command=proShowContent&boardNo=${pvo.boardNo }">
														${pvo.title}
														<c:if test="${pvo.totalCommentCount != 0}">[${pvo.totalCommentCount }]</c:if>
														</a>
												</c:otherwise>
											</c:choose>
										</td>

										<td align="center">${pvo.member.name }</td>
										<td align="center">${pvo.timePosted }</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					<div class="panel-footer" align="center">
						<a class="btn btn-default"
							href="DispatcherServlet?command=proList" role="button">게시판으로
							이동 &raquo;</a>
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
									<th width="50%">제 목</th>
									<th>작성자</th>
									<th>작성일</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="qvo" items="${requestScope.qvo.list}" begin="0"
									end="5">
									<tr>
										<td align="center">
											<%-- <a href="${pageContext.request.contextPath}/DispatcherServlet?command=qnashowContent&boardNo=${qvo.boardNo }">
											 ${qvo.title }</a> --%> <c:choose>
												<c:when test="${qvo.secret =='Y'}">
													<c:choose>
														<c:when
															test="${qvo.member.id == sessionScope.mvo.id||sessionScope.mvo.memberType=='강사'}">
															<a
																href="${pageContext.request.contextPath}/DispatcherServlet?command=qnashowContent&boardNo=${qvo.boardNo }">
																<img src="${pageContext.request.contextPath}/img/lock.jpg"
																width="18" height="18" />
																${qvo.title} 
															</a>
														</c:when>
														<c:otherwise>
											 <img src="${pageContext.request.contextPath}/img/lock.jpg" width="18" height="18" />
											 ${qvo.title}
														</c:otherwise>
													</c:choose>
												</c:when>
												<c:otherwise>
													<a
														href="${pageContext.request.contextPath}/DispatcherServlet?command=qnashowContent&boardNo=${qvo.boardNo }">
														${qvo.title}
														<c:if test="${qvo.totalCommentCount != 0}">[${qvo.totalCommentCount }]</c:if>
														</a>
												</c:otherwise>
											</c:choose>
										</td>
										<td align="center">${qvo.member.name }</td>
										<td align="center">${qvo.timePosted }</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					<div class="panel-footer" align="center">
						<a class="btn btn-default"
							href="DispatcherServlet?command=qnaboardlist" role="button">게시판으로
							이동 &raquo;</a>
					</div>
				</div>
			</div>
			<div class="col-sm-4">
				<div class="panel panel-warning">
					<div class="panel-heading" align="center">강사 게시판</div>
					<div class="panel-body">
						<table class="table table-condensed">
							<thead>
								<tr>
									<th width="50%">제 목</th>
									<th>작성자</th>
									<th>작성일</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="ivo" items="${requestScope.ivo.list}" begin="0"
									end="5">
									<tr>
										<td align="center"><a
											href="${pageContext.request.contextPath}/DispatcherServlet?command=instShowContent&boardNo=${ivo.boardNo }">
												${ivo.title }
												<c:if test="${ivo.totalCommentCount != 0}">[${ivo.totalCommentCount }]</c:if>
												</a></td>
										<td align="center">${ivo.member.name }</td>
										<td align="center">${ivo.timePosted }</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					<div class="panel-footer" align="center">
						<a class="btn btn-default"
							href="DispatcherServlet?command=instList" role="button">게시판으로
							이동 &raquo;</a>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="container">
		<div class="row"></div>
	</div>
	<jsp:include page="/layout/footer.jsp" />
	<%}else{ %>
		<script type="text/javascript">
			alert("로그인하세요!");
			location.href="${pageContext.request.contextPath}/login.jsp";
		</script>
<%} %>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="//code.jquery.com/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#logoutBtn").click(function() {
			return confirm("정말 로그아웃 하시겠습니까?");
		});	// click
		$("#updateBtn").click(function() {
			return confirm("회원 정보를 수정 하시겠습니까?");
		});	// click
		$("#selectingPresenter").click(function() {
			if ("${sessionScope.mvo.memberType}" != "강사") {
				alert("강사만 발표자 선정을 할 수 있습니다.");
				return false;
			}
		});
		$("#selectingGroup").click(function() {
			if ("${sessionScope.mvo.memberType}" != "강사") {
				alert("강사만 발표자 선정을 할 수 있습니다.");
				return false;
			}
		});
	});	// ready
</script>
<nav class="navbar navbar-inverse">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#myNavbar">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="${pageContext.request.contextPath}/DispatcherServlet?command=mainList">KOMS. v.145</a>
		</div>
		<div class="collapse navbar-collapse" id="myNavbar">
			<ul class="nav navbar-nav">
				<li><a href="${pageContext.request.contextPath}/DispatcherServlet?command=mainList">Home</a></li>
				<li><a href="${pageContext.request.contextPath}/sel_group.jsp" id="selectingGroup">조 선정</a></li>
				<li><a href="${pageContext.request.contextPath}/sel_presenter.jsp" id="selectingPresenter">발표자 선정</a></li>
				<li class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">게시판 <span class="caret"></span></a>
					<ul class="dropdown-menu" role="menu">
						<li><a href="${pageContext.request.contextPath}/DispatcherServlet?command=proList">건의사항 게시판</a></li>
						<li><a href="${pageContext.request.contextPath}/DispatcherServlet?command=qnaboardlist">QnA 게시판</a></li>
						<li><a href="${pageContext.request.contextPath}/DispatcherServlet?command=instList">강사 게시판</a></li>
					</ul>
				</li>
			</ul>
			
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><span class="glyphicon glyphicon-user"></span>${sessionScope.mvo.name}<span class="caret"></span></a>
					<ul class="dropdown-menu" role="menu">
						<li><a href="DispatcherServlet?command=updateView" id="updateBtn">회원정보수정<span class="glyphicons glyphicons-log-out"></span></a></li>
						<li><a href="DispatcherServlet?command=logout" id="logoutBtn">로그아웃<span class="glyphicons glyphicons-log-out"></span></a></li>
					</ul>
				</li>
			</ul>
		</div>
	</div>
</nav>
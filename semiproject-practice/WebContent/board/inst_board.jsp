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
<script type="text/javascript">
	$(document).ready(function() {
		$("#searchBtn").click(function() {
			var searchTxt = document.getElementById("searchTxt").value;
			if (searchTxt == "") {
				alert("검색어를 입력하세요.");
				return;
			} else {
				var type = document.getElementById("search").value;
				location.href = "${pageContext.request.contextPath}/DispatcherServlet?command=instSearch&type=" + type + "&searchTxt=" + searchTxt;
			}
		});
	});
</script>
</head>
<body>
	<jsp:include page="/layout/header.jsp" />
	<div class="container">
		<div class="row">
			<div class="col-sm-2"></div>
			<div class="col-sm-8" align="center">
				<div class="panel panel-warning">
					<div class="panel-heading" align="center">강사 게시판</div>
					<div class="panel-body">
						<table class="table table-hover">
							<thead>
								<tr>
									<th>글번호</th>
									<th width="50%">제 목</th>
									<th>작성자</th>
									<th>작성일</th>
									<th>조회수</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="bvo" items="${requestScope.lvo.list}">
									<tr>
										<td  align="center">${bvo.boardNo }</td>
										<td  align="center">
											<a href="${pageContext.request.contextPath}/DispatcherServlet?command=instShowContent&boardNo=${bvo.boardNo }">
											 ${bvo.title } 
											 <c:if test="${bvo.totalCommentCount != 0}">[${bvo.totalCommentCount }]</c:if></a>
											</td>
										<td  align="center">${bvo.member.name }</td>
										<td  align="center">${bvo.timePosted }</td>
										<td  align="center">${bvo.hits }</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					<p class="paging">
						<%-- 코드를 줄이기 위해 pb 변수에 pagingBean을 담는다. --%>
						<c:set var="pb" value="${requestScope.lvo.pagingBean}"></c:set>
						<!-- 
			step2 1) 이전 페이지 그룹이 있으면 이미지 보여준다. (img/left_arrow_btn.gif)
				   		페이징빈의 previousPageGroup 이용 
				   2)  이미지에 이전 그룹의 마지막 페이지번호를 링크한다. 
				   	    hint)   startPageOfPageGroup-1 하면 됨 		 
	 -->
						<c:if test="${pb.previousPageGroup}">
							<a href="DispatcherServlet?command=instList&pageNo=${pb.startPageOfPageGroup-1}">
								<!-- <img src="img/left_arrow_btn.gif"> --> ◀&nbsp;
							</a>
						</c:if>
						<!-- step1. 1)현 페이지 그룹의 startPage부터 endPage까지 forEach 를 이용해 출력한다
				   2) 현 페이지가 아니면 링크를 걸어서 서버에 요청할 수 있도록 한다.
				      현 페이지이면 링크를 처리하지 않는다.  
				      PagingBean의 nowPage
				      jstl choose 를 이용  
				      예) <a href="DispatcherServlet?command=list&pageNo=...">				   
	 -->
						<c:forEach var="i" begin="${pb.startPageOfPageGroup}" end="${pb.endPageOfPageGroup}">
							<c:choose>
								<c:when test="${pb.nowPage!=i}">
									<a href="DispatcherServlet?command=instList&pageNo=${i}">${i}</a>
								</c:when>
								<c:otherwise>
								${i}
								</c:otherwise>
							</c:choose>
							&nbsp;
						</c:forEach>
						<!-- 
			step3 1) 다음 페이지 그룹이 있으면 이미지(img/right_arrow_btn.gif) 보여준다. 
				   		페이징빈의 nextPageGroup 이용 
				   2)  이미지에 이전 그룹의 마지막 페이지번호를 링크한다. 
				   	    hint)   endPageOfPageGroup+1 하면 됨 		 
	 -->
						<c:if test="${pb.nextPageGroup}">
							<a href="DispatcherServlet?command=instList&pageNo=${pb.endPageOfPageGroup+1}">
								▶<!-- <img src="img/right_arrow_btn.gif"> -->
							</a>
						</c:if>
					</p>
					<!-- 강사인 경우에만 쓸 수 있게 -->
					<c:if test="${sessionScope.mvo.memberType == '강사' }">
						<div align="right">
							<a href="${pageContext.request.contextPath}/board/inst_write_board.jsp"><img src="${pageContext.request.contextPath}/img/write_btn.jpg" border="0"></a>
							<c:forEach begin="0" end="5">&nbsp;</c:forEach>
						</div><br>
					</c:if>
					<div class="panel-footer" align="center">
						<select id="search">
							<option value="title">제목</option>
							<option value="titleAndContent">제목+내용</option>
							<option value="writer">작성자</option>
						</select>
						<input type="text" id="searchTxt" placeholder="Search" size="30">
						<button type="button" id="searchBtn" class="btn btn-default">검색</button>
					</div>
				</div>
			</div>
			<div class="col-sm-2"></div>
		</div>
	</div>
	<div class="container">
		<div class="row"></div>
	</div>
	<jsp:include page="/layout/footer.jsp" />
</body>
</html>
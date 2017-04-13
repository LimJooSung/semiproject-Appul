<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head> 
<title>발표자 선정</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/mystyle.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$.ajax({
			type:"get",
			url:"DispatcherServlet",
			dataType:"json",
			data:"command=selectingPresenter",
			success:function(data){
				//<td align="center">1</td><td align="center">강정호</td><td align="center">0</td>
				var values = [];
				var info1 = "";
				var info2 = "";
				var info3 = "";
				values = data.allMemberList;
				
				// 1-12번
				for (var i=0; i<12; i++) {
					
					info1+="<tr>";					
					info1+="<td>"+values[i].memNumber+"</td>";
					info1+="<td>"+values[i].memberVO.name+"</td>";
					info1+="<td>"+ values[i].cntPresentation+"</td>";
					info1+="</tr>";
				}
				$("#table1").html(info1);
				
				 // 13-24번
				for (var i=12; i<24; i++) {
					info2+="<tr>";					
					info2+="<td>"+values[i].memNumber+"</td>";
					info2+="<td>"+values[i].memberVO.name+"</td>";
					info2+="<td>"+ values[i].cntPresentation+"</td>";
					info2+="</tr>";
				}
				$("#table2").html(info2);
				
				// 25-36번
				for (var i=24; i<35; i++) {
					info3+="<tr>";					
					info3+="<td>"+values[i].memNumber+"</td>";
					info3+="<td>"+values[i].memberVO.name+"</td>";
					info3+="<td>"+ values[i].cntPresentation+"</td>";
					info3+="</tr>";
				}
				$("#table3").html(info3);
			}//success DispatcherServlet?command=""
		});//ajax	
	});
</script>
</head>
<body>
	
	<jsp:include page="/layout/header.jsp" />
	<div class="container">
		<h4 align="center">KOSTA 145기 발표 현황</h4>${sessionScope.mvo.memberType}
		<br>
		<div class="row">
			<div class="col-sm-4">
				<div class="panel panel-info">
					<div class="panel-heading" align="center">1 - 12</div>
					<div class="panel-body">
						<table class="table table-condensed">
							<thead>
								<tr>
									<th>번호</th>
									<th>이름</th>
									<th>총 발표 횟수</th>
								</tr>
							</thead>
							<tbody id="table1" align="center">

							</tbody>
						</table>
					</div>
				</div>
			</div>
			<div class="col-sm-4">
				<div class="panel panel-info">
					<div class="panel-heading" align="center">13-24</div>
					<div class="panel-body">
						<table class="table table-condensed">
							<thead>
								<tr>
									<th>번호</th>
									<th>이름</th>
									<th>총 발표 횟수</th>
								</tr>
							</thead>
							<tbody id="table2" align="center">

							</tbody>
						</table>
					</div>
				</div>
			</div>
			<div class="col-sm-4">
				<div class="panel panel-info">
					<div class="panel-heading" align="center">25-36</div>
					<div class="panel-body">
						<table class="table table-condensed">
							<thead>
								<tr>
									<th>번호</th>
									<th>이름</th>
									<th>총 발표 횟수</th>
								</tr>
							</thead>
							<tbody id="table3" align="center">

							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
		<div class="panel-footer" align="center">
			<input type="button" id="selectingPresenterBtn" value="발표자 선정하기">
			<script type="text/javascript">
				$(document).ready(function() {
					$("#selectingPresenterBtn").click(function() {
						$.ajax({
							type : "get",
							url : "DispatcherServlet",
							dataType : "json",
							data : "command=selectingPresenter",
							success : function(result) {
							if (confirm("발표자 : " + result.name) == true) {
								$.ajax({
									type : "post",
									url : "DispatcherServlet",
									data : "command=updateCntPresentation&id="
											+ result.id
											+ "&cntPresentation="
											+ result.cntPresentation,
									success : function() {
										location.reload();
										return; 
									}
								});//ajax
							} else {
								return;
							}
						}
					});//ajax
				});//click
			});//ready
			</script>
		</div>
	</div>
	<div class="container">
		<div class="row"></div>
	</div>
	<jsp:include page="/layout/footer.jsp" />
</body>
</html>
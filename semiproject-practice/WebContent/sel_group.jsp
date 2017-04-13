<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>발표자 선정</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/mystyle.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery.min.js"></script>
</head>

<body>
	<jsp:include page="/layout/header.jsp" />
	
	<c:set value = "${requestScope.memberList.name}" var="memberName"/>
	
	<div class="container">
		<h4 align="center">KOSTA 145기 조선정</h4>
		<br>	
	</div>
	<div class="row" align="center">
		<input type="button" id="selectingGroupBtn" value="조선정 시작">
		<br>	
	</div>	
	
	<div class="container">
		<div class="row">
			<div class="col-sm-4">
				<div class="panel panel-warning">
					<div class="panel-heading" align="center">1조</div>
					<div class="panel-body">
						<table class="table table-condensed">
							<tbody id="table1" align="center">
							
							</tbody>
						</table>
					</div>
				</div>
			</div>
			<div class="col-sm-4">
				<div class="panel panel-warning">
					<div class="panel-heading" align="center">2조</div>
					<div class="panel-body">
						<table class="table table-condensed">
							<tbody id="table2" align="center">
							
							</tbody>
						</table>
					</div>
				</div>
			</div>
			<div class="col-sm-4">
				<div class="panel panel-warning">
					<div class="panel-heading" align="center">3조</div>
					<div class="panel-body">
						<table class="table table-condensed">
							<tbody id="table3" align="center">
						
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-4">
				<div class="panel panel-warning">
					<div class="panel-heading" align="center">4조</div>
					<div class="panel-body">
						<table class="table table-condensed">
							<tbody id="table4" align="center">
							
							</tbody>
						</table>
					</div>
				</div>
			</div>
			<div class="col-sm-4">
				<div class="panel panel-warning">
					<div class="panel-heading" align="center">5조</div>
					<div class="panel-body">
						<table class="table table-condensed">
							<tbody id="table5" align="center">

							</tbody>
						</table>
					</div>
				</div>
			</div>
			<div class="col-sm-4">
				<div class="panel panel-warning">
					<div class="panel-heading" align="center">6조</div>
					<div class="panel-body">
						<table class="table table-condensed">
							<tbody id="table6" align="center">
							
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="container">
		<div class="row"></div>
	</div>
	<div class="row" align="center">
		
		<script type="text/javascript">
				$(document).ready(function() {
					$("#selectingGroupBtn").click(function() {
						$.ajax({
							type : "get",
							url : "DispatcherServlet",
							dataType : "json",
							data : "command=selectingGroup",
							success : function(data) {
								
								var values1 = [];
								var values2 = [];
								var values3 = [];
								var values4 = [];
								var values5 = [];
								var values6 = [];
								
								values1 = data.no1;
								values2 = data.no2;
								values3 = data.no3;
								values4 = data.no4;
								values5 = data.no5;
								values6 = data.no6;
								
								
								var info1 = "";
								var info2 = "";
								var info3 = "";
								var info4 = "";
								var info5 = "";
								var info6 = "";
								
								for (var i = 0; i < 6; i ++)
								{
									info1+="<tr>";					
										info1+="<td>"+values1[i]+"</td>";
									info1+="</tr>";
								}
								$("#table1").html(info1);
								
								for (var i = 0; i < 6; i ++)
								{
									info2+="<tr>";					
										info2+="<td>"+values2[i]+"</td>";
									info2+="</tr>";
								}
								$("#table2").html(info2);
						
								
								for (var i = 0; i < 6; i ++)
								{
									info3+="<tr>";					
										info3+="<td>"+values3[i]+"</td>";
									info3+="</tr>";
								}
								$("#table3").html(info3);
						
								
								for (var i = 0; i < 6; i ++)
								{
									info4+="<tr>";					
										info4+="<td>"+values4[i]+"</td>";
									info4+="</tr>";
								}
								$("#table4").html(info4);
						
								
								for (var i = 0; i < 6; i ++)
								{
									info5+="<tr>";					
										info5+="<td>"+values5[i]+"</td>";
									info5+="</tr>";
								}
								$("#table5").html(info5);
						
								
								for (var i = 0; i < 6; i ++)
								{
									info6+="<tr>";					
										info6+="<td>"+values6[i]+"</td>";
									info6+="</tr>";
								}
								$("#table6").html(info6);
						
								
						
								
								
							/* if (confirm("발표자 : " + result.name) == true) {
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
							}  */
						}
					});//ajax
				});//click
			});//ready
			</script>
	</div>
	<jsp:include page="/layout/footer.jsp" />
</body>
</html>
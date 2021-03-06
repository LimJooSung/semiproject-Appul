<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/mystyle.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<meta charset="UTF-8">
<script src="//code.jquery.com/jquery.min.js"></script>
<title>show content</title>
<script type="text/javascript">
	$(document).ready(function() {
		$("#deleteBtn").click(function() {
			return confirm("정말 삭제 하시겠습니까?");
		});	// click
		$("#fileCheck").click(function() {
			if (confirm("다운로드 하시겠습니까?")) {
				$("#fileCheck").submit();
			} else {
				return;
			}
		}); 
	});	// ready

    var httpRequest = null;
    // httpRequest 객체 생성
    function getXMLHttpRequest(){
        var httpRequest = null;
        if(window.ActiveXObject){
            try{
                httpRequest = new ActiveXObject("Msxml2.XMLHTTP");    
            } catch(e) {
                try{
                    httpRequest = new ActiveXObject("Microsoft.XMLHTTP");
                } catch (e2) { httpRequest = null; }
            }
        }
        else if(window.XMLHttpRequest){
            httpRequest = new window.XMLHttpRequest();
        }
        return httpRequest;    
    }
    
    // 댓글 등록
    function writeCmt() {
        var form = document.getElementById("writeCommentForm");
        var board = form.comment_board.value
        var id = form.comment_id.value
        var content = form.comment_content.value;
        if(!content) {
            alert("내용을 입력하세요.");
            return false;
        } else  {    
            var param="comment_board="+board+"&comment_id="+id+"&comment_content="+content;
            httpRequest = getXMLHttpRequest();
            httpRequest.onreadystatechange = checkFunc;
            httpRequest.open("POST", "DispatcherServlet?command=proCommentWrite", true);    
            httpRequest.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8'); 
            httpRequest.send(param);
        }
    }
    
    //댓글 삭제창
    function cmDeleteOpen(commentNo){
    	var msg = confirm("댓글을 삭제할까요?");
    	if(msg==true){//확인을 누를경우
			deleteCmt(commentNo);    		
    	}else{
    		return false;
    	}
    }
    function cmUpdateOpen(commentNo){
    	var msg = confirm("댓글을 수정할까요?");
    	if(msg==true){//확인을 누를경우
    		cmUpdate(commentNo);    		
    	}else{
    		return false;
    	}
    }
    function cmUpdate(commentNo){
    	window.name="parentForm";
     	window.open("${pageContext.request.contextPath}/DispatcherServlet?command=proCommentUpdateView&commentNo="+commentNo,
    			"updateForm", "width=570, height=350, resize=no, scrollbars = no"); 
     }
    
     //댓글 삭제
     function deleteCmt(commentNo){
    	var param = "commentNo=" +commentNo;//아이디 붙여얗는데 아직 안붙임;
    	httpRequest = getXMLHttpRequest();
    	httpRequest.onreadystatechange = checkFunc;
    	httpRequest.open("POST", "DispatcherServlet?command=proCommentDelete", true);    
        httpRequest.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8'); 
        httpRequest.send(param);
    }  
     
    function checkFunc(){
        if(httpRequest.readyState == 4){
            // 결과값을 가져온다.
            var resultText = httpRequest.responseText;
            	{  document.location.reload(); // 상세보기 창 새로고침
            }
        }
    }	
</script>
</head>
<body>
	<c:import url="/layout/header.jsp"></c:import>
	<div class="container">
		<div class="row">
			<div class="col-sm-2"></div>
			<div class="col-sm-8" align="center">
				<div class="panel panel-success">
					<div class="panel-heading" align="center">건의사항 게시글 상세보기</div>
					<div class="panel-body">
						<table class="table">
							<tbody>
								<tr>
								<td>글번호 |
										&nbsp;${requestScope.bvo.boardNo }</td>
									<td>제&nbsp;&nbsp;&nbsp;목 | &nbsp;${requestScope.bvo.title}
									</td>
									<td>조회수 | &nbsp;${requestScope.bvo.hits }</td>
								</tr>
								<tr>
									<td>작성자 | &nbsp;${requestScope.bvo.member.name }</td>
									<td>작성일 | &nbsp;${requestScope.bvo.timePosted }</td>
									<td>
										<form
											action="${pageContext.request.contextPath}/DispatcherServlet"
											id="fileCheck" name="">
											<input type="hidden" name="command" value="proDownload">
											<input type="hidden" name="boardNo" value="${requestScope.bvo.boardNo }">
											<c:if test="${requestScope.bvo.attachedFile != null}">
											파&nbsp;&nbsp;&nbsp;일 |  <a href="#">&nbsp;${requestScope.bvo.attachedFile }</a>
											</c:if>
										</form>
									</td>
								</tr>
								<tr>
									<td colspan="3"><pre>${requestScope.bvo.content}</pre></td>
								</tr>
							</tbody>
						</table>
						<div align="right">
							<c:if test="${sessionScope.mvo.id == requestScope.bvo.member.id }">
								<a
									href="${pageContext.request.contextPath}/DispatcherServlet?command=proUpdateView&boardNo=${requestScope.bvo.boardNo }">
									<img src="${pageContext.request.contextPath}/img/modify_btn.jpg"
									border="0">
								</a>
								<a
									href="${pageContext.request.contextPath}/DispatcherServlet?command=proDeletePosting&boardNo=${requestScope.bvo.boardNo }"
									id="deleteBtn"> <img
									src="${pageContext.request.contextPath}/img/delete_btn.jpg"
									border="0"></a>
							</c:if>
							<a
								href="${pageContext.request.contextPath}/DispatcherServlet?command=proList">
								<img src="${pageContext.request.contextPath}/img/list_btn.jpg"
								border="0">
							</a>
						</div>
					</div>

					<div class="comment-footer" align="left">
						<c:if test="${requestScope.cvo != null}">
							<hr>
							<c:forEach var="comment" items="${requestScope.cvo}">
								<tr>
									<!--  아이디, 작성날짜 -->
									<td width="150">
										<div>
											<c:forEach begin="0" end="2">&nbsp;</c:forEach>
											[${comment.member.name}]<font size="2" color="lightgray"> ${comment.timePosted}</font>
										</div>
									</td>
									<!--  본문내용 -->
									<td width="550">
										<div class="text_wrapper">
											<c:forEach begin="0" end="2">&nbsp;</c:forEach>
											${comment.content}
											<c:if test="${comment.member.id == sessionScope.mvo.id }">
												<div align="right"> 
													<a href="#" onclick="cmUpdateOpen(${comment.commentNo})">[수정]</a>
													<a href="#" onclick="cmDeleteOpen(${comment.commentNo})">[삭제]</a>
													<c:forEach begin="0" end="2">&nbsp;</c:forEach>
												</div>
											</c:if>
										</div>
									</td>
								</tr>
								<hr>
							</c:forEach>
						</c:if>
					</div>
					<br>

					<!-- 로그인 했을 경우만 댓글 작성가능 -->
					<c:if test="${sessionScope.mvo.id !=null }">
						<form id="writeCommentForm">
							<input type="hidden" name="comment_board" value="${requestScope.bvo.boardNo}"> 
							<input type="hidden" name="comment_id" value="${sessionScope.mvo.id}">

							<div class="panel-footer">
							<!-- 본문 작성 -->
							<tr>
								<td width="550">
									<div>
										<textarea name="comment_content" rows="2" cols="80"></textarea>
									</div>
								</td>
							<tr>
								<!-- 댓글 등록 버튼 -->
								<td width="100">
									<div id="btn" style="text-align: center;">
										<!-- <a href="#" onclick="writeCmt()">[댓글등록]</a> -->
										<input type="button" value="댓글등록" onclick="writeCmt()">
									</div>
								</td>
							</div>
						</form>
					</c:if>
				</div>
			</div>
			<div class="col-sm-2"></div>
		</div>
	</div>
	<div class="container">
		<div class="row"></div>
	</div>
	<c:import url="/layout/footer.jsp"></c:import>
</body>
</html>
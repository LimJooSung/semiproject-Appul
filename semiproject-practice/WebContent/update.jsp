<%@page import="model.member.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="model.member.MemberVO"%>
<!DOCTYPE html>
<html lang="en">
  <head >
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../favicon.ico">

    <title>Signup Template for Bootstrap</title>
    
<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
 <script type="text/javascript">
 $(function(){
  $('#password').keyup(function(){
   $('font[id=check]').text('');
  }); //#user_pass.keyup

  $('#chpass').keyup(function(){
   if($('#password').val()!=$('#chpass').val()){
    $('font[id=check]').text('');
    $('font[id=check]').html("Password incorrect!");
    return false;
   }else{
    $('font[id=check]').text('');
    $('font[id=check]').html("Password correct");
   }
   return true;
  }); //#chpass.keyup
 });

/* 
 <script type="text/javascript">
 function checkPassword(password){

	 var pw = password;
	 var num = pw.search(/[0-9]/g);
	 var eng = pw.search(/[a-z]/ig);
	 var spe = pw.search(/[`~!@@#$%^&*|₩₩₩'₩";:₩/?]/gi);

	 if(pw.length < 10 || pw.length > 20){
	  alert("10자리 ~ 20자리 이내로 입력해주세요.");
	  return false;
	 }

	 if(pw.search(/₩s/) != -1){
	  alert("비밀번호는 공백업이 입력해주세요.");
	  return false;
	 }

	 if( (num < 0 && eng < 0) || (eng < 0 && spe < 0) || (spe < 0 && num < 0) ){
	  alert("영문,숫자, 특수문자 중 2가지 이상을 혼합하여 입력해주세요.");
	  return false;
	 }

	 return true;
	}

	if(!checkPassword( $.trim($('#password').val()))){
	   $('#password').val('');
	   $('#password').focus();
	   return false;
	}
 */
 

 $(document).ready(function(){      
     $("#id").keyup(function(){
        var id= $(this).val();

        if(id.length == 0){
           $("#checkResult").text('');
        }
        else if(id.length < 4 || id.length > 10){
           $("#checkResult").html("4자이상 10자이하만 가능합니다");
        }else{
           $.ajax({
              type:"post",
              url:"${pageContext.request.contextPath}/DispatcherServlet?command=idcheck&id=" + id,
              data:$("#formUpdate").serialize(),
              success:function(data){
            	 
           
                  if(data=="ok"){
                    $("#checkResult").html("사용이 가능한 아이디입니다");   
                 }else{
                    $("#checkResult").html("중복된 아이디입니다 사용불가");   
                 } 
              } 
           });
        }
     });
 });
 
 function checkForm(){
	 var f=document.formUpdate;

	if(f.password.value != f.chpass.value){
		alert("비밀번호를 동일하게 입력하세요.");
		return false;
	}	 	
    if(isNaN(f.dateOfBirth.value)){
		alert("생년월일은 숫자만 입력가능합니다.");
		return false;
	}   
    if(! f.gender.value){
		alert("성별을 선택하세요.");
		return false;
	}
    if(! f.memberType.value){
		alert("전공을 선택하세요.");
		return false;
	}
    if (f.password.value.length < 4 || f.password.value.length > 10){
        alert("4자이상 10자이하만 가능합니다.");
        return false;
    }
    if (f.id.value.indexOf(" ")>=0){
        alert("아이디에 공백을 사용할 수 없습니다.");
        return false;
    }
    if (f.password.value.indexOf(" ")>=0){
        alert("비밀번호에 공백을 사용할 수 없습니다.");
        return false;
    }
    if (f.name.value.indexOf(" ")>=0){
        alert("이름에 공백을 사용할 수 없습니다.");
        return false;
    }
    if (f.dateOfBirth.value.indexOf(" ")>=0){
        alert("생년월일에 공백을 사용할 수 없습니다.");
        return false;
    }
 } 
 
 function checkQuit() {
	    window.open("member/game.jsp", "_blank", "toolbar=yes,resizable=yes,top=300,left=300,width=300,height=200");
	}
 
     </script>


<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

    <!-- Bootstrap core CSS -->
    <link href="../../dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="signin.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="../../assets/js/ie-emulation-modes-warning.js"></script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    
<style type="text/css">
div.container {
	text-align: center;
    align-items: center;
    justify-content: center;
	width: 500px; 
	height: 500px;
}
table.type02 {
    border-collapse: separate;
    border-spacing: 0;
    text-align: left;
    line-height: 1.5;
    border-top: 1px solid #ccc;
    border-left: 1px solid #ccc;
  margin : 20px 10px;
}
table.type02 th {
    width: 150px;
    padding: 10px;
    font-weight: bold;
    vertical-align: top;
    border-right: 1px solid #ccc;
    border-bottom: 1px solid #ccc;
    border-top: 1px solid #fff;
    border-left: 1px solid #fff;
    background: #eee;
}
table.type02 td {
    width: 350px;
    padding: 10px;
    vertical-align: top;
    border-right: 1px solid #ccc;
    border-bottom: 1px solid #ccc;
}
table.type03 {
    text-align: center;
    align-items: center;
    justify-content: center;
	width: 550px; 
	height: 50px;
}
</style>

  </head>

  <body style="font-weight:bold;">
<% MemberVO vo=(MemberVO)session.getAttribute("mvo"); 
	System.out.println(vo);
	if(vo!=null){
%>
    <div class="container">
	
	<form name="formUpdate" id="formUpdate" class="formUpdate" method="post" action="${pageContext.request.contextPath}/DispatcherServlet" onsubmit="return checkForm()">
	<!--  <font size="5" color="#F6358A">KOMS</font>-->
	<input type="hidden" name="command" value="update">
    <h2 class="form-signup-heading">회원 정보 수정</h2>

    <table class="type02">
        <tr>
            <th scope="row"> ID</th>
            <td width="450">
                
                    <input type="text" name="id" id="id" class="form-control" required="required" onkeyup="startAjax()" value="<%=vo.getId() %>" readonly>
                    <span id="checkResult"></span>
 
                    <input type="hidden" name="command" value="idcheck">	
                          
            </td>
        </tr>
       
        <tr>
            <th scope="row"> Password</th>
            <td>
               
                    <font> Password1</font>
                    <input type="password" name="password" id="password" class="form-control" required="required" value="<%=vo.getPassword() %>">
                    <br />
                    <font> Password2</font>
                    <input type="password" name="chpass" id="chpass" class="form-control" required="required">
                    <font id="check" size="2" color="red"></font> 
                    
                    <br />
               
            </td>
        </tr>
               
        <tr>
            <th scope="row">Name</th>
            <td>
                <input type="text" name="name" id="name" class="form-control" required="required" value="<%=vo.getName() %>">
            </td>
        </tr>
       
        <tr>
            <th scope="row">Date of birth</th>
            <td>
                   <input type="text" name="dateOfBirth" id="dateOfBirth" class="form-control" required="required" value="<%=vo.getDateOfBirth() %>"> ex)1999year Sep 9th = 19990909
            </td> 
        </tr>
       


        <tr>
            <th scope="row">Gender</th>
            <td>
            <input type="text"  class="form-control"  value="<%=vo.getGender() %>" readonly>           
                <input type="radio" name="gender" id="gender" value="Female" > Female
                <input type="radio" name="gender" id="gender" value="Male"> Male
            </td>
        </tr>

        <tr>
            <th scope="row">Types of students</th>
            <td>
            <input type="text"  class="form-control"  value="<%=vo.getMemberType() %>" readonly>
                 <input type="radio" name="memberType" id="memberType" value="IT student"> IT student
                <input type="radio" name="memberType" id="memberType" value="None IT student"> None IT student
            </td>
        </tr>
   
    </table>
    
 <!-- location.href="${pageContext.request.contextPath}/DispatcherServlet?command=mainList"; -->
    
    <table class="type03">
       <tr><td> <input type="submit"  class="btn btn-lg btn-primary btn-block" style="width: 100px; height: 50px;" value="Update"> </td>
         <!--  <td><input type="reset"  name="reset" id="reset" class="btn btn-lg btn-primary btn-block"  style="width: 100px; height: 50px;" value="Reset" onClick="javascript:document.formUpdate.reset()"> </td>-->
        <td> <input type="button"  class="btn btn-lg btn-primary btn-block" style="width: 100px; height: 50px;" onClick="checkQuit()" value="탈퇴"> </td>
        <td><a href="${pageContext.request.contextPath}/DispatcherServlet?command=mainList" type="button"  class="btn btn-lg btn-primary btn-block"  onClick="alert('Main page')" style="width: 100px; height: 50px;">Cancel</a> </td></tr>
    </table>
    

 </form>
</div> <!-- /container -->
<%}else{ %>
		<script type="text/javascript">
			alert("로그인하세요!");
			location.href="${pageContext.request.contextPath}/login.jsp";
		</script>
<%} %>
    


    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>
  </body>
</html>
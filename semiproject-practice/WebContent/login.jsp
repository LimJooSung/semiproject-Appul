<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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

    <title>Signin Template for Bootstrap</title>

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
</style>

<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
 <script type="text/javascript">
    function checkFindId() {
	    window.open("member/findId.jsp", "_blank", "toolbar=yes,resizable=yes,top=500,left=500,width=500,height=250");
	}
    
    function checkLogin() { 
  	  var frm = document.formSignin;
  	  if (frm.id.value.length < 1) {
  	   alert("ID 입력해주세요");
  	   return false;
  	  }
  	  if (frm.password.value.length < 1) {
  		   alert("PASSWORD 입력해주세요");
  		   return false;
  	  }
    }
 </script>

  </head>

  <body style="font-weight:bold;">
			
    <div class="container">
	
      <form class="formSignin" method="post" action="${pageContext.request.contextPath}/DispatcherServlet">
      <input type="hidden" name="command" value="login">
      	<font size="5" color="#F6358A">KOMS</font>
        <h2 class="form-signin-heading">Please Sign In</h2>
        <label for="inputId" class="sr-only" >ID</label>
        <input type="text" id="id" class="form-control" placeholder="ID" required autofocus
        style="width: 500px; height: 50px;" name="id" required="required">
        <label for="inputPassword" class="sr-only" >Password</label>
        <input type="password" id="password" class="form-control" placeholder="Password" required 
        style="width: 500px; height: 50px;" name="password" required="required">
        
        <!--  <div class="checkbox">
         <center> <label>
            <input type="checkbox" value="remember-me"> Remember me           
          </label></center>
        </div> -->
        <input type="hidden" name="command" value="login">
        <input type="submit" class="btn btn-lg btn-primary btn-block" 
        style="width: 500px; height: 50px;" value="Sign In" onClick="return checkLogin()">
        
        <a href="register.jsp" class="btn btn-lg btn-primary btn-block" type="button"
        style="width: 500px; height: 50px;" >Sign Up</a>
        <a onClick="checkFindId()">Forgot ID?</a>
        
        
      </form>

    </div> <!-- /container -->


    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>
  </body>
</html>
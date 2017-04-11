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

    <title>Signup Template for Bootstrap</title>
    
    <script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<script>
 $(function(){
  $('#password').keyup(function(){
   $('font[name=check]').text('');
  }); //#user_pass.keyup

  $('#chpass').keyup(function(){
   if($('#password').val()!=$('#chpass').val()){
    $('font[name=check]').text('');
    $('font[name=check]').html("Password incorrect");
   }else{
    $('font[name=check]').text('');
    $('font[name=check]').html("Password correct");
   }
  }); //#chpass.keyup
 });
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

    <div class="container">

	<form class="form-signup" method="post" action="${pageContext.request.contextPath}/DispatcherServlet">
	<input type="hidden" name="command" value="register">
    <h2 class="form-signup-heading">Please Sign Up</h2>

    <table class="type02">
        <tr>
            <th scope="row"> ID</th>
            <td width="450">
                
                    <input type="text" name="id" id="id" class="form-control" autofocus required></input>
                    <input type="submit" value="Checking for duplicate ID"> 
                          
            </td>
        </tr>
       
        <tr>
            <th scope="row"> Password</th>
            <td>
               
                    <font> Password1</font>
                    <input type="password" name="password" id="password" class="form-control"></input>
                    <br />
                    <font> Password2</font>
                    <input type="password" name="chpass" id="chpass" class="form-control"></input>
                    <font name="check" size="2" color="red"></font> 
                    
                    <br />
               
            </td>
        </tr>
               
        <tr>
            <th scope="row">Name</th>
            <td>
                <input type="text" name="name" id="name" class="form-control"></input>
            </td>
        </tr>
       
        <tr>
            <th scope="row">Date of birth</th>
            <td>
                   <input type="text" name="dateOfBirth" id="dateOfBirth" value="ex)1999year Sep 9th == 19990909" class="form-control"></input>
            </td> 
        </tr>
       


        <tr>
            <th scope="row">Gender</th>
            <td>
                <input type="radio" name="gender" id="gender"/> Female
                <input type="radio" name="gender" id="gender"/> Male
            </td>
        </tr>

        <tr>
            <th scope="row">Types of students</th>
            <td>
                <input type="radio" name="memberType" id="memberType"/> IT student
                <input type="radio" name="memberType" id="memberType"/> None IT student
            </td>
        </tr>
   
    </table>
    
 
    
    <table class="type03">
       <tr><td> <a href="javascript:form-signup.submit()" type="submit"  class="btn btn-lg btn-primary btn-block" style="width: 100px; height: 50px;">Submit</a> </td>
        <td><a href="javascript:form-signup.reset()" type="reset"  class="btn btn-lg btn-primary btn-block"  style="width: 100px; height: 50px;">Reset</a></td>
        <td><a href="login.jsp" type="button"  class="btn btn-lg btn-primary btn-block"  onClick="alert('Main page')" style="width: 100px; height: 50px;">Cancel</a> </td></tr>
    </table>
    

 </form>
</div> <!-- /container -->

    


    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>
  </body>
</html>
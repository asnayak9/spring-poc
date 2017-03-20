<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">
	var contextPath = '<%=request.getContextPath()%>';
</script>	
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login Page</title>
<spring:url value="/resources/css/bootstrap-min.css" var="bootstrapMin"></spring:url>
<link href="${bootstrapMin}" rel="stylesheet">
<spring:url value="/resources/css/login.css" var="login"></spring:url>
<link href="${login}" rel="stylesheet">
</head>
<body>
<span class="glyphicon glyphicon-heart"></span>

<div class="container">
	<div id="login-box">
		<div class="logo">
			<img src="http://lorempixel.com/output/people-q-c-100-100-1.jpg" class="img img-responsive img-circle center-block"/>
			<h1 class="logo-caption"><span class="tweak">L</span>ogin</h1>
		</div><!-- /.logo -->
		<form:form action="${pageContext.request.contextPath}/get-home" modelAttribute="userForm"  method="POST">
			<div class="controls">
				<input type="text" name="userName" placeholder="Username" class="form-control" />
				 <br/>
				<input type="password" name="password" placeholder="Password" class="form-control" />
				<button type="submit" class="btn btn-default btn-block btn-custom">Login</button>
			</div><!-- /.controls -->
		</form:form>
		
	</div><!-- /#login-box -->
</div><!-- /.container -->
<div id="particles-js"></div>
<spring:url value="/resources/js/jquery.js" var="jquery"></spring:url>
<script type="text/javascript" src="${jquery}"></script>
<spring:url value="/resources/js/bootstrap.js" var="bootstrapJs"></spring:url>
<script type="text/javascript" src="${bootstrapJs}"></script>
<spring:url value="/resources/js/login.js" var="loginJs"></spring:url>
<script type="text/javascript" src="${loginJs}"></script>
</body>
</html>
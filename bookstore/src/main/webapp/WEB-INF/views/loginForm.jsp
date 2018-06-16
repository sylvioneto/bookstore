<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Bookstore</title>
<!-- Bootstrap -->
<c:url value="/resources/css" var="cssPath" />
<link rel="stylesheet" href="${cssPath}/bootstrap.min.css" />
<link rel="stylesheet" href="${cssPath}/css/bootstrap-theme.min.css" />
<style type="text/css">
body {
	padding: 60px 0px;
}
</style>
</head>
<body>
    <div class="container">
	<h2>Login</h2>
	<form:form servletRelativeAction="/login" method="POST"> 	
		<div class="form-group">
			<label>E-mail</label>
			<input name="username" type="text" class="form-control"/>
		</div>
		<div class="form-group">
			<label>Password</label>
			<input name="password" type="password" class="form-control"/>
		</div>
		<button type="submit" class="btn btn-primary">Submit</button>
	</form:form>
	</div>
</body>
</html>
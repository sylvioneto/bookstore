<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Bookstore</title>
<c:url value="/resources/css" var="cssPath" />

<!-- Bootstrap -->
<link rel="stylesheet" href="${cssPath}/bootstrap.min.css" />
<link rel="stylesheet" href="${cssPath}/css/bootstrap-theme.min.css" />

<style type="text/css">
body {
	padding-top: 60px;
}
</style>
</head>
<body>
	<nav class="navbar navbar-inverse">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
				aria-expanded="false">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="${s:mvcUrl('HC#home').build()}">Bookstore</a>
		</div>
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li><a href="${s:mvcUrl('PC#getProducts').build()}">List of
						Products</a></li>
				<li><a href="${s:mvcUrl('PC#form').build()}">Product input</a></li>
				<li><a href="${s:mvcUrl('CC#items').build()}">Your cart
						${cart.quantity}</a></li>
			</ul>
		</div>
	</div>
	</nav>

	<div class="container">
		<h1>Bookstore home page</h1>
		<p>
		Description: This is a Bookstore application I created to study java and new frameworks.
		The user can create books, add them to the cart and checkout using external rest api.
		<br>
		Project built in Java, Spring MVC, Hibernate and Bootstrap.
		<br>
		Contact: sylvio.pedroza@gmail.com
		</p>
	</div>
</body>
</html>
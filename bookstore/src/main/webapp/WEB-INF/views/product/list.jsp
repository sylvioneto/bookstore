<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<html>
<head>
<c:url value="/" var="contextPath" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Bookstore - Product list</title>
<c:url value="/resources/css" var="cssPath" />

<!-- Bootstrap -->
<link rel="stylesheet" href="${cssPath}/bootstrap.min.css" />
<link rel="stylesheet" href="${cssPath}/css/bootstrap-theme.min.css" />

<style type="text/css">
body {
	padding: 60px 0px;
}
</style>
</head>
<body>
	<nav class="navbar navbar-inverse">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
				aria-expanded="false">
				<span class="sr-only">Toggle navigation</span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="${s:mvcUrl('HC#home').build()}">Bookstore</a>
		</div>
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li><a href="${s:mvcUrl('PC#getProducts').build()}">List of Products</a></li>
				<security:authorize access="isAuthenticated()">
					<li><a href="${s:mvcUrl('PC#form').build()}">Product input</a></li>
				</security:authorize>
				<li><a href="${s:mvcUrl('CC#items').build()}">Your cart ${cart.quantity}</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<security:authorize access="isAuthenticated()">
					 <li><a href="#"><security:authentication property="principal.username"/></a></li>
					 <li><a href="${contextPath}logout">Log Out</a></li>
				 </security:authorize>
				 <security:authorize access="!isAuthenticated()">
					 <li><a href="${contextPath}login">Log In</a></li>
				 </security:authorize>
			</ul>
		</div>
	</div>
	</nav>

	<div class="container">
		<h2>List of Products</h2>
		<p>${success}</p>
		<p>${fail}</p>
		<table class="table table-bordered table-hover">
			<thead class="thead-dark">
				<tr>
					<th>Title</th>
					<th>Description</th>
					<th>Pages</th>
					<th>Release Date</th>
					<th colspan="2">Prices</th>
				</tr>
			</thead>
			<c:forEach items="${products}" var="product">
				<tr>
					<td><a href="<c:url value="/product/detail/${product.id}"/>">${product.title}</a></td>
					<td>${product.description}</td>
					<td>${product.pages}</td>
					<td><fmt:formatDate pattern="dd/MM/yyyy"
							value="${product.releaseDt.time}" /></td>
					<c:forEach items="${product.prices}" var="price">
						<td>${price.pType}=${price.amount}</td>
					</c:forEach>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>
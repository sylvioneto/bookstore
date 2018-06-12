<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Bookstore - Product maintenance</title>
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
				<li><a href="${s:mvcUrl('PC#form').build()}">Product input</a></li>
				<li><a href="${s:mvcUrl('CC#items').build()}">Your cart ${cart.quantity}</a></li>
			</ul>
		</div>
	</div>
	</nav>
	<div class="container">
	<h2>Product maintenance</h2>
	<form:form action="/bookstore/product" method="POST" modelAttribute="product" enctype="multipart/form-data"> 	
		<div class="form-group">
			<label>Title</label>
			<form:input  path="title" cssClass="form-control"/>
			<form:errors path="title" />
		</div>
		<div class="form-group">
			<label>Description</label>
			<form:textarea path="description"  cssClass="form-control"></form:textarea>
			<form:errors path="description" />
		</div>
		<div class="form-group">
			<label>Pages</label> 
			<form:input  path="pages" cssClass="form-control" />
			<form:errors path="pages" />
		</div>
		<div class="form-group">
		    <label>Release Date</label>
		    <form:input  path="releaseDt" cssClass="form-control"/>
		    <form:errors path="releaseDt" />
		</div>
		<c:forEach items="${prices}" var="price" varStatus="status">
			<div class="form-group">
				 <label>${price}</label>
				 <form:input path="prices[${status.index}].amount" cssClass="form-control"/>
				 <form:hidden path="prices[${status.index}].pType" value="${price}" />
			</div>
		</c:forEach>
		<div class="form-group">
			<label>Summary file</label>
			<input name="summary" type="file" class="form-control"/>
		</div>
		<button type="submit" class="btn btn-primary">Save</button>
	</form:form>
	</div>
</body>
</html>
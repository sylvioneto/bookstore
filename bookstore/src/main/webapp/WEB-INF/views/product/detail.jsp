<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Bookstore - ${product.title}</title>
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
					<li><a href="${s:mvcUrl('PC#getProducts').build()}">List
							of Products</a></li>
					<li><a href="${s:mvcUrl('PC#form').build()}">Product input</a></li>
					<li><a href="${s:mvcUrl('CC#items').build()}">Your cart
							${cart.quantity}</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<div class="container">
		<article id="${product.id}">
			<h1>${product.title}</h1>
			<p>${product.description}</p>
			<p>Pages: ${product.pages}</p>
			<p>
				Release Date:
				<fmt:formatDate pattern="dd/MM/yyyy"
					value="${product.releaseDt.time}" />
			</p>
		</article>
		<section>
			<form action='<c:url value="/cart/add" />' method="POST">
				<input type="hidden" value="${product.id}" name="productId">
				<!-- Radio button to select the price -->
				<p>
					<strong>Prices</strong>
				</p>
				<c:forEach items="${product.prices}" var="price">
					<input type="radio" name="priceType" value="${price.pType}"
						required="required" />${price.pType}<br>
					<p>${price.amount}</p>
				</c:forEach>
				<button type="submit" title="Buy Now ${product.title}">Buy</button>
			</form>
		</section>
	</div>
</body>
</html>
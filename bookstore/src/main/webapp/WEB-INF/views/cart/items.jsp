<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<html>
<head>
<c:url value="/" var="contextPath" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Bookstore - Your cart</title>
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
		<h2>Your cart</h2>
		<p>${sucess}</p>
		<p>Your cart (${cart.quantity})</p>
		<table style="width: 80%">
			<thead>
				<tr>
					<th>Item</th>
					<th>Price</th>
					<th>Quantity</th>
					<th>Total</th>
					<th>Remove</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${cart.items}" var="item">
					<tr>
						<td>${item.product.title}</td>
						<td>${item.priceType}</td>
						<td>${cart.getQuantity(item)}</td>
						<td>${cart.getTotal(item)}</td>
						<td>
							<form
								action="${s:mvcUrl('CC#remove').arg(0, item.product.id).arg(1, item.priceType).build() }"
								method="POST">
								<input type="submit" name="remove" value="X" />
							</form>
						</td>
					</tr>
				</c:forEach>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="3">
						<form action="<c:url value="/payment/checkout"/>" method="POST">
							<input type="submit" class="checkout" name="checkout"
								value="Checkout" />
						</form>
					</td>
					<td class="numeric-cell">${cart.getTotal()}</td>
				</tr>
			</tfoot>
		</table>
	</div>
</body>
</html>
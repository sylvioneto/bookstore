<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
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
	<!-- Header -->
	<%@include file="/WEB-INF/views/header.jsp" %>

	<div class="container">
		<article id="${product.id}">
			<h1>${product.title}</h1>
			<p><fmt:message key="book.description"/>: ${product.description}</p>
			<p><fmt:message key="book.pages"/>: ${product.pages}</p>
			<p>
				<fmt:message key="book.releasedate"/>:	<fmt:formatDate pattern="dd/MM/yyyy" value="${product.releaseDt.time}" />
			</p>
		</article>
		<section>
			<form:form servletRelativeAction="/cart/add" method="POST" cssClass="container">
				<input type="hidden" value="${product.id}" name="productId">
				<!-- Radio button to select the price -->
				<p>
					<strong><fmt:message key="book.prices"/></strong>
				</p>
				<c:forEach items="${product.prices}" var="price">
					<input type="radio" name="priceType" value="${price.pType}"
						required="required" />${price.pType}<br>
					<p>${price.amount}</p>
				</c:forEach>
				<button type="submit" title="Buy Now ${product.title}">Buy</button>
			</form:form>
		</section>
	</div>
</body>
</html>
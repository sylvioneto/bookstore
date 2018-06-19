<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>

<tags:pageTemplate title="List of Products">         
	<div class="container">
		<h2>List of Products</h2>
		<p>${success}</p>
		<p>${fail}</p>
		<table class="table table-bordered table-hover">
			<thead class="thead-dark">
				<tr>
					<th><fmt:message key="book.title"/></th>
					<th><fmt:message key="book.description"/></th>
					<th><fmt:message key="book.pages"/></th>
					<th><fmt:message key="book.releasedate"/></th>
					<th colspan="2"><fmt:message key="book.prices"/></th>
				</tr>
			</thead>
			<c:forEach items="${products}" var="product">
				<tr>
					<td><a href="<c:url value="/product/detail/${product.id}"/>">${product.title}</a></td>
					<td>${product.description}</td>
					<td>${product.pages}</td>
					<td><fmt:formatDate pattern="dd/MM/yyyy" value="${product.releaseDt.time}" /></td>
						<c:forEach items="${product.prices}" var="price">
						<td>${price.pType} = ${price.amount}</td>
					</c:forEach>
				</tr>
			</c:forEach>
		</table>
	</div>
</tags:pageTemplate>
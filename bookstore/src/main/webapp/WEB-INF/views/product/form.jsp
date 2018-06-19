<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>

<tags:pageTemplate title="Cart">        
	
	<div class="container">
	<h2>Product maintenance</h2>
	<form:form action="/bookstore/product" method="POST" modelAttribute="product" enctype="multipart/form-data"> 	
		<div class="form-group">
			<label><fmt:message key="book.title"/></label>
			<form:input  path="title" cssClass="form-control"/>
			<form:errors path="title" />
		</div>
		<div class="form-group">
			<label><fmt:message key="book.description"/></label>
			<form:textarea path="description"  cssClass="form-control"></form:textarea>
			<form:errors path="description" />
		</div>
		<div class="form-group">
			<label><fmt:message key="book.pages"/></label> 
			<form:input  path="pages" cssClass="form-control" />
			<form:errors path="pages" />
		</div>
		<div class="form-group">
		    <label><fmt:message key="book.releasedate"/></label>
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
			<label><fmt:message key="book.summary"/></label>
			<input name="summary" type="file" class="form-control"/>
		</div>
		<button type="submit" class="btn btn-primary"><fmt:message key="global.save"/></button>
	</form:form>
	</div>
</tags:pageTemplate>
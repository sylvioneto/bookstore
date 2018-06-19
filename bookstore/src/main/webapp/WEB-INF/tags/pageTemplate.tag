<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="title" required="true" %>
<%@ attribute name="bodyClass" required="false" %>
<%@ attribute name="extraScripts" fragment="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
	<c:url value="/" var="contextPath" />
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Bookstore - ${title }</title>
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
<body class="${bodyClass}">

	<%@ include file="/WEB-INF/views/header.jsp"%>

	<jsp:doBody />

	<jsp:invoke fragment="extraScripts"></jsp:invoke>
</body>
</html>
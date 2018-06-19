<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<nav class="navbar navbar-inverse">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
				aria-expanded="false">
				<span class="sr-only">Toggle navigation</span> 
				<span class="icon-bar">1</span>
				<span class="icon-bar">2</span>
				<span class="icon-bar">3</span>
			</button>
			<a class="navbar-brand" href="${s:mvcUrl('HC#home').build()}">Bookstore</a>
		</div>
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li>
					<a href="${s:mvcUrl('PC#getProducts').build()}">
						<fmt:message key="menu.product.list"/>
					</a>
				</li>
				<security:authorize access="isAuthenticated()">
				<li>
					<a href="${s:mvcUrl('PC#form').build()}">
						<fmt:message key="menu.product.input"/>
					</a>
				</li>
				</security:authorize>
				<li><a href="${s:mvcUrl('CC#items').build()}">
					<fmt:message key="menu.cart">
						<fmt:param value="${cart.quantity}"/>
					</fmt:message>
					</a>
				</li>
				<li><a href="?locale=pt_BR"><fmt:message key="menu.pt"/></a></li>
				<li><a href="?locale=en_US"><fmt:message key="menu.en"/></a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<security:authorize access="isAuthenticated()">
					<li><a href="#"><security:authentication property="principal.username" /></a></li>
					<li><a href="/bookstore/logout"><fmt:message key="menu.logout"/></a></li>
				</security:authorize>
				<security:authorize access="!isAuthenticated()">
					<li><a href="/bookstore/login"><fmt:message key="menu.login"/></a></li>
				</security:authorize>
			</ul>
		</div>
	</div>
</nav>
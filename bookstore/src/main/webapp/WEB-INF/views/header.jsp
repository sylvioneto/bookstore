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
				<security:authorize access="isAuthenticated()">
					<li><a href="${s:mvcUrl('PC#form').build()}">Product input</a></li>
				</security:authorize>
				<li><a href="${s:mvcUrl('CC#items').build()}">Your cart	${cart.quantity}</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<security:authorize access="isAuthenticated()">
					<li><a href="#"><security:authentication
								property="principal.username" /></a></li>
					<li><a href="${contextPath}logout">Log Out</a></li>
				</security:authorize>
				<security:authorize access="!isAuthenticated()">
					<li><a href="${contextPath}login">Log In</a></li>
				</security:authorize>
			</ul>
		</div>
	</div>
</nav>
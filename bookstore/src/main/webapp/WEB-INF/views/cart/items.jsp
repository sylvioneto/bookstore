<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>

<tags:pageTemplate title="Cart">         
	
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
							<form:form	action="${s:mvcUrl('CC#remove').arg(0, item.product.id).arg(1, item.priceType).build() }"
								method="POST">
								<button type="submit">X</button>
							</form:form>
						</td>
					</tr>
				</c:forEach>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="3">
						<form:form action="${s:mvcUrl('PC#checkout').build()}" method="POST">
							<button type="submit" class="btn btn-primary">Checkout</button>
						</form:form>
					</td>
					<td class="numeric-cell">${cart.getTotal()}</td>
				</tr>
			</tfoot>
		</table>
	</div>
</tags:pageTemplate>
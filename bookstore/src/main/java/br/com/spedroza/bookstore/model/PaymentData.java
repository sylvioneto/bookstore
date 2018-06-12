package br.com.spedroza.bookstore.model;

import java.math.BigDecimal;

public class PaymentData {

	private BigDecimal value;
	
	public PaymentData(BigDecimal value) {
		this.value = value;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}
	
	

}

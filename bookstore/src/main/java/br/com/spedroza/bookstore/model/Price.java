package br.com.spedroza.bookstore.model;

import java.math.BigDecimal;

import javax.persistence.Embeddable;

//this annotation means it is into the product and will be persisted
@Embeddable
public class Price {

	private BigDecimal amount;
	private PriceType pType
	;
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public PriceType getpType() {
		return pType;
	}
	public void setpType(PriceType pType) {
		this.pType = pType;
	}
	@Override
	public String toString() {
		return this.pType.name()+" - "+this.amount;
	}
		
}

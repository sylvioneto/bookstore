package br.com.spedroza.bookstore.model;


import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Product{

	//Attributes
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String title;
	private String description;
	private int pages;
	
	@DateTimeFormat
	private Calendar releaseDt;
	
	private String summaryPath;
	
	@ElementCollection(fetch = FetchType.EAGER) //this annotation creates a new table for the prices linked to the product
	private List<Price> prices; //price list for this product
	
	// get and set
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public Calendar getReleaseDt() {
		return releaseDt;
	}

	public void setReleaseDt(Calendar releaseDt) {
		this.releaseDt = releaseDt;
	}

	public String getSummaryPath() {
		return summaryPath;
	}

	public void setSummaryPath(String summaryPath) {
		this.summaryPath = summaryPath;
	}

	public List<Price> getPrices() {
		return prices;
	}

	public void setPrices(List<Price> prices) {
		this.prices = prices;
	}

		
	@Override
	public String toString() {
		return "Product [id=" + id + ", title=" + title + ", description=" + description + ", pages=" + pages
				+ ", releaseDt=" + releaseDt + ", summaryPath=" + summaryPath + ", prices=" + prices + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (id != other.id)
			return false;
		return true;
	}

	// get the price based on price type
	public BigDecimal getPrice(PriceType priceType) {
		System.out.println("Inside Product.getPrice...");
		return prices.stream().filter(amount -> amount.getpType().equals(priceType)).findFirst().get().getAmount();
	}	
	
}

package br.com.spedroza.bookstore.builder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.spedroza.bookstore.model.Price;
import br.com.spedroza.bookstore.model.PriceType;
import br.com.spedroza.bookstore.model.Product;

/*
 * This class is a Product builder for testing purposes 
 */
public class ProductBuilder {

	public static List<Product> createList(PriceType priceType, BigDecimal amount, int quantity) {
		List<Product> products = new ArrayList<>();
		
		for (int i = 0; i < quantity; i++) {
			Product product = new Product();
			product.setTitle("Book no  "+i);
			product.setDescription("Description no "+i);
			product.setPages(i);
			Price price = new Price();
			price.setAmount(amount);
			price.setpType(priceType);
			product.getPrices().add(price);
			products.add(product);
		}

		return products;

	}
}

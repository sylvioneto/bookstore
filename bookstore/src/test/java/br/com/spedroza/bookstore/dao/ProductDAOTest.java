package br.com.spedroza.bookstore.dao;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import br.com.spedroza.bookstore.builder.ProductBuilder;
import br.com.spedroza.bookstore.conf.DataSourceConfigurationTest;
import br.com.spedroza.bookstore.conf.JPAConfiguration;
import br.com.spedroza.bookstore.model.PriceType;
import br.com.spedroza.bookstore.model.Product;

/*
 * This class tests ProductDAO class using JUNIT 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={JPAConfiguration.class, ProductDAO.class, DataSourceConfigurationTest.class})
@ActiveProfiles("test")
public class ProductDAOTest {
	
	@Autowired
	ProductDAO productDAO;

	@Test
	@Transactional
	public void sumPriceByPriceType() {
		
		// create product lists
		List<Product> ebookList = ProductBuilder.createList(PriceType.EBOOK, BigDecimal.ONE, 3);
		List<Product> printedList = ProductBuilder.createList(PriceType.PRINTED, BigDecimal.TEN, 3);

		// insert into the db
		ebookList.stream().forEach(p -> productDAO.insert(p));
		printedList.stream().forEach(p -> productDAO.insert(p));

		// query db
		BigDecimal ebookSum = productDAO.getTotalByPriceType(PriceType.EBOOK);
		BigDecimal printedSum = productDAO.getTotalByPriceType(PriceType.PRINTED);
		
		// compare the results
		Assert.assertEquals(new BigDecimal(3).setScale(2), ebookSum);
		Assert.assertEquals(new BigDecimal(30).setScale(2), printedSum);
	}
}

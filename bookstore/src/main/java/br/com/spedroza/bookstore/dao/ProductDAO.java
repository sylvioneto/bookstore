package br.com.spedroza.bookstore.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.spedroza.bookstore.model.PriceType;
import br.com.spedroza.bookstore.model.Product;

//this annotation is to spring recognize this class
@Repository
@Transactional
public class ProductDAO {

	// this annotation is to spring persistence injection
	@PersistenceContext
	private EntityManager manager;

	public void insert(Product product) {
		System.out.println("Inside ProductDAO.insert...");
		manager.persist(product);
	}

	public List<Product> getAll() {
		System.out.println("Inside ProductDAO.getAll...");
		System.out.println("Querying products... ");
		return manager.createQuery("select p from Product p", Product.class).getResultList();
	}

	public Product getById(int id) {
		System.out.println("Inside ProductDAO.getById...");
		System.out.println("Querying for product id " + id);
		return manager.createQuery(
				"select distinct(p) from Product p " + " join fetch p.prices prices " + " where p.id = :id",
				Product.class).setParameter("id", id).getSingleResult();
	}

	// get the total price by pricetype
	public BigDecimal getTotalByPriceType(PriceType priceType) {
		TypedQuery<BigDecimal> query = manager.createQuery("select sum(Price.amount) "
				+ " from Product p"
				+ " join p.prices Price where Price.pType = :priceType",
				BigDecimal.class);
		query.setParameter("priceType", priceType);
		return query.getSingleResult();
	}
}

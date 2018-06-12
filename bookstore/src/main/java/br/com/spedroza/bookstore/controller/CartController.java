package br.com.spedroza.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import br.com.spedroza.bookstore.dao.ProductDAO;
import br.com.spedroza.bookstore.model.Cart;
import br.com.spedroza.bookstore.model.CartItem;
import br.com.spedroza.bookstore.model.Product;
import br.com.spedroza.bookstore.model.PriceType;

@Controller
@RequestMapping("/cart")
@Scope(value=WebApplicationContext.SCOPE_REQUEST)
public class CartController {

	@Autowired
	private ProductDAO productDao;

	@Autowired
	private Cart cart;

	@RequestMapping("/add")
	public ModelAndView add(Integer productId, PriceType priceType) {
		System.out.println("inside CartController.add...");
		ModelAndView modelAndView = new ModelAndView("redirect:/cart");
		CartItem cartItem = addItem(productId, priceType);
		cart.add(cartItem);
		return modelAndView;
	}
	
	@RequestMapping("/remove")
	public ModelAndView remove(Integer produtoId, PriceType priceType) {
		System.out.println("inside CartController.remove...");
		cart.remove(produtoId, priceType);
		ModelAndView modelAndView = new ModelAndView("redirect:/cart");		
		return modelAndView;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView items() {
		System.out.println("inside CartController.items...");
		ModelAndView modelAndView = new ModelAndView("cart/items");
		return modelAndView;
	}

	private CartItem addItem(Integer productId, PriceType priceType) {
		Product product = productDao.getById(productId);
		CartItem cartItem = new CartItem(product, priceType);
		return cartItem;
	}
}

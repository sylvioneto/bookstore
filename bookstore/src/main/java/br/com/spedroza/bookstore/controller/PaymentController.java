package br.com.spedroza.bookstore.controller;

import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.spedroza.bookstore.model.Cart;
import br.com.spedroza.bookstore.model.PaymentData;

@RequestMapping("/payment")
@Controller
public class PaymentController {

	@Autowired
	private Cart cart;

	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping(value = "/checkout", method = RequestMethod.POST)
	public Callable<ModelAndView> checkout(RedirectAttributes model) {
		// Callable is used for asynchronous calls 
		// Anonymous class
		return () -> {

			System.out.println("inside PaymentController.checkout...");
			System.out.println("Total: " + cart.getTotal());

			// call external payment api (Paypal, Visa, Mastercard, etc)
			System.out.println("Preparing Payment API request...");
			try {
				String url = "http://book-payment.herokuapp.com/payment";
				String response = restTemplate.postForObject(url, new PaymentData(cart.getTotal()), String.class);
				System.out.println("API response: " + response);
				model.addFlashAttribute("success", response);
				cart.clear();
			} catch (HttpClientErrorException e) {
				System.out.println("Request error: " + e.getMessage());
				model.addFlashAttribute("fail", e.getMessage());
				e.printStackTrace();
			}
			System.out.println("End of Payment API request...");
			return new ModelAndView("redirect:/product");
		};
	}
}

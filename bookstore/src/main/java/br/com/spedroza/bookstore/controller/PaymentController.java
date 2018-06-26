package br.com.spedroza.bookstore.controller;

import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.spedroza.bookstore.model.Cart;
import br.com.spedroza.bookstore.model.PaymentData;
import br.com.spedroza.bookstore.model.User;

@RequestMapping("/payment")
@Controller
public class PaymentController {

	@Autowired
	private Cart cart;

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private MailSender mailSender;

	@RequestMapping(value = "/checkout", method = RequestMethod.POST)
	public Callable<ModelAndView> checkout(@AuthenticationPrincipal User user,RedirectAttributes model) {
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
				System.out.println("End of Payment API request...");
				System.out.println("Sending mail...");
				sendMail(user);
			} catch (HttpClientErrorException e) {
				System.out.println("Request error: " + e.getMessage());
				model.addFlashAttribute("fail", e.getMessage());
				e.printStackTrace();
			} finally {
				cart.clear();
			}
			
			return new ModelAndView("redirect:/product");
		};
	}
	
	private void sendMail(User user) {
		System.out.println("Inside PaymentController.sendMail...");
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setSubject("Order received");
		mailMessage.setFrom("sid.neto@yahoo.com.br");
		//mailMessage.setTo(user.getEmail());
		mailMessage.setTo("sylvio.pedroza@gmail.com");
		StringBuilder productList = new StringBuilder();
		cart.getItems().stream().forEach(i -> productList.append(i.getProduct().getTitle()));
		mailMessage.setText("Your order has been received! Products: "+productList);
		mailSender.send(mailMessage);
		System.out.println("End of PaymentController.sendMail...");
	}
}

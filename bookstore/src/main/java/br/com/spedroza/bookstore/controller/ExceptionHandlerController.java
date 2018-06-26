package br.com.spedroza.bookstore.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionHandlerController {

	// this method will get the exceptions from the other controllers and send the user to error page
	@ExceptionHandler(Exception.class)
	public ModelAndView handleNoResultException(Exception ex) {
		ModelAndView modelAndView = new ModelAndView("error");
		modelAndView.addObject("exception", ex.getMessage());
		ex.printStackTrace();
		return modelAndView;
	}
}

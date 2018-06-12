package br.com.spedroza.bookstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping("/")
	public String home(){
		System.out.println("Inside HomeController.home ...");
		return "home";
	}
}

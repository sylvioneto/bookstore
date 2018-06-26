package br.com.spedroza.bookstore.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.spedroza.bookstore.dao.ProductDAO;
import br.com.spedroza.bookstore.infra.FileSaver;
import br.com.spedroza.bookstore.model.PriceType;
import br.com.spedroza.bookstore.model.Product;
import br.com.spedroza.bookstore.validation.ProductValidation;

@Controller
@RequestMapping("/product")
public class ProductController {

	// this annotation is to spring inject product dao here
	@Autowired
	private ProductDAO pdao;
	
	@Autowired
	private FileSaver fileSaver;
	
	// this method links the validator with the product
	@InitBinder
	public void initBinder(WebDataBinder binder) {
	    binder.addValidators(new ProductValidation());
	}
	
	//this method is mapped to /products/form and send the user to form.jsp
	@RequestMapping("/form")
	public ModelAndView form(Product product){
		System.out.println("Inside ProductController.form");
		System.out.println("Creating a modelAndView for /product/form");
		ModelAndView modelAndView = new ModelAndView("/product/form");
		System.out.println("Setting PriceType enum");
		modelAndView.addObject("prices", PriceType.values());
		return modelAndView;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@CacheEvict(value="productList", allEntries=true) 
	public ModelAndView save(MultipartFile summary, @Valid Product product, BindingResult result, RedirectAttributes redirectAttributes){
		// Valid annotation send the Product object to validation
		// MultipartFile is used to received the file		
		System.out.println("Inside ProductController.save");
		System.out.println(product);
		
		System.out.println("Filename: "+summary.getOriginalFilename());
		if(summary.getOriginalFilename()!=null && !summary.getOriginalFilename().isEmpty()) {
			String path = fileSaver.write("upload-dir", summary);
			product.setSummaryPath(path);	
		}
		
		// check problems
		  if (result.hasErrors()) {
		        return form(product);
		    }
		
		// insert the product  
		pdao.insert(product);
		
		// set success message
		System.out.println("Setting success message...");
		redirectAttributes.addFlashAttribute("success", "Product has been inserted successfully!!!");
		// send user to product list page using redirect after posting
		System.out.println("Redirect after posting...");
		return new ModelAndView("redirect:/product");
	}
	
	@RequestMapping(method=RequestMethod.GET)
	@Cacheable(value="productList")
	public ModelAndView getProducts(){
		System.out.println("Inside ProductController.getProducts");
		ModelAndView modelAndView = new ModelAndView("product/list");
		List<Product> products = pdao.getAll();
		System.out.println("Setting products list...");
		modelAndView.addObject("products",products);
		return modelAndView;
	}
	
	@RequestMapping("/detail/{id}")
	public ModelAndView detail(@PathVariable("id") int id){
		System.out.println("Inside ProductController.detail");
		ModelAndView modelAndView = new ModelAndView("product/detail");
		Product product = pdao.getById(id);
		System.out.println("Setting product...");
		modelAndView.addObject("product", product);
		return modelAndView;
	}
	
	// this is a rest api to return a product in json format
	@RequestMapping("/{id}")
	@ResponseBody // spring will return the body
	public Product getProductJson(@PathVariable("id") int id){
		System.out.println("Inside ProductController.getProductJson");
		return pdao.getById(id);
	}
	
}

package br.com.spedroza.bookstore.controller;

import javax.servlet.Filter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import br.com.spedroza.bookstore.conf.AppWebConfiguration;
import br.com.spedroza.bookstore.conf.DataSourceConfigurationTest;
import br.com.spedroza.bookstore.conf.JPAConfiguration;
import br.com.spedroza.bookstore.conf.SecurityConfiguration;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JPAConfiguration.class, AppWebConfiguration.class, DataSourceConfigurationTest.class, SecurityConfiguration.class})
@ActiveProfiles("test")
public class ProductControllerTest {

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;
	
	@Autowired
	private Filter springSecurityFilterChain;
	
	@Before
	public void setup(){
	    mockMvc = MockMvcBuilders.webAppContextSetup(wac).addFilter(springSecurityFilterChain).build();
	}
	
	/*
	 * This method tests if the product page returns with all products 
	 */
	@Test
	public void mustReturnProductListWithProducts() throws Exception {
	    mockMvc.perform(MockMvcRequestBuilders.get("/product"))
	    	.andExpect(MockMvcResultMatchers.model().attributeExists("products")) // check if the products are there
	    	.andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/views/product/list.jsp")); // check the forward
	}
	
	/*
	 * This method tests the access to product form page
	 */
	@Test
	public void onlyAdminCanAccessProductForm() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/product/form")
				.with(SecurityMockMvcRequestPostProcessors.user("sylvio.pedroza@gmail.com") // set requester
						.password("123456")
						.roles("USUARIO"))) 
    	 	    .andExpect(MockMvcResultMatchers.status().is(403)); // check the forward
	}
}

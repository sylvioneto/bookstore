package br.com.spedroza.bookstore.conf;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.guava.GuavaCacheManager;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.datetime.DateFormatterRegistrar;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.google.common.cache.CacheBuilder;

import br.com.spedroza.bookstore.controller.HomeController;
import br.com.spedroza.bookstore.dao.ProductDAO;
import br.com.spedroza.bookstore.infra.FileSaver;
import br.com.spedroza.bookstore.model.Cart;

@EnableWebMvc
@EnableCaching
@ComponentScan(basePackageClasses = { HomeController.class, ProductDAO.class, FileSaver.class, Cart.class })
public class AppWebConfiguration implements WebMvcConfigurer {

	@Bean
	public InternalResourceViewResolver internalResourceViewResolver() {
		System.out.println("Inside AppWebConfiguration.internalResourceViewResolver");
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/"); // views path
		resolver.setSuffix(".jsp"); // pages sufix

		// cart object will be exposed
		resolver.setExposedContextBeanNames("cart");

		return resolver;
	}

	@Bean
	public MessageSource messageSource() {
		System.out.println("Inside AppWebConfiguration.messageSource");
		System.out.println("Setting messages property file...");
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("/WEB-INF/messages"); // messages file
		messageSource.setDefaultEncoding("UTF-8"); // encoding
		messageSource.setCacheSeconds(1);
		return messageSource;
	}

	@Bean
	public FormattingConversionService mvcConversionService() {
		System.out.println("Inside AppWebConfiguration.mvcConversionService");
		DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService();
		DateFormatterRegistrar registrar = new DateFormatterRegistrar();
		System.out.println("Setting date format...");
		registrar.setFormatter(new DateFormatter("dd/MM/yyyy"));
		registrar.registerFormatters(conversionService);
		return conversionService;
	}

	@Bean
	public MultipartResolver multipartResolver() {
		return new StandardServletMultipartResolver();
	}

	@Bean
	public RestTemplate restTemplate() {
		System.out.println("Inside AppWebConfiguration.restTemplate");
		return new RestTemplate();
	}

	@Bean
	public CacheManager cacheManager() {
		System.out.println("Inside AppWebConfiguration.cacheManager");
		// return new ConcurrentMapCacheManager(); //basic cache only for tests
		System.out.println("Setting cache : 5 minutes and 100 objects...");
		CacheBuilder<Object, Object> cacheBuilder = CacheBuilder.newBuilder().maximumSize(100).expireAfterAccess(5,
				TimeUnit.MINUTES);
		GuavaCacheManager manager = new GuavaCacheManager();
		manager.setCacheBuilder(cacheBuilder);
		return manager;
	}

	// this new resolver method allow the requester to choose the response type json
	// in the request
	@Bean
	public ViewResolver contentNegotiationViewResolver(ContentNegotiationManager manager) {
		System.out.println("Inside AppWebConfiguration.contentNegotiationViewResolver");
		List<ViewResolver> viewResolvers = new ArrayList<>();
		viewResolvers.add(internalResourceViewResolver());
		viewResolvers.add(new JsonViewResolver());

		ContentNegotiatingViewResolver viewResolver = new ContentNegotiatingViewResolver();
		viewResolver.setViewResolvers(viewResolvers);
		viewResolver.setContentNegotiationManager(manager);

		return viewResolver;
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		System.out.println("Inside AppWebConfiguration.addInterceptors");
		registry.addInterceptor(new LocaleChangeInterceptor());
	}

	@Bean
	public LocaleResolver localeResolver() {
		return new CookieLocaleResolver();
	}

	@Bean
	public MailSender mailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

		mailSender.setHost("smtp.mail.yahoo.com");
		mailSender.setUsername("user@yahoo.com.br");
		mailSender.setPassword("password");
		mailSender.setPort(465);
		
		Properties mailProperties = new Properties();
		mailProperties.put("mail.smtp.auth", true);
		mailProperties.put("mail.smtp.ssl.enable", "true");
		mailProperties.put("mail.smtp.ssl.trust", "*");
		mailProperties.put("mail.smpt.starttls.enable", true); 
		mailProperties.put("mail.smtp.connectiontimeout", "10000");
		mailProperties.put("mail.debug", "true");
		mailSender.setJavaMailProperties(mailProperties);
		return mailSender;
	}
}

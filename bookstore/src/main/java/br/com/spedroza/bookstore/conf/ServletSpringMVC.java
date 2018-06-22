package br.com.spedroza.bookstore.conf;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class ServletSpringMVC extends AbstractAnnotationConfigDispatcherServletInitializer{

	@Override
	protected Class<?>[] getRootConfigClasses() {
		System.out.println("Inside ServletSpringMVC.getRootConfigClasses...");
		return new Class[]{SecurityConfiguration.class, AppWebConfiguration.class, JPAConfiguration.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		System.out.println("Inside ServletSpringMVC.getServletConfigClasses...");
		return new Class[]{};
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] {"/"};
	}
	
	// filter to set UTF-8
	@Override
	protected Filter[] getServletFilters() {
		System.out.println("Setting Servlet Filters...");
		CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
		System.out.println("Setting characterset UTF-8...");
		encodingFilter.setEncoding("UTF-8");
		return new Filter[]{encodingFilter};
	}
	
	@Override
	protected void customizeRegistration(Dynamic registration) {
		System.out.println("Setting customizeRegistration...");
		registration.setMultipartConfig(new MultipartConfigElement(""));
	}
	
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		System.out.println("Inside ServletSpringMVC.onStartup...");
		super.onStartup(servletContext);
		servletContext.addListener(RequestContextListener.class);
		servletContext.setInitParameter("spring.profiles.active", "app");
	}
}

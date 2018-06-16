package br.com.spedroza.bookstore.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import br.com.spedroza.bookstore.dao.UserDAO;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

	@Autowired
	private UserDAO userDAO;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		System.out.println("Inside SecurityConfiguration.configure HttpSecurity");
		http.authorizeRequests()
		.antMatchers(HttpMethod.POST, "/product").hasRole("ADMIN") // only admin can post new products
	    .antMatchers(HttpMethod.GET, "/product").permitAll() // all users can see produt list
	    .antMatchers("/product/form").hasRole("ADMIN") // only admin can access produt form
	    .antMatchers("/resources/**").permitAll()
	    .antMatchers("/cart/**").permitAll()
	    .antMatchers("/payment/**").permitAll()
	    .antMatchers("/product/**").permitAll()
	    .antMatchers("/").permitAll()
	    .anyRequest().authenticated()
	    .and().formLogin().loginPage("/login").permitAll() // login page
		.and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll(); // logout page
	}
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		System.out.println("Inside SecurityConfiguration.configure AuthenticationManagerBuilder");
		auth.userDetailsService(userDAO).passwordEncoder(new BCryptPasswordEncoder());
	}
}

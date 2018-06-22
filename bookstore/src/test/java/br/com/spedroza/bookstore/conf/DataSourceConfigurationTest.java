package br.com.spedroza.bookstore.conf;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class DataSourceConfigurationTest {

	@Bean
	@Profile("test")
	public DataSource getDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUsername("spedroza");
		dataSource.setPassword("1234");
		dataSource.setUrl("jdbc:mysql://localhost:3306/bookstore_test?useSSL=false&serverTimezone=UTC");
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		
		return dataSource;
	}
}

package br.com.spedroza.bookstore.conf;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

// class to configure and connect to the database
@EnableTransactionManagement //spring will use this class to control the transactions
public class JPAConfiguration {

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(){
		System.out.println("Getting entityManagerFactory...");
		LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
		
		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		factoryBean.setJpaVendorAdapter(vendorAdapter);

		// database connection details
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUsername("spedroza");
		dataSource.setPassword("1234");
		dataSource.setUrl("jdbc:mysql://localhost:3306/bookstore?useSSL=false&serverTimezone=UTC");
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		factoryBean.setDataSource(dataSource);
		
		// hibernate properties
		Properties properties = new Properties();
		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
		properties.setProperty("hibernate.show_sql", "true");
		properties.setProperty("hibernate.hbm2ddl.auto", "update");
		factoryBean.setJpaProperties(properties);
		
		//models
		factoryBean.setPackagesToScan("br.com.spedroza.bookstore.model");
		return factoryBean;
	}
	
	@Bean
	public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFac){
		System.out.println("Getting transactionManager...");
		return new JpaTransactionManager(entityManagerFac);
	}
}

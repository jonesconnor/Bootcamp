package com.cgi.springbootwithhibernate;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

@Configuration
public class DbConfig {
	
	@Value("${db.driver}")
	String driver;
	
	@Value("${db.url}")
	String url;
	
	@Value("${db.username}")
	String username;
	
	@Value("${db.password}")
	String password;
	
	@Value("${hibernate.dialect}")
	String dialect;
	
	@Value("${hibernate.hbm2ddl.auto}")
	String hbm;
	
	@Value("${entitymanager.packageToScan}")
	String pkg;
	
	@Bean
	public DataSource getDataSource() {
		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
		driverManagerDataSource.setDriverClassName(driver);
		driverManagerDataSource.setUrl(url);
		driverManagerDataSource.setUsername(username);
		driverManagerDataSource.setPassword(password);
		return driverManagerDataSource;
	}
	
	public LocalSessionFactoryBean getSessionFactory(DataSource source) {
		LocalSessionFactoryBean sFB = new LocalSessionFactoryBean();
		sFB.setDataSource(source);
		sFB.setPackagesToScan(pkg);
		Properties properties = new Properties();
		properties.put("hibernate.dialect", dialect);
		properties.put("hibernate.hbm2ddl.auto", hbm);
		sFB.setHibernateProperties(properties);
		return sFB;
	}
	
	public HibernateTransactionManager transactionManager(SessionFactory) {
		
	}
	
}

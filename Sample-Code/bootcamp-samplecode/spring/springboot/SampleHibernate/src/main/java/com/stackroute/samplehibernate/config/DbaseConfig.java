package com.stackroute.samplehibernate.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class DbaseConfig {

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
	@Value("${hibernate.show_sql}")
	String show;
	@Value("${hibernate.hbm2ddl.auto}")
	String hbm;
	@Value("${entity.packagestoscan}")
	String mypakcage;
	
	
	
	
	
	@Bean
	public DataSource getDatasource()
	{
		DriverManagerDataSource datasource=new DriverManagerDataSource();
		
		datasource.setDriverClassName(driver);
		datasource.setUrl(url);
		datasource.setUsername(username);
		datasource.setPassword(password);
       return datasource;
		
	}
	
	@Bean
	public LocalSessionFactoryBean getSessionfactory(DataSource datasource)
	{
		LocalSessionFactoryBean sess=new LocalSessionFactoryBean();
		sess.setDataSource(datasource);
		sess.setPackagesToScan(mypakcage);
		Properties hiberprop=new Properties();
		hiberprop.put("hibernate.dialect",dialect);
		hiberprop.put("hibernate.show_sql",show);
		hiberprop.put("hibernate.hbm2ddl.auto",hbm);
		sess.setHibernateProperties(hiberprop);
		return sess;
	}
	
	@Bean
	public HibernateTransactionManager getTransaction(SessionFactory sessfactory)
	{
		HibernateTransactionManager hmanager=new HibernateTransactionManager();
		hmanager.setSessionFactory(sessfactory);
		hmanager.afterPropertiesSet();
		return hmanager;
	}
	
	
}

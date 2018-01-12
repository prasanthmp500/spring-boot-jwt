package com.openmind.springjwt.springbootjwt.jpa.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableJpaRepositories("com.openmind.springjwt.springbootjwt.jpa.repository")
class JpaConfiguration {
	
  @Value("${spring.datasource.jdbcUrl}")
  private String jdbcUrl;
  
  @Value("${spring.datasource.username}")
  private String dbUserName;
  
  @Value("${spring.datasource.password}")
  private String dbPassword;
  
  @Value("${spring.datasource.driverClassName}")
  private String dbDiverClassName;
  
  @Value("${spring.datasource.minimumIdlePoolSize}")
  private int minimumIdle;
  
  @Value("${spring.datasource.maximumPoolSize}")
  private int maximumPoolSize;
  
  @Value("${spring.datasource.idleConnectionTimeOut}")
  private long idleTimeOutMs;
  
  
  @Bean 	
  DataSource dataSource() {
	 
	 HikariDataSource dataSource = new HikariDataSource();
	 
	 dataSource.setPoolName("springbootjwt-cp");
	 dataSource.setJdbcUrl(jdbcUrl);
	 dataSource.setUsername(dbUserName);
	 dataSource.setPassword(dbPassword);
	 dataSource.setDriverClassName(dbDiverClassName);
	 dataSource.setIdleTimeout(idleTimeOutMs);
	 dataSource.setMinimumIdle(minimumIdle);
	 dataSource.setMaximumPoolSize(maximumPoolSize);
	 
	 return dataSource;
 }
	
  @Bean
  public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

    HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
    vendorAdapter.setShowSql(true);
    vendorAdapter.setGenerateDdl(false);
    vendorAdapter.setDatabasePlatform("org.hibernate.dialect.MySQL5Dialect");
    
    LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
    factory.setJpaVendorAdapter(vendorAdapter);
    factory.setPackagesToScan("com.openmind.springjwt.springbootjwt.jpa.entities");

    factory.setDataSource(dataSource());
    return factory;
  }
  
  @Bean
  public PlatformTransactionManager transactionManager() {

    JpaTransactionManager txManager = new JpaTransactionManager();
    txManager.setEntityManagerFactory(entityManagerFactory().getObject());
    return txManager;
  }
  
}
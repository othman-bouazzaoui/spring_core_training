package com.oth.dao.jpa;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import jakarta.persistence.EntityManagerFactory;


@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = { "com.oth.dao.jpa" })
public class JpaDataSource {

	private static final Logger LOG = LoggerFactory.getLogger(JpaDataSource.class);

	@Value("${spring.datasource.url:jdbc:mysql://localhost:3306/spring?useSSL=false&serverTimezone=UTC}")
	private String jdbcUrl;

	@Value("${spring.datasource.username:root}")
	private String username;

	@Value("${spring.datasource.password:root}")
	private String password;

	@Value("${spring.datasource.driver-class-name:com.mysql.cj.jdbc.Driver}")
	private String driverClassName;

	@Bean
	public DataSource dataSource() {
		HikariConfig config = new HikariConfig();

		// Datasource configuration
		config.setJdbcUrl(jdbcUrl);
		config.setUsername(username);
		config.setPassword(password);
		config.setDriverClassName(driverClassName);

		LOG.debug("BDD configuration - url: {} -  username: {}", jdbcUrl, username);

		return new HikariDataSource(config);
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
		LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
		emf.setDataSource(dataSource);
		emf.setPackagesToScan("com.oth.model");
		emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		
		return emf;
	}

	@Bean
	public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}
}

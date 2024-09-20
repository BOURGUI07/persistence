package com.example.CH02.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@EnableJpaRepositories("com.example.CH02.repos")
@Configuration
@EnableTransactionManagement
public class SpringDataConfig {
    @Bean
    public DataSource dataSource() {
        var datasource = new DriverManagerDataSource();
        datasource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        datasource.setUrl("jdbc:mysql://localhost:3306/CH02?serverTimezone=UTC");
        datasource.setUsername("springstudent");
        datasource.setPassword("springstudent");
        return datasource;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        var jpaVendorAdapter = new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setShowSql(true);
        jpaVendorAdapter.setDatabase(Database.MYSQL);
        return jpaVendorAdapter;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        var factory = new LocalContainerEntityManagerFactoryBean();
        factory.setDataSource(dataSource());
        var properties = new Properties();
        properties.put("hibernate.hbm2ddl.auto", "create");
        factory.setJpaProperties(properties);
        factory.setJpaVendorAdapter(jpaVendorAdapter());
        factory.setPackagesToScan("com.example.CH02");
        return factory;
    }
}

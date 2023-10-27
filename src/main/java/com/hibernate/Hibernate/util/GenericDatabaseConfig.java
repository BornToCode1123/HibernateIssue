package com.hibernate.Hibernate.util;


import com.zaxxer.hikari.HikariDataSource;
import org.hibernate.ConnectionReleaseMode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;


import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Properties;

@Configuration
    @EnableTransactionManagement
    public class GenericDatabaseConfig {

        @Value("${hibernate.dialect}")
        private String dialect;
        @Value("${entitymanager.packagesToScan}")
        private String hibernatePackagesToScan;

        @Value("${hibernate.id.new_generator_mappings}")
        private String hibernateIdNewGeneratorMappings;

        @Value("${hibernate.allow_update_outside_transaction:false}")
        private Boolean hibernateAllowUpdateOutsideTransaction;






        @Bean
        @Primary
        public LocalSessionFactoryBean sessionFactory(HikariDataSource dataSource) {
            try {
                LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
//			dataSource.setLeakDetectionThreshold(60 * (long) 10000);
                sessionFactoryBean.setDataSource(dataSource);
                String[] arr = hibernatePackagesToScan.split(",");
                sessionFactoryBean.setPackagesToScan(arr);


                Properties hibernateProperties = new Properties();
                hibernateProperties.put("hibernate.dialect", dialect);
                hibernateProperties.put("hibernate.show_sql", false);
                hibernateProperties.put("hibernate.enable_lazy_load_no_trans", "true");
                hibernateProperties.put("hibernate.id.new_generator_mappings", hibernateIdNewGeneratorMappings);
                hibernateProperties.put("hibernate.connection.release_mode", ConnectionReleaseMode.AFTER_STATEMENT);
//        hibernateProperties.put("javax.persistence.validation.mode", "none");
                hibernateProperties.put("hibernate.allow_update_outside_transaction", hibernateAllowUpdateOutsideTransaction);


                sessionFactoryBean.setHibernateProperties(hibernateProperties);

                return sessionFactoryBean;
            } catch (Exception e) {

            }
            return null;
        }



    }

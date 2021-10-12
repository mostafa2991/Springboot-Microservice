package com.customers.sqllite.config;

import com.customers.sqllite.CustomersApplication;
import org.apache.logging.log4j.LogManager;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;


@Configuration
public class GeneralConfiguration {

//    private static final Logger LOGGER = LoggerFactory.getLogger(CustomersApplication.class);


    private Map<String, String> countries;

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean(name = "populateCountries")
    public Map<String, String> populateCountries(){
//        LOGGER.info("Testing 123");
        countries = new HashMap<>();
        countries.put("Cameroon", "(237)");
        countries.put("Ethiopia", "(251)");
        countries.put("Morocco", "(212)");
        countries.put("Mozambique", "(258)");
        countries.put("Uganda", "(256)");
        return  countries;
    }


//    @Bean
//    public DataSource dataSource() {
//        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName(env.getProperty("org.sqlite.JDBC"));
//        dataSource.setUrl(env.getProperty("jdbc:sqlite:sample.db"));
//        return dataSource;
//    }

    @Bean
    @Primary

    public DataSource getDataSource() {
//        LOGGER.info("Testing 123");

        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("org.sqlite.JDBC");
        dataSourceBuilder.url("jdbc:sqlite::sample.db");
        dataSourceBuilder.username("");
        dataSourceBuilder.password("");
        return dataSourceBuilder.build();
    }

//    @Bean
//    @Primary
//    public DataSource dataSource() {
//        return DataSourceBuilder
//                .create()
//                .username("")
//                .password("")
//                .url("lite:sample.db")
//                .driverClassName("jdbc:sqlite:sample.db")
//                .build();
//    }


}

package com.itranswarp.learnjava;

import com.itranswarp.learnjava.config.MasterDataSourceConfiguration;
import com.itranswarp.learnjava.config.RoutingDataSourceConfiguration;
import com.itranswarp.learnjava.config.SlaveDataSourceConfiguration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableAutoConfiguration(exclude = DataSourceAutoConfiguration.class)
@Import({ MasterDataSourceConfiguration.class, SlaveDataSourceConfiguration.class, RoutingDataSourceConfiguration.class })
public class Application {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }

    // -- Mvc configuration ---------------------------------------------------

    @Bean
    WebMvcConfigurer createWebMvcConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addResourceHandlers(ResourceHandlerRegistry registry) {
                registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
            }
        };
    }
}

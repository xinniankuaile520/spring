package com.itranswarp.learnjava.web;

import java.io.IOException;
import java.util.List;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(20)
@Component
public class ApiFilterRegistrationBean extends FilterRegistrationBean<Filter> {

    @PostConstruct
    public void init() {
        setFilter(new ApiFilter());
        setUrlPatterns(List.of("/api/*"));
    }

    class ApiFilter implements Filter {
        @Override
        public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
            HttpServletResponse resp = (HttpServletResponse) response;
            resp.setHeader("X-Api-Version", "1.0");
            chain.doFilter(request, response);
        }
    }
}

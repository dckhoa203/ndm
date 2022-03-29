package com.ndm.api.controller.error;

import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

@Configuration
public class ErrorPageCustomize implements WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> {

    /**
     * This is a custom method for error page path not found
     * @param factory ConfigurableServletWebServerFactory
     */
    @Override
    public void customize(ConfigurableServletWebServerFactory factory) {
        factory.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/404"));
    }
}

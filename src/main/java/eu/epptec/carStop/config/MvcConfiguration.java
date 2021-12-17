package main.java.eu.epptec.carStop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @EnableWebMvc - imports the Spring MVC configuration.
 *  It enables support for @Controller to map incoming requests.
 *  It can be added to one @Configuration class.
 * @ComponentScan and @Configuration will scan package and subpackages to create beans.
 * By default, the current folder is the start
 */
@EnableWebMvc
@Configuration
@ComponentScan(basePackages = "main.java.eu.epptec.carStop.controller")
@ComponentScan(basePackages = "eu.epptec.carStop.service")
public class MvcConfiguration implements WebMvcConfigurer {
    /**
     * Configure a handler to delegate unhandled requests by forwarding to the Servlet container's "default" servlet.
     *  A common use case for this is when the DispatcherServlet is mapped to "/".
     */
    @Override
    public void configureDefaultServletHandling(
            DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}

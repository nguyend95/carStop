package eu.epptec.carStop;

import eu.epptec.carStop.config.MvcConfiguration;
import eu.epptec.carStop.config.rootConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;


/**
 * Spring MVC is designed around the front controller with central Servlet - DispatcherServlet.
 * Each DispatcherServlet has Servlet WebApplicationContext and Root WebApplicationContext.
 * If you have multiple DispatcherServlets in a webapp and some of them are using the same beans
 * such as datasources, middle-tier services, ... Those beans should go to Root WebApplicationContext
 * Controllers, viewResolver, HandlerMapping should go to Servlet WebApplicationContext
 * In our case, we can put everything to Root WebApplicationContext because we have only one DispatcherServlet
 * If
 * WebApplicationContext is configured by methods -
 *      getServletMappings, getRootConfig and getServletConfig
 *
 * credits: https://docs.spring.io/spring-framework/docs/current/reference/html/web.html
 */
public class AppInitializer
        extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {

        super.onStartup(servletContext);
    }

//  default mapping
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[] { rootConfig.class };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] { MvcConfiguration.class };
    }
}

package hr.tvz.productservice.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static hr.tvz.productservice.constant.ConfigurationConstants.*;

/**
 * A class representing web MVC configuration for product service.
 */
@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController(ADD_VIEW_CONTROLLER_ONE).setViewName(VIEW_NAME);
        registry.addViewController(ADD_VIEW_CONTROLLER_TWO).setViewName(VIEW_NAME);
        registry.addViewController(ADD_VIEW_CONTROLLER_THREE).setViewName(VIEW_NAME);
    }

}

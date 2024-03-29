package hr.tvz.productservice.configuration;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/**
 * A class representing validation configuration for product service.
 */
@Configuration
public class ValidationConfiguration {

    /**
     * Bean for custom validation.
     *
     * @return {@link LocalValidatorFactoryBean} bean
     */
    @Bean
    public LocalValidatorFactoryBean validator(MessageSource messageSource) {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource);
        return bean;
    }

}

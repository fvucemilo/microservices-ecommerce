package hr.tvz.productservice.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import java.util.List;
import java.util.Locale;

import static hr.tvz.productservice.constant.ConfigurationConstants.DEFAULT_LOCALE;

/**
 * A class representing localization configuration.
 */
@Configuration
public class LocalizationConfiguration {

    @Value("${i18n.locale.supported}")
    private List<String> supportedLocales;

    /**
     * Bean for configuring i18n.
     *
     * @return {@link LocaleResolver} bean
     */
    @Bean
    public LocaleResolver localeResolver() {
        AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();
        localeResolver.setDefaultLocale(Locale.forLanguageTag(DEFAULT_LOCALE));

        if (supportedLocales != null && !supportedLocales.isEmpty()) {
            localeResolver.setSupportedLocales(supportedLocales
                .stream()
                .map(Locale::forLanguageTag)
                .toList());
        }
        return localeResolver;
    }
}

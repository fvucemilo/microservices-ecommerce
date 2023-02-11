package hr.tvz.productservice.configuration;

import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static hr.tvz.productservice.constant.ConfigurationConstants.*;
import static java.time.format.DateTimeFormatter.ofPattern;

/**
 * A class representing Jackson ObjectMapper configuration.
 */
@Configuration
public class ObjectMapperConfiguration {

    /**
     * Bean for configuring date and time formatting.
     *
     * @return {@link Jackson2ObjectMapperBuilderCustomizer} bean
     */
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jsonDateTimeFormat() {
        return builder -> {
            builder.simpleDateFormat(SIMPLE_DATE_FORMAT);
            builder.serializers(new LocalDateSerializer(ofPattern(LOCAL_DATE_SERIALIZER_FORMAT)));
            builder.serializers(new LocalDateTimeSerializer(ofPattern(LOCAL_DATE_TIME_SERIALIZER_FORMAT)));
        };
    }

}

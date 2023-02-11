package hr.tvz.productservice.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * A class representing string constants for the configuration classes.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ConfigurationConstants {

    /* ViewControllerRegistry constants */
    public static final String ADD_VIEW_CONTROLLER_ONE = "/{spring:^[a-zA-Z\\d-_]+}";
    public static final String ADD_VIEW_CONTROLLER_TWO = "/**/{spring:^[a-zA-Z\\d-_]+}";
    public static final String ADD_VIEW_CONTROLLER_THREE = "/{spring:^[a-zA-Z\\d-_]+}/**{spring:?!(\\.js|\\.css)$}";
    public static final String VIEW_NAME = "forward:/";

    public static final String DEFAULT_LOCALE = "en";

    /* Jackson2ObjectMapperBuilderCustomizer bean constants */
    public static final String SIMPLE_DATE_FORMAT = "MM.dd.yyyy. HH:mm";
    public static final String LOCAL_DATE_SERIALIZER_FORMAT = "MM.dd.yyyy.";
    public static final String LOCAL_DATE_TIME_SERIALIZER_FORMAT = SIMPLE_DATE_FORMAT;

}

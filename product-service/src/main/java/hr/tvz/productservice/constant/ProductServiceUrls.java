package hr.tvz.productservice.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * A class representing string constants for product service REST URL endpoints.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ProductServiceUrls {

    /* ProductRestController URLs */
    public static final String PRODUCT_URL = "/api/v1/product";
    public static final String PRODUCT_BY_ID_URL = "{id}";

}

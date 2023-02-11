package hr.tvz.productservice.model.rest.request;

import hr.tvz.productservice.model.entity.ProductEntity;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.math.BigDecimal;

/**
 * A record representing product DTO request for the {@link ProductEntity} entity.
 */
@Builder
public record ProductRequest(
    @NotBlank(message = "{validation.product.request.name.not-blank}")
    String name,
    @NotBlank(message = "{validation.product.request.description.not-blank}")
    String description,
    @NotNull(message = "{validation.product.request.price.not-null}")
    @DecimalMin(value = "0.0", inclusive = true, message = "{validation.product.request.price.decimal-min}")
    @Digits(integer = 6, fraction = 2, message = "{validation.product.request.price.digits}")
    BigDecimal price
) {
}

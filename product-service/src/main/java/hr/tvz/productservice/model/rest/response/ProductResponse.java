package hr.tvz.productservice.model.rest.response;

import hr.tvz.productservice.model.entity.ProductEntity;
import lombok.Builder;

import java.math.BigDecimal;

/**
 * A record representing product DTO response for the {@link ProductEntity} entity.
 */
@Builder
public record ProductResponse(String id, String name, String description, BigDecimal price) {
}

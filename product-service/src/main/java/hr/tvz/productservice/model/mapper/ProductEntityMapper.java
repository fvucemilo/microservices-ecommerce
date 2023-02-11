package hr.tvz.productservice.model.mapper;

import hr.tvz.productservice.model.entity.ProductEntity;
import hr.tvz.productservice.model.rest.request.ProductRequest;
import hr.tvz.productservice.model.rest.response.ProductResponse;
import org.mapstruct.*;

import java.util.List;

/**
 * An interface for the mapping of {@link ProductEntity}, {@link ProductRequest}, {@link ProductResponse} objects.
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ProductEntityMapper {

    ProductEntity productRequestToProductEntity(ProductRequest productRequest);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ProductEntity updateProductEntityFromProductRequest(ProductRequest productRequest, @MappingTarget ProductEntity productEntity);

    ProductResponse productEntityToProductResponse(ProductEntity productEntity);

    List<ProductResponse> productEntityListToProductResponseList(List<ProductEntity> productEntityList);

}

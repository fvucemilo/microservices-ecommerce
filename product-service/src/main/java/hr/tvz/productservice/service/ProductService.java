package hr.tvz.productservice.service;

import hr.tvz.productservice.exception.ExceptionType;
import hr.tvz.productservice.exception.MessagedException;
import hr.tvz.productservice.model.entity.ProductEntity;
import hr.tvz.productservice.model.mapper.ProductEntityMapper;
import hr.tvz.productservice.model.rest.request.ProductRequest;
import hr.tvz.productservice.model.rest.response.ProductResponse;
import hr.tvz.productservice.model.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * A class representing service for the {@link ProductEntity} entity.
 */
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductEntityMapper productEntityMapper;

    /**
     * Creates new {@link ProductEntity} entity.
     *
     * @param productRequest {@link ProductRequest} JSON request body object
     */
    public void createProduct(ProductRequest productRequest) {
        ProductEntity productEntity = productRepository.save(productEntityMapper.productRequestToProductEntity(productRequest));
        log.info("Product with ID: '{}' is saved", productEntity.getId());
    }

    /**
     * Retrieves all {@link ProductEntity} entities as {@link ProductResponse}.
     *
     * @return JSON response body as list of {@link ProductResponse} objects
     */
    public List<ProductResponse> getAllProducts() {
        return productEntityMapper
            .productEntityListToProductResponseList(productRepository
                .findAll()
            );
    }

    /**
     * Retrieves {@link ProductEntity} entity by id as {@link ProductResponse}.
     *
     * @param id {@link ProductEntity} id
     * @return JSON response body as {@link ProductResponse} object
     */
    public ProductResponse getProductById(String id) {
        return productEntityMapper
            .productEntityToProductResponse(productRepository
                .findById(id)
                .stream()
                .peek(p -> log.info("Found product with ID: '{}'", p.getId()))
                .findFirst()
                .orElseThrow(() -> {
                    log.error("Product with ID: '{}' doesn't exist!", id);
                    throw new MessagedException(ExceptionType.NOT_FOUND, "Product with ID: '" + id + "' doesn't exist!");
                })
            );
    }

    /**
     * Updates {@link ProductEntity} entity with {@link ProductRequest} and returns it as {@link ProductResponse}.
     *
     * @param productRequest {@link ProductRequest} JSON request body object
     * @param id             {@link ProductEntity} id
     * @return JSON respons body as {@link ProductResponse} object
     */
    public ProductResponse updateProductById(ProductRequest productRequest, String id) {
        return productEntityMapper
            .productEntityToProductResponse(productRepository
                .save(productEntityMapper
                    .updateProductEntityFromProductRequest(productRequest, productRepository
                        .findById(id)
                        .orElseThrow(() -> {
                            log.error("Product with ID: '{}' doesn't exist!", id);
                            throw new MessagedException(ExceptionType.NOT_FOUND, "Product with ID: '" + id + "' doesn't exist!");
                        })
                    )
                )
            );
    }

    /**
     * Deletes {@link ProductEntity} entity by id.
     *
     * @param id {@link ProductEntity} id
     */
    public void deleteProductById(String id) {
        productRepository
            .delete(productRepository
                .findById(id)
                .stream()
                .peek(p -> log.info("Product with ID: '{}' is removed", p.getId()))
                .findFirst()
                .orElseThrow(() -> {
                    log.error("Product with ID: '{}' doesn't exist!", id);
                    throw new MessagedException(ExceptionType.NOT_FOUND, "Product with ID: '" + id + "' doesn't exist!");
                })
            );
    }

}

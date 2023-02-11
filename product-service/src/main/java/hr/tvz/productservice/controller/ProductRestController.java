package hr.tvz.productservice.controller;

import hr.tvz.productservice.model.entity.ProductEntity;
import hr.tvz.productservice.model.rest.request.ProductRequest;
import hr.tvz.productservice.model.rest.response.ProductResponse;
import hr.tvz.productservice.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static hr.tvz.productservice.constant.ProductServiceUrls.PRODUCT_BY_ID_URL;
import static hr.tvz.productservice.constant.ProductServiceUrls.PRODUCT_URL;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

/**
 * A class representing REST controller for the {@link ProductEntity} entity.
 */
@RestController
@RequestMapping(PRODUCT_URL)
@RequiredArgsConstructor
public class ProductRestController {

    private final ProductService productService;

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(CREATED)
    public void createProduct(@RequestBody @Valid ProductRequest productRequest) {
        productService.createProduct(productRequest);
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    @ResponseBody
    public List<ProductResponse> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping(value = PRODUCT_BY_ID_URL, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    @ResponseBody
    public ProductResponse getProductById(@PathVariable String id) {
        return productService.getProductById(id);
    }

    @PutMapping(value = PRODUCT_BY_ID_URL, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    @ResponseBody
    public ProductResponse updateProductById(@PathVariable String id, @RequestBody @Valid ProductRequest productRequest) {
        return productService.updateProductById(productRequest, id);
    }

    @DeleteMapping(PRODUCT_BY_ID_URL)
    @ResponseStatus(NO_CONTENT)
    public void deleteProductById(@PathVariable String id) {
        productService.deleteProductById(id);
    }

}

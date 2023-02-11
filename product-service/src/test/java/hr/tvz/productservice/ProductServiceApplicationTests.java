package hr.tvz.productservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import hr.tvz.productservice.model.rest.request.ProductRequest;
import hr.tvz.productservice.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.math.BigDecimal;

import static hr.tvz.productservice.constant.ProductServiceUrls.PRODUCT_URL;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * A test class for product service.
 */
@ActiveProfiles("test")
@AutoConfigureMockMvc
@SpringBootTest
@Testcontainers
class ProductServiceApplicationTests {

    @Container
    static MongoDBContainer mongoDBContainer = new MongoDBContainer(DockerImageName.parse("mongo:4.4.2"));
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private ProductService productService;

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
        dynamicPropertyRegistry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    @Test
    void shouldCreateProduct() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post(PRODUCT_URL)
                .accept(APPLICATION_JSON)
                .contentType(APPLICATION_JSON)
                .content(objectMapper
                    .writeValueAsString(getProductRequest())
                )
            )
            .andExpect(status().isCreated())
            .andExpect(result -> assertEquals(1, productService.getAllProducts().size()));
    }

    @Test
    void shouldFailedCreateProduct() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post(PRODUCT_URL)
                .accept(APPLICATION_JSON)
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(null))
            )
            .andExpect(status().isBadRequest());
    }

    @Test
    void shouldGetAllProducts() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get(PRODUCT_URL)
                .contentType(APPLICATION_JSON)
            )
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.length()").value(productService.getAllProducts().size()));
    }

    @Test
    void shouldUpdateProductById() throws Exception {

    }

    @Test
    void shouldFailedUpdateProductById() throws Exception {

    }

    @Test
    void shouldDeleteProductById() throws Exception {
        ProductRequest productRequest = getProductRequest();

    }

    @Test
    void shouldFailedDeleteProductById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .delete(PRODUCT_URL + "/635c5e0873053848f913f7bf")
            )
            .andExpect(status().isNotFound());
    }

    private ProductRequest getProductRequest() {
        return ProductRequest
            .builder()
            .name("iPhone 13")
            .description("iPhone 13")
            .price(BigDecimal.valueOf(1200))
            .build();
    }

}

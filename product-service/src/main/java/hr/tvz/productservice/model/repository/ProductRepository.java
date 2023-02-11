package hr.tvz.productservice.model.repository;

import hr.tvz.productservice.model.entity.ProductEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

/**
 * An interface for the {@link ProductEntity} entity repository.
 */
public interface ProductRepository extends MongoRepository<ProductEntity, String> {
    Optional<ProductEntity> findById(String id);
}

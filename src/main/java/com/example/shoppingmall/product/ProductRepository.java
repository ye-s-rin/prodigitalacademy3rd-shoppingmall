package com.example.shoppingmall.product;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    Page<Product> findByCategoryId(int categoryId, Pageable pageable);

    Optional<Product> findByProductId(int productId);

    Page<Product> findAll(Pageable pageable);

    void deleteByProductId(int productId);
}
package com.example.shoppingmall.product;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    Page<Product> findByCategoryId(int categoryId, Pageable pageable);

    Optional<Product> findById(int id);

    Page<Product> findAll(Pageable pageable);

    void deleteById(int id);
}
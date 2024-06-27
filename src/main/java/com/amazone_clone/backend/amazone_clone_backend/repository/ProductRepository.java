package com.amazone_clone.backend.amazone_clone_backend.repository;

import com.amazone_clone.backend.amazone_clone_backend.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

}

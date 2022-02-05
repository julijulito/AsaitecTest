package com.example.julian.test.serviceApi.repository;

import com.example.julian.test.serviceApi.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DataEntityRepository extends JpaRepository<Product, Long> {
}

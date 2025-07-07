package com.itachi.DemoSpringBoot.repository;

import com.itachi.DemoSpringBoot.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
}

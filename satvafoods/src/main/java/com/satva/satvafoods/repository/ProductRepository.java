package com.satva.satvafoods.repository;

import com.satva.satvafoods.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
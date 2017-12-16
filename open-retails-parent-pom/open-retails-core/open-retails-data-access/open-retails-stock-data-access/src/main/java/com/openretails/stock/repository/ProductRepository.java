package com.openretails.stock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.openretails.stock.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {


}

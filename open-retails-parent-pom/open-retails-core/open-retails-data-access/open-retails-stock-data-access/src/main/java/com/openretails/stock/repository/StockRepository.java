package com.openretails.stock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.openretails.stock.model.Stock;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {


}

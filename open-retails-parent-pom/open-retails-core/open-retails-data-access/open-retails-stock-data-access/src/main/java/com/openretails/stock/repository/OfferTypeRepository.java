package com.openretails.stock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.openretails.stock.model.OfferType;

@Repository
public interface OfferTypeRepository extends JpaRepository<OfferType, Long> {


}

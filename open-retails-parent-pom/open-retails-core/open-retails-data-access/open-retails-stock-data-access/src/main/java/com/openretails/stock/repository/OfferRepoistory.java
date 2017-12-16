package com.openretails.stock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.openretails.stock.model.Offer;

@Repository
public interface OfferRepoistory extends JpaRepository<Offer, Long> {


}

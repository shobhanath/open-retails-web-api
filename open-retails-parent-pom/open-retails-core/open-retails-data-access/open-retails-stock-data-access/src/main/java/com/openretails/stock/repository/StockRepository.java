package com.openretails.stock.repository;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.openretails.stock.model.Stock;

@Repository
public interface StockRepository extends BaseJpaRepository<Stock, Long> {

	@Query("Select s from Stock s join s.product p where p.identity in (?1)")
	Optional<Stock> findByProductId(Long productId);

	@Query("Select s from Stock s join s.product p where p.obsolete=true and s.obsolete=?2 and p.identity in (?1)")
	Optional<Stock> findByProductIdAndObsolete(Long productId, Boolean flag);

	@Query("Select s from Stock s join s.product p where p.identity in (?1)")
	Optional<Collection<Stock>> findByProductIdIn(Collection<Long> productIds);

	@Query("Select s from Stock s join s.product p where p.obsolete=true and s.obsolete=?2 and p.identity in (?1)")
	Optional<Collection<Stock>> findByProductIdInAndObsolete(Collection<Long> productIds, Boolean flag);

	@Query("Select s.identity from Stock s join s.product p where lower(p.name) = lower(?1)")
	Optional<Long> findByProductName(String name);

	@Query("Select s.identity from Stock s join s.product p where p.obsolete=true and s.obsolete=?2 and lower(p.name) = lower(?1)")
	Optional<Long> findByProductNameAndObsolete(String name, Boolean flag);

	@Query("Select s.identity from Stock s join s.product p where lower(p.name) in (?1)")
	Optional<Collection<Long>> findByProductNameIn(Collection<String> names);

	@Query("Select s.identity from Stock s join s.product p where p.obsolete=true and s.obsolete=?2 and lower(p.name) in (?1)")
	Optional<Collection<Long>> findByProductNameInAndObsolete(Collection<String> names, Boolean flag);

	@Query("Select p.name from Stock s join s.product p where s.identity in (?1)")
	Optional<String> findProductNameById(Long stockIds);

	@Query("Select p.name from Stock s join s.product p where p.obsolete=true and s.obsolete=?2 and s.identity in (?1)")
	Optional<String> findProductNameByIdAndObsolete(Long stockIds, Boolean flag);

	@Query("Select p.name from Stock s join s.product p where s.identity in (?1)")
	Optional<Collection<String>> findProductNameByIdIn(Collection<Long> stockIds);

	@Query("Select p.name from Stock s join s.product p where p.obsolete=true and s.obsolete=?2 and s.identity in (?1)")
	Optional<Collection<String>> findProductNameByIdInAndObsolete(Collection<Long> stockIds, Boolean flag);

}

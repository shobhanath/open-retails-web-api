package com.openretails.stock.repository;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.openretails.stock.model.Product;

@Repository
public interface ProductRepository extends BaseJpaRepository<Product, Long> {

	Optional<Product> findByNameIgnoreCase(String name);

	Optional<Product> findByNameIgnoreCaseAndObsolete(String name, boolean active);

	Optional<Collection<Product>> findByNameIgnoreCaseContainingOrIdentityAndObsoleteTrue(String names, Long identity);

	Optional<Collection<Product>> findByNameIgnoreCaseIn(Collection<String> names);

	Optional<Collection<Product>> findByNameIgnoreCaseInAndObsolete(Collection<String> names, boolean active);

	@Query("Select pc.identity from Product pc where lower(pc.name) in (lower(?1))")
	Optional<Long> getByNameIgnoreCase(String name);

	@Query("Select pc.identity from Product pc where pc.obsolete=?2 and lower(pc.name) in (lower(?1))")
	Optional<Long> getByNameIgnoreCaseAndObsolete(String name, boolean active);

	@Query("Select pc.identity from Product pc where lower(pc.name) in (lower(?1))")
	Optional<Collection<Long>> getByNameIgnoreCaseIn(Collection<String> names);

	@Query("Select pc.identity from Product pc where pc.obsolete=?2 and lower(pc.name) in (lower(?1))")
	Optional<Collection<Long>> getByNameIgnoreCaseInAndObsolete(Collection<String> names, boolean active);

	@Query("Select pc.name from Product pc where pc.identity in (?1)")
	Optional<String> getNameById(Long identity);

	@Query("Select pc.name from Product pc where pc.obsolete=?2 and pc.identity in (?1)")
	Optional<String> getNameByIdAndObsolete(Long identity, boolean active);

	@Query("Select pc.name from Product pc where pc.identity in (?1)")
	Optional<Collection<String>> getNameByIdIn(Collection<Long> identities);

	@Query("Select pc.name from Product pc where pc.obsolete=?2 and pc.identity in (?1)")
	Optional<Collection<String>> getNameByIdInAndObsolete(Collection<Long> identities, boolean active);

}

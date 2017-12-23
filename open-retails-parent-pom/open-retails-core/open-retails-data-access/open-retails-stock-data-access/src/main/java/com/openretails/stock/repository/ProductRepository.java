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

	Optional<Collection<Product>> findByNameIgnoreCaseIn(Collection<String> names);

	Optional<Collection<Product>> findByNameIgnoreCaseInAndObsolete(Collection<String> names, boolean active);

	@Query("Select p from Product p where p.obsolete=true and (lower(p.name) like %?1% or lower(p.barCode) like %?1% or p.identity = ?2)")
	Optional<Collection<Product>> findByObsoleteTrueAndNameIgnoreCaseContainingOrIdentity(String name, Long identity);

	@Query("Select p.identity from Product p where lower(p.name) in (lower(?1))")
	Optional<Long> getByNameIgnoreCase(String name);

	@Query("Select p.identity from Product p where p.obsolete=?2 and lower(p.name) in (lower(?1))")
	Optional<Long> getByNameIgnoreCaseAndObsolete(String name, boolean active);

	@Query("Select p.identity from Product p where lower(p.name) in (lower(?1))")
	Optional<Collection<Long>> getByNameIgnoreCaseIn(Collection<String> names);

	@Query("Select p.identity from Product p where p.obsolete=?2 and lower(p.name) in (lower(?1))")
	Optional<Collection<Long>> getByNameIgnoreCaseInAndObsolete(Collection<String> name, boolean active);

	@Query("Select p.name from Product p where p.identity in (?1)")
	Optional<String> getNameById(Long identity);

	@Query("Select p.name from Product p where p.obsolete=?2 and p.identity in (?1)")
	Optional<String> getNameByIdAndObsolete(Long identity, boolean active);

	@Query("Select p.name from Product p where p.identity in (?1)")
	Optional<Collection<String>> getNameByIdIn(Collection<Long> identities);

	@Query("Select p.name from Product p where p.obsolete=?2 and p.identity in (?1)")
	Optional<Collection<String>> getNameByIdInAndObsolete(Collection<Long> identities, boolean active);

}

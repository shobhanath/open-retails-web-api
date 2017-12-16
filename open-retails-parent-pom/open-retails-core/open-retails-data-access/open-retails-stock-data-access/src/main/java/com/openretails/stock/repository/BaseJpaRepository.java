package com.openretails.stock.repository;

import java.io.Serializable;
import java.util.Collection;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseJpaRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {

	Optional<T> findByIdentity(Long identity);

	Optional<T> findByIdentityAndObsolete(Long identity, boolean active);

	Optional<Collection<T>> findByIdentityIn(Collection<Long> identities);

	Optional<Collection<T>> findByIdentityInAndObsolete(Collection<Long> identities, boolean active);

	Optional<Collection<T>> findByObsolete(boolean active);
}

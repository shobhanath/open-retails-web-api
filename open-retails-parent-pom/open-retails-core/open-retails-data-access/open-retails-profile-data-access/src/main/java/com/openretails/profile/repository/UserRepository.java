package com.openretails.profile.repository;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.openretails.profile.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	@Query("Select u from User u where u.obsolete=true")
	Collection<User> findAllObsoleteTrue();

	Optional<User> findByIdentityAndObsoleteTrue(Long identity);

	Optional<User> findByUsernameOrPrimaryEmailIdAndObsoleteTrue(String name, String primaryEmail);

}

package com.openretails.profile.repository;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.openretails.profile.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	@Query("Select u from User u where u.obsolete=?1")
	Optional<Collection<User>> findAll(boolean flag);

	@Query("Select u from User u where u.obsolete=?1 and u.identity in (?2)")
	Optional<Collection<User>> findByIdentity(boolean flag,Collection<Long> identities);

	@Query("Select u from User u where u.obsolete=?1 and u.identity =?2")
	Optional<User> findByIdentity(boolean flag,Long identity);

	@Query("Select u from User u where u.identity in (?1)")
	Optional<Collection<User>> findByIdentity(Collection<Long> identities);

	@Query("Select u from User u where u.identity in (?1)")
	Optional<User> findByIdentity(Long identity);

	Optional<User> findByIdentityAndObsoleteTrue(Long identity);

	Optional<Collection<User>> findByUsernameOrPrimaryEmailId(Collection<String> names,
			Collection<String> primaryEmails);

	Optional<User> findByUsernameOrPrimaryEmailId(String name, String primaryEmail);

	@Query("Select u from User u where u.obsolete=?1 and lower(u.username) in (?2) or lower(u.primaryEmailId) in (?3)")
	Optional<Collection<User>> findByUsernameOrPrimaryEmailIdAndObsolete(boolean flag, Collection<String> names,
			Collection<String> primaryEmails);

	@Query("Select u from User u where u.obsolete=?1 and lower(u.username) in (?2) or lower(u.primaryEmailId) in (?3)")
	Optional<User> findByUsernameOrPrimaryEmailIdAndObsolete(boolean flag, String name,
			String primaryEmail);

	@Query("Select u from User u")
	Optional<Collection<User>> getAll();

}

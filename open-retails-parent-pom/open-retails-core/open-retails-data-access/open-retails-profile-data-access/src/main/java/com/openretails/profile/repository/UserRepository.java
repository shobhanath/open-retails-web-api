package com.openretails.profile.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.openretails.profile.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findByIdentityAndObsoleteTrue(Long identity);

	User findByUsername(String name);

}

package com.openretails.profile.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.openretails.profile.model.UserType;

@Repository
public interface UserTypeRepository extends JpaRepository<UserType, Long> {

	UserType findByIdentityAndObsoleteTrue(Long identity);

}

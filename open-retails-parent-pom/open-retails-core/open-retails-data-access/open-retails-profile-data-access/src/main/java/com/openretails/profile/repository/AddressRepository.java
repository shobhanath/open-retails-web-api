package com.openretails.profile.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.openretails.profile.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long>{}

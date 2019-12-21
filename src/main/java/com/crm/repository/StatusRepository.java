package com.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crm.entity.Status;
@Repository
public interface StatusRepository extends JpaRepository<Status, String>{
	Status findByName(String name);
}

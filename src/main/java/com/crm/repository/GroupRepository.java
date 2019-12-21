package com.crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crm.entity.Group;
@Repository
public interface GroupRepository extends JpaRepository<Group,String>{
	Group findByName(String name);
}

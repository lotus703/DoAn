package com.crm.repository;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.crm.dto.UserDTO;
import com.crm.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
	User findByEmail(String email);
	
	@Query("SELECT new com.crm.dto.UserDTO(u.id, u.email, u.fullname, u.address, u.phone, u.roleId, r.description) FROM User u JOIN u.role r")
	List<UserDTO> findAllUserRole();
	
	@Query("SELECT u.password FROM User u where u.id = :id") 
    String findPasswordById(@Param("id") String id);
	
	@Query("SELECT u FROM User u WHERE u.fullname = :fullname")
	Stream<User> findByFullname(String fullname);
	
	
	
}

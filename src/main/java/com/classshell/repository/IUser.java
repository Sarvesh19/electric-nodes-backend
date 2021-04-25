package com.classshell.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.classshell.dto.User;
@Repository
public interface IUser extends MongoRepository<User, Integer> {
	
	@Query("{ 'email' : ?0 }")
	Optional<User> findEmployeeByUserNameNative(String username);
}
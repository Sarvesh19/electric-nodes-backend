package com.electricnodes.repository;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.electricnodes.dto.User;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private IUser userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	Optional<User> user = userRepo.findEmployeeByUserNameNative(username);
    	User userEntity= null;
    	if(user.isPresent()) {
    		userEntity = user.get();
    	}
        return new org.springframework.security.core.userdetails.User(userEntity.getEmail(), userEntity.getPassword(), new ArrayList<>());
    }
    
    
   
}
package com.hcl.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.user.dto.Credentials;
import com.hcl.user.exception.InvalidCredentialsException;
import com.hcl.user.model.User;
import com.hcl.user.repository.UserRepository;
import com.hcl.user.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public String login(Credentials credentials) throws InvalidCredentialsException {
		User user=userRepository.findByUserNameAndPassword(credentials.getUserName(),credentials.getPassword());
		if(user==null) {
			throw new InvalidCredentialsException("Invalid Credentials");
		}
		return "Login Success";
	}

}

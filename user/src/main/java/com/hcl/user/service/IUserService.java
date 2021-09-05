package com.hcl.user.service;

import com.hcl.user.dto.Credentials;
import com.hcl.user.exception.InvalidCredentialsException;

public interface IUserService {
	
	public String login(Credentials credentials) throws InvalidCredentialsException;

}

package com.hcl.user.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

import com.hcl.user.dto.Credentials;
import com.hcl.user.exception.InvalidCredentialsException;
import com.hcl.user.service.IUserService;

@RestController
public class UserController {	
	
	@Autowired
	IUserService iUserService;
	
	@PostMapping("/users/login")
	public ResponseEntity<String> login(@Valid @RequestBody Credentials credentials)  throws InvalidCredentialsException {
		 return new ResponseEntity<String>(iUserService.login(credentials), HttpStatus.OK);
	}
	
}

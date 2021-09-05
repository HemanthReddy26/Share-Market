package com.hcl.management.feignclient;

import javax.validation.Valid;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.hcl.user.dto.Credentials;
import com.hcl.user.exception.InvalidCredentialsException;

@FeignClient(name="userservice",url="http://localhost:8090/")
public interface UserConnect {
	
	@PostMapping("/users/login")
	public ResponseEntity<String> login(@Valid @RequestBody Credentials credentials)  throws InvalidCredentialsException;

}

package com.smartarch.message.test;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="user-service")
public interface UserService {
	@GetMapping("/users/")
	Response getUserById(@RequestParam("id") String id);
}

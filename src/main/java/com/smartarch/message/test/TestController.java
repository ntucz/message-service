package com.smartarch.message.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
	@Autowired
    private UserService userService;
	
    @GetMapping("/")
    public Response testQuery(@RequestParam("id") String id) {
    	Response res = userService.getUserById(id);
    	return res;
	}
}

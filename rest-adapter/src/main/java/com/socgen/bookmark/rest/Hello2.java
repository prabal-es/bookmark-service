package com.socgen.bookmark.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/hello2")
public class Hello2 {
	
	@GetMapping
	public String hello() {
		return "hello";
	}

}

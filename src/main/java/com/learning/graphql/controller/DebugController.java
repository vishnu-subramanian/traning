package com.learning.graphql.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.graphql.service.DebugService;

@RestController
@RequestMapping("/api/java")
public class DebugController {
	
	@Autowired
	DebugService service;
	
	@GetMapping("/test")
	private void testController() {
		System.out.println("Test Controller!!");
	}
	
	@PostMapping("/save")
	private ResponseEntity<?> compileFile(@RequestBody String code) throws IOException {
		System.out.println("Test Controller!!");
		service.saveFile(code);
		
		return  ResponseEntity.ok().body("success!!");
		
	}

	
	@PostMapping("/run")
	private ResponseEntity<?> run(@RequestBody String code) throws IOException {
		System.out.println("Test Controller!!");
		return ResponseEntity.ok().body(service.run("test"));
	}
	
	@PostMapping("/compile")
	private ResponseEntity<?> compile(@RequestBody String code) throws IOException {
		System.out.println("Test Controller!!");
		return ResponseEntity.ok().body(service.compile("test", "test"));
	}
}

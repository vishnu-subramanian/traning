package com.learning.graphql.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.graphql.service.PythonService;

@RestController
@RequestMapping("/api/python")
public class PythonController {
	
	@Autowired
	PythonService service;
	
	@PostMapping("/save")
	private ResponseEntity<?> compileFile(@RequestBody String code) throws IOException {
		service.saveFile(code);
		return  ResponseEntity.ok().body("success!!");
	}

	
	@PostMapping("/run")
	private ResponseEntity<?> run(@RequestBody String code) throws IOException {
		System.out.println("Test Controller!!");
		return ResponseEntity.ok().body(service.compile("test","test"));
	}

}

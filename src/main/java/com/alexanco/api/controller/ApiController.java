package com.alexanco.api.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class ApiController {


    @Value("${pom.version}")
	private String versionProject;

	@GetMapping("/version")
	public ResponseEntity<HashMap<String, String>> versionCheck() {
		HashMap<String, String> response = new HashMap<String, String>();

		response.put("project", "PARSER-ISO8583");
		response.put("version", versionProject);
		return ResponseEntity.ok(response);
	}


    @GetMapping("/parser")
    public String parser() {
        return "Hello, World!";
    }
}
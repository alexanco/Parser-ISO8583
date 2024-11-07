package com.alexanco.api.routes;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alexanco.api.controller.ApiController;


@RestController
@RequestMapping("/api")
public class ApiRoutes {

	@Autowired
	private ApiController controller;

    @Value("${pom.version}")
	private String versionProject;


	@GetMapping("/version")
	public ResponseEntity<HashMap<String, String>> versionCheck() {

		HashMap<String, String> response = new HashMap<String, String>();
		response.put("project", "PARSER-ISO8583");
		response.put("version", versionProject);
		return ResponseEntity.ok(response);
	}


	@PostMapping("/parser")
	public ResponseEntity<HashMap<Integer, String>> parseISO(
		@RequestBody HashMap<String, String> payload,
		@RequestParam(name = "type", required = true) String type
	) {
		return ResponseEntity.ok(controller.parser(payload.get("iso"), type));
	}
}
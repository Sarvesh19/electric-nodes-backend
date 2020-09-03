package com.classshell.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class UserController {

	@RequestMapping(value = "test", method = RequestMethod.GET)
	public ResponseEntity<Map<String, String>> welcome() {
		Map<String, String> test = new HashMap<>();
		test.put("data", "hello class shell App");

		return new ResponseEntity<Map<String, String>>(test, new HttpHeaders(), HttpStatus.OK);
	}

}

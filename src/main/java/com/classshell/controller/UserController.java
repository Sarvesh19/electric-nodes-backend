package com.classshell.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.classshell.dto.User;
import com.classshell.repository.IUser;
import com.classshell.repository.UserService;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.MongoCollection;

@RestController
@CrossOrigin(origins = "https://electricnodes.com, www.electricnodes.com")
@RequestMapping("/")
public class UserController {

	@Autowired
	private UserService userService;

	private static LinkedList<String> queue = new LinkedList<>();

	@RequestMapping(value = "test", method = RequestMethod.GET)
	public ResponseEntity<Map<String, String>> welcome() {
		Map<String, String> test = new HashMap<>();
		test.put("data", "hello class shell App");

		User s = userService.getUser();
		System.out.println(s);

		return new ResponseEntity<Map<String, String>>(test, new HttpHeaders(), HttpStatus.OK);
	}
	
	
	@PostMapping(path = "signup", consumes = "application/json", produces = "application/json")

	public ResponseEntity<User> registerUser(@RequestBody User userDto) throws Exception {

		User user = userService.saveUser(userDto);
		user.setId(user.getUserid().get().toString());
		if (user == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found", new Exception());
		}

//		String token = jwtUtil.generateToken(user.getEmail());
//		user.setToken(token);

		return new ResponseEntity<User>(user, new HttpHeaders(), HttpStatus.OK);

	}
	
	@PostMapping(path = "login", consumes = "application/json", produces = "application/json")
	public ResponseEntity<User> loginUser(@RequestBody com.classshell.dto.UserNamePassword userDto) throws Exception {

		User user = userService.loginUser(userDto);
//		logger.info("hello info.....!!!!!");
//		String token = jwtUtil.generateToken(user.getEmail());
//		user.setToken(token);
		
		
		return new ResponseEntity<User>(user, new HttpHeaders(), HttpStatus.OK);

	}

//	@RequestMapping(value = "gettoken", method = RequestMethod.POST)
//	public ResponseEntity<Map<String, String>> getToken(@RequestBody UserDto userDto) {
//
//		System.out.println(userDto);
//
//		queue.add(userDto.getEmailId() + "_" + userDto.getMobileNumber());
//
//		Map<String, String> token = new HashMap<>();
//		token.put("token", userDto.getEmailId() + "_" + userDto.getMobileNumber());
//
//		return new ResponseEntity<Map<String, String>>(token, new HttpHeaders(), HttpStatus.OK);
//	}

	@GetMapping("/waitingtime/{tokenuser}")
	public ResponseEntity<Map<String, Integer>> getWaitingTime(@PathVariable String tokenuser) {

		int count = 0;
		// int waitingTime = 0;
		// System.out.println(userDto);
		for (int i = 0; i < queue.size(); i++) {
			if (tokenuser.equals(queue.get(i)) && count <= 4) {
				count = 0;
				break;
			}
			count++;
			if (tokenuser.equals(queue.get(i)) && count > 4) {
				count = count * 15;
				break;
			}
		}
		Map<String, Integer> tokenTime = new HashMap<>();

		tokenTime.put(tokenuser, count);

		return new ResponseEntity<Map<String, Integer>>(tokenTime, new HttpHeaders(), HttpStatus.OK);
	}

}

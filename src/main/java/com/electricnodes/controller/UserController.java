package com.electricnodes.controller;

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
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.electricnodes.dto.User;
import com.electricnodes.dto.UserNamePassword;
import com.electricnodes.repository.IUser;
import com.electricnodes.repository.UserService;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.MongoCollection;

@RestController
@CrossOrigin(origins = { "https://electricnodes.com","https://electric-nodes-backend.herokuapp.com", "http://localhost:4200" })
@RequestMapping("/")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private JavaMailSender mailSender;

	private static LinkedList<String> queue = new LinkedList<>();

	@RequestMapping(value = "test", method = RequestMethod.GET)
	public ResponseEntity<Map<String, String>> welcome() {
		Map<String, String> test = new HashMap<>();
		test.put("data", "Electric Nodes");
		String toSend = "";
//		String verificationCode = "DRFD";
//		SimpleMailMessage message = new SimpleMailMessage();
//		message.setFrom("support@electricnodes.com");
//		message.setTo("sarvesh.y305@gmail.com");// passing array of recipients
//		message.setSubject("Confirmation Code");
//		message.setText("Hi, \\r\\n" + "We just need to verify your email address before you can access.\\r\\n"
//				+ verificationCode + "Thanks! – ElectricNodes.com team\n" + "");
//		// sending message
//		mailSender.send(message);

		User s = userService.getUser();
		System.out.println(s);

		return new ResponseEntity<Map<String, String>>(test, new HttpHeaders(), HttpStatus.OK);
	}

	@PostMapping(path = "signup", consumes = "application/json", produces = "application/json")

	public ResponseEntity<User> registerUser(@RequestBody User userDto) throws Exception {

		User user = userService.saveUser(userDto);
		user.setId(user.getUserid().get().toString());

//		String token = jwtUtil.generateToken(user.getEmail());
//		user.setToken(token);

		return new ResponseEntity<User>(user, new HttpHeaders(), HttpStatus.OK);

	}
	
	//@PostMapping(path = "confirmmail", consumes = "application/json", produces = "application/json")

//	public ResponseEntity<User> confirmMail(@RequestBody UserNamePassword userDto) throws Exception {

	//	User user = userService.confirmUser(userDto);
		
		//user.setId(user.getUserid().get().toString());


		//return new ResponseEntity<User>(user, new HttpHeaders(), HttpStatus.OK);

//	}

	@PostMapping(path = "login", consumes = "application/json", produces = "application/json")
	public ResponseEntity<User> loginUser(@RequestBody com.electricnodes.dto.UserNamePassword userDto) throws Exception {

		User user = userService.loginUser(userDto);

		return new ResponseEntity<User>(user, new HttpHeaders(), HttpStatus.OK);

	}
	
	@GetMapping("profile")
	public ResponseEntity<String> getProfile() {

		
		
		return new ResponseEntity<String>("Welcome", new HttpHeaders(), HttpStatus.OK);
	}
	
	



}

package com.electricnodes.repository;

import java.util.Optional;
import java.util.Random;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.electricnodes.config.JwtUtil;
import com.electricnodes.dto.Inspections;
import com.electricnodes.dto.Post;
import com.electricnodes.dto.User;
import com.electricnodes.dto.UserNamePassword;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.IndexOptions;

@Service
public class UserService {
	@Autowired
	private IUser userRepo;

	@Autowired
	MongoTemplate mongoTemplate;

	@Autowired
	IPost postRepo;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private JwtUtil jwtUtil;

	public User getUser() {

		MongoCollection<org.bson.Document> collection = mongoTemplate.getCollection("user");

		Query query1 = new Query();
		query1.addCriteria(Criteria.where("userId").is(new ObjectId("60844aef41ef803f4ecf1425")));
		User p = mongoTemplate.findOne(query1, User.class);

		userRepo.findAll().stream().forEach(x -> {
			System.out.println(x.getFirstname());
		});

		postRepo.findAll().stream().forEach(x -> {
			System.out.println(x.getCity());
		});
		// Optional<Post> post = postRepo.findById(1);
		// System.out.println(post.get().getCountry());

		System.out.println(userRepo.existsById(2));
		System.out.println(userRepo.existsById(4));

		System.out.println(p);
		return p;
	}

	public User saveUser(User user) throws Exception {
		Optional<User> user1 = userRepo.findEmployeeByUserNameNative(user.getEmail());
		if (user1.isPresent()) {
			throw new Exception("No User record exist for given username");
		}
		Random rand = new Random();

		String verificationCode = String.format("%04d", rand.nextInt(10000));

		try {
			this.emailVerfification(user,verificationCode);

		} catch (Exception e) {
			// TODO: handle exception
		}
		User user_ = new User();
		user_.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user_.setEmail(user.getEmail());
		user_.setFirstname(user.getFirstname());
		user_.setIsproducer(user.getIsproducer());
		user_.setIsconcumer(user.getIsconcumer());
		user_.setConfirmCode(verificationCode);
		
		
		user_.setEmailConfirm(false);

		return userRepo.save(user_);
	}

	public User confirmUser(UserNamePassword val) throws Exception {
		Optional<User> user1 = userRepo.findEmployeeByUserNameNative(val.getUsername());
		if (!user1.isPresent()) {
			throw new Exception("No User record exist for given username");
		}
		boolean valid = false;
		if (user1.get().getConfirmCode().equals(val.getPassword())) {
			valid = true;
		}
		if (valid) {
			
			String token = jwtUtil.generateToken(user1.get().getEmail());
			user1.get().setToken(token);
			user1.get().setEmailConfirm(true);
			
			return user1.get();

		}
		return null;

	}

	public User loginUser(UserNamePassword val) throws Exception {

		Optional<User> user = userRepo.findEmployeeByUserNameNative(val.getUsername());

		if (user.isPresent() && userPasswordCheck(val.getPassword(), user.get())) {
			String token = jwtUtil.generateToken(user.get().getEmail());

			User user_ = new User();
			// user_.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
			user_.setId(user.get().getUserid().get().toString());
			user_.setEmail(user.get().getEmail());
			user_.setFirstname(user.get().getFirstname());
			user_.setIsproducer(user.get().getIsproducer());
			user_.setIsconcumer(user.get().getIsconcumer());
			user_.setToken(token);
			// return userRepo.save(user_);
			return user_;
		} else {
			throw new Exception("No User record exist for given username");

		}

	}

	public boolean userPasswordCheck(String password, User user) {

		// PasswordEncoder passencoder = new BCryptPasswordEncoder();
		String encodedPassword = user.getPassword();
		return passwordEncoder.matches(password, encodedPassword);
	}

	private boolean emailVerfification(User user, String verificationCode) {

		try {
			//String token = jwtUtil.generateToken(user.getEmail());
			//String verificationCode = token;

			

			SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom("support@electricnodes.com");
			message.setTo(user.getEmail());// passing array of recipients
			message.setSubject("Confirmation Code");
			message.setText("Hi, " + "Confirmation Code : "
					+ verificationCode );
			// sending message
			mailSender.send(message);

			return true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;

	}

}

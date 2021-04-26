package com.classshell.repository;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.classshell.dto.Inspections;
import com.classshell.dto.Post;
import com.classshell.dto.User;
import com.classshell.dto.UserNamePassword;
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

	public User saveUser(User user) throws Exception{
		Optional<User> user1 = userRepo.findEmployeeByUserNameNative(user.getEmail());
		if(user1.isPresent()) {
			throw new Exception("No User record exist for given username");
		}
		User user_ = new User();
		//user_.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user_.setEmail(user.getEmail());
		user_.setFirstname(user.getFirstname());
		user_.setIsproducer(user.getIsproducer());
		user_.setIsconcumer(user.getIsconcumer());
		return userRepo.save(user_);
	}
	public User loginUser(UserNamePassword val) throws Exception {

		Optional<User> user = userRepo.findEmployeeByUserNameNative(val.getUsername());
		
		if (user.isPresent() && userPasswordCheck(val.getPassword(), user.get())) {
			//;
			User user_ = new User();
			//user_.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
			user_.setId(user.get().getId());
			user_.setEmail(user.get().getEmail());
			user_.setFirstname(user.get().getFirstname());
			user_.setIsproducer(user.get().getIsproducer());
			user_.setIsconcumer(user.get().getIsconcumer());
			//return userRepo.save(user_);
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

//	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//
//	    User user = userRepository.findByEmail(email);
//	    if(user != null) {
//	        List<GrantedAuthority> authorities = getUserAuthority(user.getRoles());
//	        return buildUserForAuthentication(user, authorities);
//	    } else {
//	        throw new UsernameNotFoundException("username not found");
//	    }
//	}

//	public void createUniqueIndex() {
//	    Document index = new Document("field", 1);
//	    MongoCollection<org.bson.Document> collection = mongoTemplate.getCollection("Collection");
//	    collection.createIndex(index, new IndexOptions().unique(true));
//	}
}

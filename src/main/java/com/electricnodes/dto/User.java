package com.electricnodes.dto;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection = "user")

public class User {
	@Id
    private ObjectId userId;
    private boolean isConsumer;
    private boolean isProducer;
    private String firstName;
    private String lastName;
    private String address;
    private String password;
    private String email;
    private String phone;
    private List<Post> post;
    private boolean isEmailConfirm;
    private String confirmCode;
    
    private String id;
    
    private String token;
    
    
   

  

	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public boolean isEmailConfirm() {
		return isEmailConfirm;
	}
	public void setEmailConfirm(boolean isEmailConfirm) {
		this.isEmailConfirm = isEmailConfirm;
	}
	public String getConfirmCode() {
		return confirmCode;
	}
	public void setConfirmCode(String confirmCode) {
		this.confirmCode = confirmCode;
	}
	public void setUserid(ObjectId userid) {
         this.userId = userid;
     }
     public ObjectId getUserid() {
         return userId;
     }

    public void setIsconcumer(boolean isconcumer) {
         this.isConsumer = isconcumer;
     }
     public boolean getIsconcumer() {
         return isConsumer;
     }

    public void setIsproducer(boolean isproducer) {
         this.isProducer = isproducer;
     }
     public boolean getIsproducer() {
         return isProducer;
     }

    public void setFirstname(String firstname) {
         this.firstName = firstname;
     }
     public String getFirstname() {
         return firstName;
     }

    public void setLastname(String lastname) {
         this.lastName = lastname;
     }
     public String getLastname() {
         return lastName;
     }

    public void setAddress(String address) {
         this.address = address;
     }
     public String getAddress() {
         return address;
     }

    public void setPassword(String password) {
         this.password = password;
     }
     public String getPassword() {
         return password;
     }

    public void setEmail(String email) {
         this.email = email;
     }
     public String getEmail() {
         return email;
     }

    public void setPhone(String phone) {
         this.phone = phone;
     }
     public String getPhone() {
         return phone;
     }

    public void setPost(List<Post> post) {
         this.post = post;
     }
     public List<Post> getPost() {
         return post;
     }
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
     
     

}

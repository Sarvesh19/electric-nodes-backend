package com.classshell.dto;

/* Copyright 2021 freecodeformat.com */
import java.util.List;
import java.time.ZonedDateTime;
import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document(collection = "post")
public class Post {
	@Id
	private ObjectId postId;
    private String longitude;
    private String latitude;
    private String address;
    private String city;
    private String country;
    private ZonedDateTime timeavalend;
    private ZonedDateTime timeavalstart;
    private Integer producerId;
    private Date dateend;
    private Date datestart;
   

    public void setLongitude(String longitude) {
         this.longitude = longitude;
     }
     public String getLongitude() {
         return longitude;
     }

    public void setLatitude(String latitude) {
         this.latitude = latitude;
     }
     public String getLatitude() {
         return latitude;
     }

    public void setAddress(String address) {
         this.address = address;
     }
     public String getAddress() {
         return address;
     }

    public void setCity(String city) {
         this.city = city;
     }
     public String getCity() {
         return city;
     }

    public void setCountry(String country) {
         this.country = country;
     }
     public String getCountry() {
         return country;
     }
	public ZonedDateTime getTimeavalend() {
		return timeavalend;
	}
	public void setTimeavalend(ZonedDateTime timeavalend) {
		this.timeavalend = timeavalend;
	}
	public ZonedDateTime getTimeavalstart() {
		return timeavalstart;
	}
	public void setTimeavalstart(ZonedDateTime timeavalstart) {
		this.timeavalstart = timeavalstart;
	}
	public Integer getProducerid() {
		return producerId;
	}
	public void setProducerid(Integer producerid) {
		this.producerId = producerid;
	}
	public Date getDateend() {
		return dateend;
	}
	public void setDateend(Date dateend) {
		this.dateend = dateend;
	}
	public Date getDatestart() {
		return datestart;
	}
	public void setDatestart(Date datestart) {
		this.datestart = datestart;
	}
	public ObjectId getPostid() {
		return postId;
	}
	public void setPostid(ObjectId postId) {
		this.postId = postId;
	}


}
package com.classshell.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.classshell.dto.Post;

@Repository
public interface IPost extends MongoRepository<Post, Integer> {
}
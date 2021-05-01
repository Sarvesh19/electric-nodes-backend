package com.electricnodes.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.electricnodes.dto.Post;

@Repository
public interface IPost extends MongoRepository<Post, Integer> {
}
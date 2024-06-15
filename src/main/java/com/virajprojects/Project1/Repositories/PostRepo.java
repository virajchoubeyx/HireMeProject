package com.virajprojects.Project1.Repositories;

import com.virajprojects.Project1.Model.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostRepo extends MongoRepository<Post,String> {

}

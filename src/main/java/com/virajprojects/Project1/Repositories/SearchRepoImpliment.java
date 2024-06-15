package com.virajprojects.Project1.Repositories;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.virajprojects.Project1.Model.Post;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class SearchRepoImpliment implements SearchRepo {

    @Autowired
    MongoClient client;

    @Autowired
    MongoConverter converter;

    @Override
    public List<Post> searchByText(String text) {
        List<Post> list = new ArrayList<>();

//      -- pipelined code here --
        MongoDatabase database = client.getDatabase("virajdb");
        MongoCollection<Document> collection = database.getCollection("JobPosts");
        AggregateIterable<Document> result = collection.aggregate(Arrays.asList(new Document("$search",
                        new Document("text", new Document("query", text).append("path", Arrays.asList("desc", "profile", "techs")))), new Document("$sort", new Document("exp", 1L)), new Document("$limit", 5L)));
//       -- end --

        result.forEach(document -> list.add(converter.read(Post.class, document)));

        return list;
    }
}

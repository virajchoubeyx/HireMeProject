package com.virajprojects.Project1.Repositories;

import com.virajprojects.Project1.Model.Post;

import java.util.List;

public interface SearchRepo {
    List<Post> searchByText(String text);
}

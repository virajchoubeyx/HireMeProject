package com.virajprojects.Project1.Controller;

import com.virajprojects.Project1.Model.Post;
import com.virajprojects.Project1.Repositories.PostRepo;
import com.virajprojects.Project1.Repositories.SearchRepo;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class PostController {

    @Autowired
    PostRepo repo;

    @Autowired
    SearchRepo s_repo;

    @Operation(hidden = true)
    @RequestMapping(value="/")
    public void redirect(HttpServletResponse response) throws IOException {
            response.sendRedirect("/swagger-ui.html");
    }

    @CrossOrigin
    @GetMapping("/posts")
    public List<Post> getAllPosts(){
        return repo.findAll();
    }

    @CrossOrigin
    @GetMapping("/posts/{text}")
    public List<Post> search(@PathVariable String text){
        return s_repo.searchByText(text);
    }

    @CrossOrigin
    @PostMapping("/post")
    public Post addPost(@RequestBody Post post){
        return repo.save(post);
    }

}

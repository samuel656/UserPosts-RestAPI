package com.webservices.restful_web_services.controller;

import com.webservices.restful_web_services.exception.UserNotFoundException;
import com.webservices.restful_web_services.model.Post;
import com.webservices.restful_web_services.model.User;
import com.webservices.restful_web_services.repository.PostRepository;
import com.webservices.restful_web_services.repository.UserRepository;
import com.webservices.restful_web_services.service.UserDaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class PostResourceJpa {
    // for posts
    private UserRepository userRepository;
    private PostRepository postRepository;

    public PostResourceJpa(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @PostMapping("users-jpa/{id}/create-post")
    public ResponseEntity<Post> createPost(@RequestBody Post post, @PathVariable int id)
    {
        User user=userRepository.findById(id).get();
        post.setUser(user);
        Post savedPost=postRepository.save(post);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(savedPost.getId())
                .toUri();

        //return url of created resource
        return ResponseEntity.created(location).build();

    }

    @GetMapping("users-jpa/{id}/list-posts")
    public List<Post> retrievePostsOfUser(@PathVariable int id)
    {

        User user= userRepository.findById(id).get();
        if(user==null)
            throw new UserNotFoundException("id :"+id);

       return user.getPosts();
    }

    @PatchMapping("/users-jpa/{id}/posts/{pid}")
    public void updatePostById(@RequestBody Post post,@PathVariable int pid,
        @PathVariable int id)
    {
        Post post1=postRepository.findById(pid).get();
        post1.setDescription(post.getDescription());
        postRepository.save (post1);

    }

    @DeleteMapping("users-jpa/{id}/delete-post")
    public void DeletePostById(@PathVariable int id)
    {
        postRepository.deleteById(id);
    }


}

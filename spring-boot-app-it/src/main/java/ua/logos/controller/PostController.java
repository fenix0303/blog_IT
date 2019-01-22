package ua.logos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.logos.domain.PostDTO;
import ua.logos.entity.PostEntity;
import ua.logos.service.PostService;


import java.util.List;

@RestController

public class PostController {


    @Autowired
    private PostService postService;

    @PostMapping("/posts")
    public ResponseEntity<?> createPost(@RequestBody PostDTO post) {
        PostDTO postDTO = postService.savePost(post);
        return new ResponseEntity<>("Post added", HttpStatus.CREATED);
    }

    @GetMapping("/posts")
    public ResponseEntity<?> getPosts() {
        List<PostDTO> posts = postService.findAll();
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<?> getPostsById(@PathVariable("postId") Long id) {
        PostDTO post = postService.findById(id);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @GetMapping("/postsUser/{userId}")
    public ResponseEntity<?> getPostsByUser(@PathVariable("userId") Long id) {

        List<PostDTO> posts = postService.findByUser(id);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @PutMapping("/posts/{userId}/{postId}")
    public ResponseEntity<?> updatePost(@PathVariable("userId") Long idUser, @PathVariable("postId") Long idPost, @RequestBody PostDTO postToUpdate) {
        PostDTO post = postService.updatePost(idUser, idPost, postToUpdate);
        return new ResponseEntity<>("Post updated", HttpStatus.OK);

    }

}

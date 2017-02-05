package com.rexi.cola.blog.controller;

import com.rexi.cola.blog.model.Post;
import com.rexi.cola.blog.model.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by moi on 04/02/2017.
 */
@RestController
public class PostController {

    private final PostRepository postRepository;

    @Autowired
    public PostController(PostRepository postRepository){
        this.postRepository = postRepository;
    }

    @RequestMapping(path = "/blog/post/{id}", method = RequestMethod.GET)
        public ResponseEntity<Post> getPost(@PathVariable long id){
            Post post = postRepository.findOne(id);
            if (post != null){
                return ResponseEntity.ok(post);
            }
            return ResponseEntity.notFound().build();
        }

    @RequestMapping(path = "/blog/post", method = RequestMethod.GET)
    public ResponseEntity<String> getAllPost(){
        String countString = String.valueOf(postRepository.count());
        return ResponseEntity.ok(countString);
    }

    @RequestMapping(path = "/blog/post", method = RequestMethod.POST)
        public ResponseEntity<String> createPost(@RequestBody Post post){
        postRepository.save(post);
        return ResponseEntity.ok(post.getId().toString());
    }

    @RequestMapping(path = "/blog/post/{id}", method = RequestMethod.POST)
        public ResponseEntity<Post> updatePost(@PathVariable long id, @RequestBody Post post){
        Post postToUpdate = this.postRepository.findOne(id);
        if (postToUpdate != null){
            postToUpdate.setTitle(post.getTitle());
            postToUpdate.setContent(post.getContent());
            return ResponseEntity.ok(this.postRepository.save(postToUpdate));
        }
        return ResponseEntity.notFound().build();
    }
}






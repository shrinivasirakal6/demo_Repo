package com.blogdemo.demo.Controller;

import com.blogdemo.demo.Dto.LikeDto;
import com.blogdemo.demo.Service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/post")
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/Likes/{postId}")
    public ResponseEntity<List<LikeDto>> getLikes(
            @PathVariable Long postId
    ){
        List<LikeDto> likes = postService.getLikes(postId);
        return new ResponseEntity<>(likes, HttpStatus.OK);
    }
}

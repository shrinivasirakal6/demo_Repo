package com.blogdemo.demo.Controller;

import com.blogdemo.demo.Dto.*;
import com.blogdemo.demo.Entities.Post;
import com.blogdemo.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/user")
@RestController
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/create/user")
    public ResponseEntity<UserDto> createUser(
            @RequestBody UserDto dto
    ){
        UserDto user = userService.createUser(dto);
        return new ResponseEntity<>(user,HttpStatus.CREATED);
    }



    @PostMapping("/create/Admin")
    public ResponseEntity<UserDto> createAdmin(@RequestBody UserDto dto){
        UserDto admin = userService.createAdmin(dto);
        return new ResponseEntity<>(admin,HttpStatus.CREATED);
    }



    @PostMapping("/create/post/{id}")
    public ResponseEntity<PostDto> createPost(
            @PathVariable Long id,
            @RequestBody PostDto postDto
    ){
        PostDto savedPost = userService.createPost(id, postDto);
        return new ResponseEntity<>(savedPost, HttpStatus.CREATED);
    }




    @PostMapping("/post/like/{id}")
    public ResponseEntity<LikeDto> giveLike(
            @PathVariable Long id,
            @RequestBody LikeDto likeDto
    ){
        LikeDto dto = userService.likePost(id, likeDto);
        return new ResponseEntity<>(dto,HttpStatus.CREATED);
    }




    @PostMapping("/post/comment/{userId}")
    public ResponseEntity<CommentDto> commentOnPost(
            @PathVariable Long userId,
            @RequestBody CommentDto dto
    ){
        CommentDto commentDto = userService.commentOnPost(userId, dto);
        return new ResponseEntity<>(commentDto,HttpStatus.CREATED);
    }




    @PostMapping("/follow/{id}")
    public ResponseEntity<FollowDto> followUser(
            @PathVariable Long id,
            @RequestBody FollowDto dto
    ){
        FollowDto followDto = userService.followUser(id, dto);
        return new ResponseEntity<>(followDto,HttpStatus.CREATED);
    }




    @GetMapping("/Dashboard/{id}")
    public ResponseEntity<UserDashboardDto> getUserDashboard(
            @PathVariable long id
    ){
        UserDashboardDto userDashboard = userService.getUserDashboard(id);
        return new ResponseEntity<>(userDashboard,HttpStatus.FOUND);
    }
}

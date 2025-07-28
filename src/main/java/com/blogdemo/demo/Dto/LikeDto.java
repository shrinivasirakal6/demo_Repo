package com.blogdemo.demo.Dto;



import com.blogdemo.demo.Entities.Post;
import com.blogdemo.demo.Entities.User;

public class LikeDto {
    private Long id;
    private UserDto user;  // Convert User to UserDto
    private PostDto post;  // Convert Post to PostDto

    // Constructors
    public LikeDto() {
    }

    public LikeDto(Long id, UserDto user, PostDto post) {
        this.id = id;
        this.user = user;
        this.post = post;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public PostDto getPost() {
        return post;
    }

    public void setPost(PostDto post) {
        this.post = post;
    }
}


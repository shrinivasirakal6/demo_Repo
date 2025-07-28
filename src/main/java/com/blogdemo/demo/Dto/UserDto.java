package com.blogdemo.demo.Dto;



import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class UserDto {
    private Long id;
    private String username;
    private String email;


    private String password;

    private String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
//    private List<PostDto> posts;  // Convert Post to PostDto
//    private List<FollowDto> following;  // Convert Follow to FollowDto
//    private List<FollowDto> followers;  // Convert Follow to FollowDto

    // Constructors
    public UserDto() {
    }

    public UserDto(Long id, String username, String email, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
//        this.posts = posts;
//        this.following = following;
//        this.followers = followers;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    public List<PostDto> getPosts() {
//        return posts;
//    }
//
//    public void setPosts(List<PostDto> posts) {
//        this.posts = posts;
//    }

//    public List<FollowDto> getFollowing() {
//        return following;
//    }
//
//    public void setFollowing(List<FollowDto> following) {
//        this.following = following;
//    }
//
//    public List<FollowDto> getFollowers() {
//        return followers;
//    }
//
//    public void setFollowers(List<FollowDto> followers) {
//        this.followers = followers;
//    }
}


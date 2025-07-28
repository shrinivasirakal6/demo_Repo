package com.blogdemo.demo.Dto;



import java.time.LocalDateTime;
import java.util.List;

public class PostDto {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;
//    private UserDto user;
//    private List<CommentDto> comments;
//    private List<LikeDto> likes;


    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

//    public UserDto getUser() {
//        return user;
//    }
//
//    public void setUser(UserDto user) {
//        this.user = user;
//    }
//
//    public List<CommentDto> getComments() {
//        return comments;
//    }
//
//    public void setComments(List<CommentDto> comments) {
//        this.comments = comments;
//    }
//
//    public List<LikeDto> getLikes() {
//        return likes;
//    }
//
//    public void setLikes(List<LikeDto> likes) {
//        this.likes = likes;
//    }
}

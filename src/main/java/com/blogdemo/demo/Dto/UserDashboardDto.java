package com.blogdemo.demo.Dto;

import java.util.List;

public class UserDashboardDto {
    private List<String> postTitles;  // Convert Post to PostDto
   private List<String> followings;  // Convert Follow to FollowDto
    private List<String> followers;

    public List<String> getPostTitles() {
        return postTitles;
    }

    public void setPostTitles(List<String> postTitles) {
        this.postTitles = postTitles;
    }

    public List<String> getFollowings() {
        return followings;
    }

    public void setFollowings(List<String> followings) {
        this.followings = followings;
    }

    public List<String> getFollowers() {
        return followers;
    }

    public void setFollowers(List<String> followers) {
        this.followers = followers;
    }
}

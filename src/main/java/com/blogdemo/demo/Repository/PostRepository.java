package com.blogdemo.demo.Repository;

import com.blogdemo.demo.Entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
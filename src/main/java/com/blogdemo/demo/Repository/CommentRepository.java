package com.blogdemo.demo.Repository;

import com.blogdemo.demo.Entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
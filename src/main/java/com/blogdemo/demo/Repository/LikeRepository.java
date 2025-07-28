package com.blogdemo.demo.Repository;

import com.blogdemo.demo.Entities.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Long> {
}
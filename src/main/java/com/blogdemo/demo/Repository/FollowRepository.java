package com.blogdemo.demo.Repository;

import com.blogdemo.demo.Entities.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowRepository extends JpaRepository<Follow, Long> {
}
package com.blogdemo.demo.Service;

import com.blogdemo.demo.Dto.LikeDto;
import com.blogdemo.demo.Entities.Like;
import com.blogdemo.demo.Entities.Post;
import com.blogdemo.demo.Repository.PostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {

    private PostRepository postRepository;

    private ModelMapper modelMapper;


    public PostService(PostRepository postRepository, ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    }

    public List<LikeDto> getLikes(long postId){
        Post post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("post with the id not found"));
        List<Like> likes = post.getLikes();

       List<LikeDto> likesDto = new ArrayList<>();
        for(Like like: likes){
            LikeDto likeDto = modelMapper.map(like, LikeDto.class);
            likesDto.add(likeDto);
        }
        return likesDto;
    }
}

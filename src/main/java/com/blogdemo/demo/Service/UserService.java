package com.blogdemo.demo.Service;

import com.blogdemo.demo.Dto.*;
import com.blogdemo.demo.Entities.*;
import com.blogdemo.demo.Repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {


    private UserRepository userRepository;


    private ModelMapper modelMapper;


    private JwtService jwtService;
    private PostRepository postRepository;
    private final LikeRepository likeRepository;

    private CommentRepository commentRepository;

    private FollowRepository followRepository;

    public UserService(UserRepository userRepository, ModelMapper modelMapper, JwtService jwtService, PostRepository postRepository,
                       LikeRepository likeRepository, CommentRepository commentRepository, FollowRepository followRepository) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.jwtService = jwtService;
        this.postRepository = postRepository;
        this.likeRepository = likeRepository;
        this.commentRepository = commentRepository;
        this.followRepository = followRepository;
    }



    public UserDto createUser(UserDto dto){
        User user = modelMapper.map(dto, User.class);
        user.setRole("ROLE_USER");
        String password = user.getPassword();
        String hashpwed = BCrypt.hashpw(password, BCrypt.gensalt(5));
        user.setPassword(hashpwed);
        User saved = userRepository.save(user);
        UserDto userDto = modelMapper.map(saved, UserDto.class);
        return userDto;

    }

    public UserDto createAdmin(UserDto dto){
        User user = modelMapper.map(dto, User.class);
        user.setRole("ROLE_ADMIN");
        String password = user.getPassword();
        String hashpwed = BCrypt.hashpw(password, BCrypt.gensalt(5));
        user.setPassword(hashpwed);
        User saved = userRepository.save(user);
        UserDto userDto = modelMapper.map(saved, UserDto.class);
        return userDto;
    }


    public String verifyLogin(LoginDto dto){
        User user = userRepository.findByUsername(dto.getUsername()).orElseThrow(() -> new RuntimeException("username not found"));
        if(BCrypt.checkpw(dto.getPassword(),user.getPassword())){
          return jwtService.generateToken(user);
        }else {
            return "bad credentials";
        }
    }

    public PostDto createPost(Long id, PostDto postDto){
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User with the Id not found"));


        Post post = modelMapper.map(postDto, Post.class);

        post.setUser(user);


        Post saved = postRepository.save(post);

        PostDto dto = modelMapper.map(saved, PostDto.class);
        return dto;

    }

    public LikeDto likePost(Long id,LikeDto likeDto){

        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("user with the id not found"));

        Long postId = likeDto.getPost().getId();
        Post post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("post with Id not found"));

        Like like = modelMapper.map(likeDto, Like.class);
        like.setPost(post);
        like.setUser(user);

        Like savedLike = likeRepository.save(like);

        LikeDto likeDto1 = modelMapper.map(savedLike, LikeDto.class);
        return likeDto1;
    }

    public CommentDto commentOnPost(Long id,CommentDto commentDto){
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("user with Id not found"));


        Long postId = commentDto.getPostId();
        Post post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post with the Id not found"));


        Comment comment = modelMapper.map(commentDto, Comment.class);
        comment.setPost(post);
        comment.setAuthor(user);
        Comment savedComment = commentRepository.save(comment);

        CommentDto dto = modelMapper.map(savedComment, CommentDto.class);
        dto.setUserId(savedComment.getAuthor().getId());
        return dto;
    }

    public FollowDto followUser(Long id,FollowDto followDto){
        User follower = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Follower Id not found"));
        Long followedId = followDto.getFollowedId();
        User followed = userRepository.findById(followedId).orElseThrow(() -> new RuntimeException("Followed user Id not found"));

        Follow follow = new Follow();
                follow.setFollowed(followed);
                follow.setFollower(follower);
        Follow saved = followRepository.save(follow);

        FollowDto dto =new FollowDto();
        dto.setId(saved.getId());
        dto.setFollowedId(saved.getFollowed().getId());
        dto.setFollowerId(saved.getFollower().getId());
        return dto;
    }

    public UserDashboardDto getUserDashboard(Long id){
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User Id not found"));
        UserDashboardDto dto = new UserDashboardDto();


        List<Follow> followers = user.getFollowers();

        ArrayList<String> followersUsername = new ArrayList<>();
        for(Follow follow:followers){
            String username = follow.getFollower().getUsername();
            System.out.println(username);
            followersUsername.add(username);
        }

        System.out.println(followersUsername);

        //follower usernames are set
        dto.setFollowers(followersUsername);

        //hjgigwkwjwbkjbw


        List<Follow> followings = user.getFollowing();

        ArrayList<String> followingUsername = new ArrayList<>();
        for(Follow following: followings){
            String username = following.getFollowed().getUsername();
            System.out.println(username);
            followingUsername.add(username);
        }


        // following usernames are set
        dto.setFollowings(followingUsername);

        List<Post> posts = user.getPosts();

        ArrayList<String> postTitles = new ArrayList<>();
        for(Post post :posts){
            String title = post.getTitle();
            postTitles.add(title);
        }

        //post titles are set
        dto.setPostTitles(postTitles);

        return dto;

    }
}

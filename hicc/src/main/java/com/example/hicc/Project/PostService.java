package com.example.hicc.Project;

import com.example.hicc.Domain.Post;
import com.example.hicc.Exception.PostNotFoundException;
import com.example.hicc.Dto.PostRequestDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
    }

    public void createPost(PostRequestDto dto) {
        Post post = new Post(dto.getTitle(), dto.getContent());
        postRepository.save(post);
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post getPostById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException("게시글을 찾을 수 없습니다."));
    }



}


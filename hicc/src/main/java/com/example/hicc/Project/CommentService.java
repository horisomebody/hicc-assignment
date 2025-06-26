package com.example.hicc.Project;

import com.example.hicc.Domain.Comment;
import com.example.hicc.Domain.Post;
import com.example.hicc.Exception.PostNotFoundException;
import com.example.hicc.Dto.CommentRequestDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public CommentService(PostRepository postRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;

    }

    public List<Comment> getCommentsByPostId(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException("존재하지 않는 게시글입니다."));
        return commentRepository.findByPostId(postId);
    }

    public void createComment(Long postId, CommentRequestDto dto) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException("존재하지 않는 게시글입니다."));

        Comment comment = new Comment(dto.getContent(), post);
        commentRepository.save(comment);
    }

}


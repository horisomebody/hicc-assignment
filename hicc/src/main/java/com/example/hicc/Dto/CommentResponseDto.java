package com.example.hicc.Dto;

import com.example.hicc.Domain.Comment;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponseDto {
    private Long id;
    private String content;
    private LocalDateTime createDate;
    private Long post;  // postId를 그냥 post로 출력

    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.content = comment.getContent();
        this.createDate = comment.getCreateDate();
        this.post = comment.getPost().getId();  // LAZY 해제됨
    }
}

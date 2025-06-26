package com.example.hicc.Project;

import com.example.hicc.Domain.Comment;
import com.example.hicc.Dto.CommentRequestDto;
import com.example.hicc.Dto.CommentResponseDto;
import com.example.hicc.Exception.PostNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/{id}/comment")
    public ResponseEntity<?> getCommentsByPostId(@PathVariable("id") Long id) {
        try {
            List<Comment> comments = commentService.getCommentsByPostId(id);
            List<CommentResponseDto> response = comments.stream()
                    .map(CommentResponseDto::new)
                    .toList();
            return ResponseEntity.ok(response);
        } catch (PostNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    Map.of(
                            "status_code", 404,
                            "error", "POST_NOT_FOUND",
                            "message", e.getMessage()
                    )
            );
        }
    }


    @PostMapping("/{id}/comment")
    public ResponseEntity<?> createComment(@PathVariable("id") Long id, @RequestBody CommentRequestDto requestDto) {
        try {
            commentService.createComment(id, requestDto);
            return ResponseEntity.ok(Map.of("message", "성공적으로 등록됐습니다."));
        } catch (PostNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    Map.of(
                            "status_code", 404,
                            "error", "POST_NOT_FOUND",
                            "message", e.getMessage()
                    )
            );
        }
    }

}


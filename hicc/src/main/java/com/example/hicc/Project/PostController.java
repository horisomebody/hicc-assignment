package com.example.hicc.Project;

import com.example.hicc.Domain.Post;
import com.example.hicc.Exception.PostNotFoundException;
import com.example.hicc.Dto.PostRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;


    @PostMapping
    public ResponseEntity<Map<String, String>> createPost(@RequestBody PostRequestDto requestDto) {
        postService.createPost(requestDto);
        return ResponseEntity.ok(Map.of("message", "성공적으로 등록됐습니다."));
    }

    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts() {
        List<Post> posts = postService.getAllPosts();
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPostById(@PathVariable("id") Long id) {
        try {
            Post post = postService.getPostById(id);
            return ResponseEntity.ok(post);
        } catch (PostNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    Map.of(
                            "status_code", 404,
                            "error", "POST_NOT_FOUND",
                            "message", "존재하지 않는 게시글입니다."
                    )
            );
        }
    }




}


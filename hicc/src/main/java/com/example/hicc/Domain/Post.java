package com.example.hicc.Domain;

import jakarta.persistence.Entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 30, nullable = false)  // 최대 30자, null 금지
    private String title;

    @Lob  // Large Object, 길이 제한 없이 사용 가능
    @Column(nullable = false)
    private String content;

    @Column(name = "create_date", nullable = false)
    private LocalDateTime createDate;

    // 생성 시 자동으로 작성 시간 설정
    @PrePersist
    protected void onCreate() {
        this.createDate = LocalDateTime.now();
    }

    // 기본 생성자
    protected Post() {}

    // 생성자
    public Post(String title, String content) {
        this.title = title;
        this.content = content;
    }
}

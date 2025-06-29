package com.example.hicc.Project;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.hicc.Domain.Post;
import java.util.List;



public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByTitle(String title);
}


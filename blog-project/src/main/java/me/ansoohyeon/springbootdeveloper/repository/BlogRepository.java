package me.ansoohyeon.springbootdeveloper.repository;

import me.ansoohyeon.springbootdeveloper.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Article, Long> {
}

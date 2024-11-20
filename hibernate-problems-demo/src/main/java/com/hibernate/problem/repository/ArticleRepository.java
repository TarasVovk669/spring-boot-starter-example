package com.hibernate.problem.repository;

import com.hibernate.problem.domain.Article;
import com.hibernate.problem.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
}

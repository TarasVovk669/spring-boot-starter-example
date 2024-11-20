package com.hibernate.problem.repository;

import com.hibernate.problem.domain.Author;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Query("SELECT a FROM Author a JOIN FETCH a.articles")
    List<Author> findAllWithArticles();

    @Query("SELECT a FROM Author a  LEFT JOIN FETCH a.articles")
    List<Author> findAllWithArticlesLeftJF();

    @EntityGraph(attributePaths = {"articles"})
    List<Author> findAll();

    @EntityGraph(attributePaths = {"articles"})
    Optional<Author> findById(Long id);

}

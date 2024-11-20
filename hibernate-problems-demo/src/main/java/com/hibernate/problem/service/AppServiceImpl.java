package com.hibernate.problem.service;

import com.hibernate.problem.domain.Article;
import com.hibernate.problem.domain.Author;
import com.hibernate.problem.repository.ArticleRepository;
import com.hibernate.problem.repository.AuthorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class AppServiceImpl implements AppService {

    private final AuthorRepository authorRepository;
    private final ArticleRepository articleRepository;

    @Override
    @Transactional(readOnly = true)
    public void getAllAuthorArticles() {
        authorRepository.findAll().stream()
                .flatMap(x -> x.getArticles().stream())
                .map(Article::getName)
                .forEach(System.out::println);
    }

    @Override
    public void getAllAuthorArticlesJoinFetch() {
        authorRepository.findAllWithArticles().stream()
                .flatMap(x -> x.getArticles().stream())
                .map(Article::getName)
                .forEach(System.out::println);
    }

    @Override
    public void getAllAuthorArticlesLeftJoinFetch() {
        authorRepository.findAllWithArticlesLeftJF().stream()
                .flatMap(x -> x.getArticles().stream())
                .map(Article::getName)
                .forEach(System.out::println);
    }

    @Override
    public void getAllAuthorArticlesGraph() {
        authorRepository.findAll().stream()
                .flatMap(x -> x.getArticles().stream())
                .map(Article::getName)
                .forEach(System.out::println);
    }

    @Override
    public void hashCodeProblem() {
        Author author = new Author();
        author.setName("Author 404");
        Set<Author> authors = new HashSet<>();
        authors.add(author);

        authorRepository.save(author);
        System.out.println(authors.contains(author)); // false, должно быть true
    }

}


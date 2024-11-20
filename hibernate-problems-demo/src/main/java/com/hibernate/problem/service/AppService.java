package com.hibernate.problem.service;

import org.springframework.transaction.annotation.Transactional;

public interface AppService {
    @Transactional
    void getAllAuthorArticles();

    void getAllAuthorArticlesJoinFetch();

    void getAllAuthorArticlesLeftJoinFetch();

    void getAllAuthorArticlesGraph();

    void hashCodeProblem();
}

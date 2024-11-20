package com.hibernate.problem;

import com.hibernate.problem.service.AppService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ApplicationStartupRunner implements CommandLineRunner {

    private final AppService appService;

    @Override
    public void run(String... args) {
        //appService.getAllAuthorArticles();
        //appService.getAllAuthorArticlesJoinFetch();
        //appService.getAllAuthorArticlesLeftJoinFetch();
        appService.getAllAuthorArticlesGraph();
        //appService.hashCodeProblem();

    }
}
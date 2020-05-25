package com.bd.mzs.article.service;

import com.bd.mzs.article.controller.dto.ArticleDTO;
import com.bd.mzs.article.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ArticleService {

    Article saveArticle(ArticleDTO articleDTO);

    Optional<Article> getById(int id);

    void deleteById(int id);

    Page<Article> getArticlesPage(PageRequest pageRequest);

    Article modifyArticle(Article article);

    void deleteArticle(Article article);

    List<Article> getArticleList();

}

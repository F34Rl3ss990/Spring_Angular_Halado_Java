package com.bd.mzs.article.service;

import com.bd.mzs.article.controller.dto.ArticleDTO;
import com.bd.mzs.article.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface ArticleService {

    Article saveArticle(ArticleDTO articleDTO);

    Optional<Article> getById(int id);

    void deleteById(int id);

    Page<Article> getArticlesPage(int page, int size);

    Article modifyArticle(Article article);

    void deleteArticle(Article article);

    Page<Article> getArticlesPageById(int page, int size, int id);

    void setTitleAndText(ArticleDTO articleDTO, Optional<Article> article);

}

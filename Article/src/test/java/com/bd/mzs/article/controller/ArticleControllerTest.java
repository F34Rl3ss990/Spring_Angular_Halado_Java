package com.bd.mzs.article.controller;

import com.bd.mzs.article.controller.dto.ArticleDTO;
import com.bd.mzs.article.entity.Article;
import com.bd.mzs.article.entity.User;
import com.bd.mzs.article.service.ArticleService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@ExtendWith(MockitoExtension.class)
class ArticleControllerTest {

    private static Article article1;
    private static Article article3;
    private static ArticleDTO article4;
    private static User user1;
    private static User user2;

    @Mock
    private ArticleService articleService;

    @InjectMocks
    private ArticleController articleController;


    @BeforeAll
    public static void init() {
        user1 = new User(1,"aaaaaa", "aaaaaaaaaa");
        user2 = new User(2,"bbbbbb", "bbbbbbbbbbb");
        article1 = new Article("aaaaaa", "aaaaaaaaaaaaaaaaa", user1);
        article3 = new Article("cccccccccc", "cccccccccccccccc", user2);
        article4 = new ArticleDTO("aaaaaa", "aaaaaaaaaaaaaaaaa", 1);
    }

    @Test
    void getArticles() {
        int page = 0;
        int size = 10;
        List<Article> articles = new ArrayList<>();
        articles.add(article1);
        Page<Article> articlesPage = new PageImpl<>(articles);
        Mockito.when(articleService.getArticlesPage(page, size)).thenReturn(articlesPage);
        assertThat(((int) articleController.list(0,10).get().count()), is(1));
        Mockito.verify(articleService, Mockito.times(1)).getArticlesPage(page, size);
    }

    @Test
    void getArticlesById() {
        int page = 0;
        int size = 10;
        List<Article> articles = new ArrayList<>();
        articles.add(article1);
        Page<Article> articlesPage = new PageImpl<>(articles);
        Mockito.when(articleService.getArticlesPageById(page, size, 1)).thenReturn(articlesPage);
        assertThat(((int) articleController.listById(0,10, 1).get().count()), is(1));
        Mockito.verify(articleService, Mockito.times(1)).getArticlesPageById(page, size, 1);
    }

    @Test
    void addArticle() {
        articleController.addArticle(article4);
        Mockito.verify(articleService, Mockito.times(1)).saveArticle(article4);
    }


    @Test
    void openArticleWhenFound() {
        Mockito.when(articleService.getById(1)).thenReturn(Optional.of(article1));
        articleController.openArticle(1);
        Mockito.verify(articleService, Mockito.times(1)).getById(1);
    }

    @Test
    void openArticleWhenNotFound() {
        Mockito.when(articleService.getById(1)).thenReturn(Optional.empty());
        articleController.openArticle(1);
        Mockito.verify(articleService, Mockito.times(1)).getById(1);
        assertThat(articleController.openArticle(1).getBody(), is(nullValue()));
    }

    @Test
    void deleteArticleWhenFound() {
        Mockito.when(articleService.getById(1)).thenReturn(Optional.of(article1));
        articleController.deleteArticle(1);
        Mockito.verify(articleService, Mockito.times(1)).deleteById(1);
    }

    @Test
    void deleteArticleWhenNotFound() {
        Mockito.when(articleService.getById(1)).thenReturn(Optional.empty());
        articleController.deleteArticle(1);
        Mockito.verify(articleService, Mockito.times(0)).deleteById(1);
    }

    @Test
    void modifyArticleWhenFound() {
        Mockito.when(articleService.getById(1)).thenReturn(Optional.of(article1));
        Mockito.when(articleService.modifyArticle(article1)).thenReturn(article3);
        assertThat(Objects.requireNonNull(articleController.modifyArticle(1, article4).getBody()).getTitle(), is("cccccccccc"));
        Mockito.verify(articleService, Mockito.times(1)).modifyArticle(article1);
    }

    @Test
    void modifyArticleWhenNotFound() {
        Mockito.when(articleService.getById(4)).thenReturn(Optional.empty());
        ResponseEntity<Article> p = articleController.modifyArticle(4, article4);
        assertThat(p.getStatusCode(), is(HttpStatus.NOT_FOUND));
    }
}

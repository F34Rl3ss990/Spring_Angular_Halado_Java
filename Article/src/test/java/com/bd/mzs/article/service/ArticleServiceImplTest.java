package com.bd.mzs.article.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.mock;

import com.bd.mzs.article.controller.ArticleController;
import com.bd.mzs.article.controller.dto.ArticleDTO;
import com.bd.mzs.article.entity.Article;
import com.bd.mzs.article.entity.User;
import com.bd.mzs.article.repository.ArticleRepository;
import com.bd.mzs.article.repository.UserRepository;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class ArticleServiceImplTest {

    private static Article article1;
    private static Article article2;
    private static Article article3;
    private static ArticleDTO articleDto;

    private static User user1;
    private static User user2;

    @Mock
    private ArticleRepository articleRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @InjectMocks
    private ArticleServiceImpl articleService;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeAll
    public static void init() {
        user1 = new User("aaaaaa", "aaaaaaaaaa");
        user2 = new User("bbbbbb", "bbbbbbbbbbb");
        article1 = new Article("aaaaaa", "aaaaaaaaaaaaaaaaa", user1);
        article2 = new Article("bbbbbb", "bbbbbbbbbbbbbbb", user1);
        article3 = new Article("cccccccccc", "cccccccccccccccc", user2);
        articleDto = new ArticleDTO("aaaaaa", "aaaaaaaaaaaaaaaaa", 1);
    }

    @Test
    void getArticlesPage() {
        int page = 0;
        int size = 10;
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Article> articles = mock(Page.class);
        Mockito.when(articleRepository.findAll(org.mockito.Matchers.isA(PageRequest.class))).thenReturn(articles);
        assertThat(((int) articleService.getArticlesPage(pageRequest).get().count()), is(0));
        Mockito.verify(articleRepository, Mockito.times(1)).findAll(pageRequest);
    }


    @Test
    void getArticleListWhenRecord() {
        Mockito.when(articleRepository.findAll()).thenReturn(Arrays.asList(article1, article2));
        assertThat(articleService.getArticleList().size(), is(2));
        assertThat(articleService.getArticleList().get(0), is(article1));
        assertThat(articleService.getArticleList().get(1),is(article2));
        Mockito.verify(articleRepository, Mockito.times(3)).findAll();
    }

    @Test
    void getArticleListWhenNoRecord() {
        Mockito.when(articleRepository.findAll()).thenReturn(Arrays.asList());
        assertThat(articleService.getArticleList().size(), is(0));
        Mockito.verify(articleRepository, Mockito.times(1)).findAll();
    }
/*
    @Test
    void saveArticle() {
        Mockito.when(articleRepository.save(article1)).thenReturn(article1);
        Mockito.when(userRepository.findById(1)).thenReturn(Optional.ofNullable(user1));
        Mockito.when(userService.getById(1)).thenReturn(Optional.ofNullable(user1));
        assertThat(articleService.saveArticle(articleDto), is(article1));
        Mockito.verify(articleRepository, Mockito.times(1)).save(article1);
    }*/

    @Test
    void modifyArticle() {
        Mockito.when(articleRepository.save(article1)).thenReturn(article1);
        assertThat(articleService.modifyArticle(article1), is(article1));
        Mockito.verify(articleRepository, Mockito.times(1)).save(article1);
    }

    @Test
    void getById() {
        Mockito.when(articleRepository.findById(1)).thenReturn(Optional.of(article1));
        assertThat(articleService.getById(1), is(Optional.of(article1)));
        Mockito.verify(articleRepository, Mockito.times(1)).findById(1);
    }

    @Test
    void deleteById() {
        articleService.deleteById(1);
        Mockito.verify(articleRepository, Mockito.times(1)).deleteById(1);
    }

    @Test
    void deleteArticle() {
        articleService.deleteArticle(article1);
        Mockito.verify(articleRepository, Mockito.times(1)).delete(article1);
    }
}

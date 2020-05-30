package com.bd.mzs.article.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.mock;

import com.bd.mzs.article.controller.dto.ArticleDTO;
import com.bd.mzs.article.entity.Article;
import com.bd.mzs.article.entity.User;
import com.bd.mzs.article.repository.ArticleRepository;
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

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class ArticleServiceImplTest {

    private static Article article1;
    private static ArticleDTO articleDto;

    private static User user1;


    @Mock
    private ArticleRepository articleRepository;

    @InjectMocks
    private ArticleServiceImpl articleService;

    @InjectMocks
    private UserServiceImpl userService;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeAll
    public static void init() {
        user1 = new User("aaaaaa", "aaaaaaaaaa");
        article1 = new Article("aaaaaa", "aaaaaaaaaaaaaaaaa", user1);
        articleDto = new ArticleDTO("aaaaaa", "aaaaaaaaaaaaaaaaa", 1);
    }

    @Test
    void getArticlesPage() {
        int page = 0;
        int size = 10;
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Article> articles = mock(Page.class);
        Mockito.when(articleRepository.findAll(org.mockito.Matchers.isA(PageRequest.class))).thenReturn(articles);
        assertThat(((int) articleService.getArticlesPage(0,10).get().count()), is(0));
        Mockito.verify(articleRepository, Mockito.times(1)).findAll(pageRequest);
    }

    @Test
    void getArticlesPageById() {
        int page = 0;
        int size = 10;
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Article> articles = mock(Page.class);
        Mockito.when(articleRepository.findAll(org.mockito.Matchers.isA(PageRequest.class))).thenReturn(articles);
        assertThat(((int) articleService.getArticlesPageById(0,10, 10).get().count()), is(0));
        Mockito.verify(articleRepository, Mockito.times(1)).findAll(pageRequest);
    }


    @Test
    void saveArticle() {
        Mockito.when(articleRepository.save(article1)).thenReturn(article1);
        Mockito.when(userService.getById(1)).thenReturn(Optional.ofNullable(user1));
        articleService.saveArticle(articleDto);
        assertThat(articleService.saveArticle(articleDto), is(article1));
        Mockito.verify(articleRepository, Mockito.times(1)).save(article1);
    }

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

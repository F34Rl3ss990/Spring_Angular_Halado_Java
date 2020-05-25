package com.bd.mzs.article.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import com.bd.mzs.article.controller.UserController;
import com.bd.mzs.article.controller.dto.UserDTO;
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
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class UserServiceImplTest {

    private static User user1;
    private static Article article1;
    private static Article article2;
    private static UserDTO userDTO1;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private ArticleRepository articleRepository;

    @InjectMocks
    private ArticleServiceImpl articleService;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeAll
    public static void init() {
        user1 = new User("aaaaaa", "aaaaaaaaaa");
        article1 = new Article("aaaaaa", "aaaaaaaaaaaaaaaaa", user1);
        article2 = new Article("bbbbbb", "bbbbbbbbbbbbbbb", user1);
        userDTO1 = new UserDTO("aaaaaa", "aaaaaaaaaa");

    }


    @Test
    void getUsersPageWhenRecord() {
        int page = 0;
        int size = 10;
        PageRequest pageRequest = PageRequest.of(page, size);
        Page users = Mockito.mock(Page.class);
        Mockito.when(userRepository.findAll(org.mockito.Matchers.isA(PageRequest.class))).thenReturn(users);
        assertThat(((int) userService.getUsersPage(pageRequest).get().count()), is(0));
        Mockito.verify(userRepository, Mockito.times(1)).findAll(pageRequest);
    }


    @Test
    void getUserById() {
        Mockito.when(userRepository.findById(1)).thenReturn(Optional.of(user1));
        assertThat(userService.getById(1), is(Optional.of(user1)));
        Mockito.verify(userRepository, Mockito.times(1)).findById(1);
    }

    @Test
    void saveUser() {
        Mockito.when(userRepository.save(user1)).thenReturn(user1);
        assertThat(userService.saveUser(userDTO1), is(user1));
        Mockito.verify(userRepository, Mockito.times(1)).save(user1);
    }
/*
    @Test
    void deleteUser() {
        Mockito.when(articleRepository.findAll()).thenReturn(Arrays.asList(article1, article2));
        Mockito.when(articleService.getArticleList()).thenReturn(Arrays.asList(article1, article2));
        userService.deleteUser(1);
        Mockito.verify(userRepository, Mockito.times(1)).deleteById(1);
    }*/
}

package com.bd.mzs.article.controller;


import com.bd.mzs.article.controller.dto.UserDTO;
import com.bd.mzs.article.entity.User;
import com.bd.mzs.article.service.UserService;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class UserControllerTest {

    private static User user1;
    private static UserDTO userDTO1;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeAll
    public static void init() {
        user1 = new User("aaaaaa", "aaaaaaaaaa");
        userDTO1 = new UserDTO("aaaaaa", "aaaaaaaaaa");
    }

    @Test
    void getUsers() {
        int page = 0;
        int size = 10;
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<User> users = Mockito.mock(Page.class);
        Mockito.when(userService.getUsersPage(org.mockito.Matchers.isA(PageRequest.class))).thenReturn(users);
        assertThat(((int) userController.list(0, 10).get().count()), is(0));
        Mockito.verify(userService, Mockito.times(1)).getUsersPage(pageRequest);
    }

    @Test
    void addUser() {
        ResponseEntity<User> user = userController.addUser(userDTO1);
        Mockito.verify(userService, Mockito.times(1)).saveUser(userDTO1);
    }


    @Test
    void deleteUserWhenFound() {
        Mockito.when(userService.getById(1)).thenReturn(Optional.of(user1));
        userController.deleteUser(1);
        Mockito.verify(userService, Mockito.times(1)).deleteUser(1);
    }

    @Test
    void deleteUserWhenNotFound() {
        Mockito.when(userService.getById(1)).thenReturn(Optional.empty());
        userController.deleteUser(1);
        Mockito.verify(userService, Mockito.times(0)).deleteUser(1);
    }


    @Test
    void userByIdWhenFound() {
        Mockito.when(userService.getById(1)).thenReturn(Optional.of(user1));
        ResponseEntity<User> u = userController.userById(1);
        assertThat(u.getBody(), is(user1));
    }

    @Test
    void userByIdWhenNotFound() {
        Mockito.when(userService.getById(1)).thenReturn(Optional.empty());
        ResponseEntity<User> u = userController.userById(1);
        assertThat(u.getStatusCode(), is(HttpStatus.NOT_FOUND));
    }
}

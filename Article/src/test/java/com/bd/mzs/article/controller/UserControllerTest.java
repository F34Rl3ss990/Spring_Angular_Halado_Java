package com.bd.mzs.article.controller;


import com.bd.mzs.article.controller.dto.UserDTO;
import com.bd.mzs.article.entity.User;
import com.bd.mzs.article.service.UserService;
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
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    private static User user1;
    private static UserDTO userDTO1;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;


    @BeforeAll
    public static void init() {
        user1 = new User(1,"aaaaaa", "aaaaaaaaaa");
        userDTO1 = new UserDTO("aaaaaa", "aaaaaaaaaa");
    }

    @Test
    void getUsers() {
        int page = 0;
        int size = 10;
        List<User> users = new ArrayList<>();
        users.add(user1);
        Page<User> usersPage = new PageImpl<>(users);
        Mockito.when(userService.getUsersPage(page, size)).thenReturn(usersPage);
        assertThat(((int) userController.list(0,10).get().count()), is(1));
        Mockito.verify(userService, Mockito.times(1)).getUsersPage(page, size);
    }

    @Test
    void addUser() {
        userController.addUser(userDTO1);
        Mockito.verify(userService, Mockito.times(1)).saveUser(userDTO1);
    }

    @Test
    void listUser(){
        List<UserDTO> ar = new ArrayList<>();
        Mockito.when(userService.getUsersTree()).thenReturn(ar);
        userController.listUser();
        Mockito.verify(userService, Mockito.times(1)).getUsersTree();
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

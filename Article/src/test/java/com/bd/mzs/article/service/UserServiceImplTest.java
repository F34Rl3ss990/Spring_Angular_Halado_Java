package com.bd.mzs.article.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import com.bd.mzs.article.controller.dto.UserDTO;
import com.bd.mzs.article.entity.User;
import com.bd.mzs.article.repository.UserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    private static User user1;
    private static UserDTO userDTO1;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;


    @BeforeAll
    public static void init() {
        user1 = new User(1,"aaaaaa", "aaaaaaaaaa");
        userDTO1 = new UserDTO("aaaaaa", "aaaaaaaaaa");
    }

    @Test
    void getUsersPageWhenRecord() {
        int page = 0;
        int size = 10;
        PageRequest pageRequest = PageRequest.of(page, size);
        List<User> users = new ArrayList<>();
        users.add(user1);
        Page<User> usersPage = new PageImpl<>(users);
        Mockito.when(userRepository.findAll(org.mockito.Matchers.isA(PageRequest.class))).thenReturn(usersPage);
        assertThat(((int) userService.getUsersPage(0,10).get().count()), is(1));
        Mockito.verify(userRepository, Mockito.times(1)).findAll(pageRequest);
    }

    @Test
    void getUsers(){
        List<User> users = new ArrayList<>();
        users.add(user1);
        Mockito.when(userRepository.findAll()).thenReturn(users);
        userService.getUsers();
        Mockito.verify(userRepository, Mockito.times(1)).findAll();
    }

    @Test
    void getUsersTree(){
        List<User> users = new ArrayList<>();
        users.add(user1);
        Mockito.when(userRepository.findAll()).thenReturn(users);
        userService.getUsersTree();
        Mockito.verify(userRepository, Mockito.times(1)).findAll();
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

    @Test
    void deleteUser() {
        userService.deleteUser(1);
        Mockito.verify(userRepository, Mockito.times(1)).deleteById(1);
    }
}

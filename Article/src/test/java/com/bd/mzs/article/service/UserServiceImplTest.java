package com.bd.mzs.article.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import com.bd.mzs.article.controller.dto.UserDTO;
import com.bd.mzs.article.entity.User;
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

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class UserServiceImplTest {

    private static User user1;
    private static UserDTO userDTO1;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

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
    void getUsersPageWhenRecord() {
        Page users = Mockito.mock(Page.class);
        Mockito.when(userRepository.findAll(org.mockito.Matchers.isA(PageRequest.class))).thenReturn(users);
        assertThat(((int) userService.getUsersPage(0,10).get().count()), is(0));
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

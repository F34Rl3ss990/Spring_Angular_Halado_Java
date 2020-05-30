package com.bd.mzs.article.service;

import com.bd.mzs.article.controller.dto.UserDTO;
import com.bd.mzs.article.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {

    User saveUser(UserDTO userDTO);

    void deleteUser(int id);

    Optional<User> getById(int userId);

    Page<User> getUsersPage(int page, int size);

    List<User> getUsers();

    List<UserDTO> getUsersTree();
}

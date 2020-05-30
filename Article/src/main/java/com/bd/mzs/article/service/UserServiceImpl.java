package com.bd.mzs.article.service;

import com.bd.mzs.article.controller.dto.UserDTO;
import com.bd.mzs.article.entity.User;
import com.bd.mzs.article.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private ArticleService articleService;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setArticleService(ArticleService articleService) {
        this.articleService = articleService;
    }

    @Override
    public Page<User> getUsersPage(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<User> pageResult = userRepository.findAll(pageRequest);
        List<User> users = pageResult
                .stream()
                .collect(toList());
        return new PageImpl<>(users, pageRequest, pageResult.getTotalElements());
    }

    @Override
    public List<UserDTO> getUsersTree(){
        List<UserDTO> ar;
        ar = userRepository.findAll().stream().map(user -> UserDTO.builder().last_name(user.getLast_name()).first_name(user.getFirst_name()).user_id(user.getUser_id()).articles(user.getArticles()).build()).collect(Collectors.toList());
        return ar;
    }

    @Override
    public List<User> getUsers(){
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getById(int userId) {
        return userRepository.findById(userId);
    }

    @Override
    public User saveUser(UserDTO userDTO) {
        return userRepository.save(convertUserDTOtoUser(userDTO));
    }

    @Override
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    private User convertUserDTOtoUser(UserDTO userDTO) {
        User user = new User();
        user.setFirst_name(userDTO.getFirst_name());
        user.setLast_name(userDTO.getLast_name());
        return user;
    }
}


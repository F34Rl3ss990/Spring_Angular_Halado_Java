package com.bd.mzs.article.service;

import com.bd.mzs.article.controller.dto.UserDTO;
import com.bd.mzs.article.entity.Article;
import com.bd.mzs.article.entity.User;
import com.bd.mzs.article.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
    public Page<User> getUsersPage(PageRequest pageRequest) {
        return userRepository.findAll(pageRequest);
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
        for (Article article : articleService.getArticleList()) {
            if (article.getUser().getUserID() == id) {
                articleService.deleteArticle(article);
            }
        }
        userRepository.deleteById(id);
    }

    private User convertUserDTOtoUser(UserDTO userDTO) {
        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        return user;
    }
}


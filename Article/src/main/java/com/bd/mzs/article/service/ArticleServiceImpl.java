package com.bd.mzs.article.service;

import com.bd.mzs.article.controller.dto.ArticleDTO;
import com.bd.mzs.article.entity.Article;
import com.bd.mzs.article.entity.User;
import com.bd.mzs.article.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleServiceImpl implements ArticleService {

    private ArticleRepository articleRepository;
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setArticleRepository(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public Page<Article> getArticlesPage(PageRequest pageRequest) {
        return articleRepository.findAll(pageRequest);
    }

    @Override
    public List<Article> getArticleList() {
        return articleRepository.findAll();
    }

    @Override
    public Article saveArticle(ArticleDTO articleDTO) {
        return articleRepository.save(convertArticleDTOtoArticle(articleDTO));
    }

    @Override
    public Article modifyArticle(Article article) {
        return articleRepository.save(article);
    }

    @Override
    public Optional<Article> getById(int id) {
        return articleRepository.findById(id);
    }

    @Override
    public void deleteById(int id) {
        articleRepository.deleteById(id);
    }

    @Override
    public void deleteArticle(Article article) {
        articleRepository.delete(article);
    }

    private Article convertArticleDTOtoArticle(ArticleDTO articleDTO) {
        Article article = new Article();
        article.setArticleText(articleDTO.getArticleText());
        article.setTitle(articleDTO.getTitle());
        Optional<User> article1 = userService.getById(articleDTO.getUserID());
        article1.ifPresent(article::setUser);
        return article;
    }

}

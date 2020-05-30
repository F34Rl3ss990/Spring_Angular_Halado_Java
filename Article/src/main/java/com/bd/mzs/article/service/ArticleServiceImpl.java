package com.bd.mzs.article.service;

import com.bd.mzs.article.controller.dto.ArticleDTO;
import com.bd.mzs.article.entity.Article;
import com.bd.mzs.article.entity.User;
import com.bd.mzs.article.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import static java.util.stream.Collectors.toList;

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
    public Page<Article> getArticlesPage(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Article> pageResult = articleRepository.findAll(pageRequest);
        List<Article> articles = pageResult
                .stream()
                .collect(toList());
        return new PageImpl<>(articles, pageRequest, pageResult.getTotalElements());
    }

    @Override
    public Page<Article> getArticlesPageById(int page, int size, int id) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Article> pageResult = articleRepository.findAll(pageRequest);
        Predicate<Article> contain = (Article item) -> item.getUser().getUser_id() == id;
        List<Article> articles = pageResult
                .stream()
                .filter(contain)
                .collect(toList());
        return new PageImpl<>(articles, pageRequest, pageResult.getTotalElements());
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
        article.setArticle_text(articleDTO.getArticle_text());
        article.setTitle(articleDTO.getTitle());
        Optional<User> article1 = userService.getById(articleDTO.getUser_id());
        article1.ifPresent(article::setUser);
        return article;
    }

    @Override
    public void setTitleAndText(ArticleDTO articleDTO, Optional<Article> article){
        if (article.isPresent()) {
            article.get().setTitle(articleDTO.getTitle());
            article.get().setArticle_text(articleDTO.getArticle_text());
        }
    }

}

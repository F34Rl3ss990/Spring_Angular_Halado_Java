package com.bd.mzs.article.controller;

import com.bd.mzs.article.controller.dto.ArticleDTO;
import com.bd.mzs.article.entity.Article;
import com.bd.mzs.article.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;


@RestController
@Slf4j
public class ArticleController {

    private ArticleService articleService;

    private static final String idErr = "Article with id ";
    private static final String notExistErr = " does not exist.";

    @Autowired
    public void setArticleService(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping("addArticle")
    public void addArticle(@Valid @RequestBody ArticleDTO articleDTO) {
       articleService.saveArticle(articleDTO);
    }

    @GetMapping("getArticles")
    public Page<Article> list(@RequestParam(name = "page", defaultValue = "0") int page,
                              @RequestParam(name = "size", defaultValue = "10") int size) {
        return articleService.getArticlesPage(page, size);
    }

    @GetMapping("getArticlesByUserID")
    public Page<Article> listById(@RequestParam(name = "page", defaultValue = "0") int page,
                                  @RequestParam(name = "size", defaultValue = "10") int size,
                                  @RequestParam(name = "id") int id) {
        return articleService.getArticlesPageById(page, size, id);
    }

    @GetMapping("openArticle/{id}")
    public ResponseEntity<Article> openArticle(@PathVariable int id) {
        Optional<Article> article = articleService.getById(id);

        if (!article.isPresent()) {
            log.error(idErr + id + notExistErr);
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(article.get());
    }

    @DeleteMapping("deleteArticle/{id}")
    public ResponseEntity<Article> deleteArticle(@PathVariable int id) {

        Optional<Article> article = articleService.getById(id);
        if (!article.isPresent()) {
            log.error(idErr + id + notExistErr);
            return ResponseEntity.notFound().build();
        }
        articleService.deleteById(id);
        return ResponseEntity.ok(article.get());
    }

    @PutMapping("modifyArticle/{id}")
    public ResponseEntity<Article> modifyArticle(@PathVariable(name = "id") int id,
                                                 @Valid @RequestBody ArticleDTO articleDTO) {
        Optional<Article> article = articleService.getById(id);
        if (!article.isPresent()) {
            log.error(idErr + id + notExistErr);
            return ResponseEntity.notFound().build();
        }

        articleService.setTitleAndText(articleDTO, article);

        return ResponseEntity.ok(articleService.modifyArticle(article.get()));
    }
}

package com.bd.mzs.article.controller;

import com.bd.mzs.article.controller.dto.ArticleDTO;
import com.bd.mzs.article.entity.Article;
import com.bd.mzs.article.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import static java.util.stream.Collectors.toList;


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
    public ResponseEntity<Article> addArticle(@Valid @RequestBody ArticleDTO articleDTO) {
        return ResponseEntity.ok(articleService.saveArticle(articleDTO));
    }

    @GetMapping("getArticles")
    public Page<Article> list(@RequestParam(name = "page", defaultValue = "0") int page,
                              @RequestParam(name = "size", defaultValue = "10") int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Article> pageResult = articleService.getArticlesPage(pageRequest);
        List<Article> articles = pageResult
                .stream()
                .collect(toList());
        return new PageImpl<>(articles, pageRequest, pageResult.getTotalElements());
    }

    @GetMapping("getArticlesByUserID")
    public Page<Article> listById(@RequestParam(name = "page", defaultValue = "0") int page,
                                  @RequestParam(name = "size", defaultValue = "10") int size,
                                  @RequestParam(name = "id") int id) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Article> pageResult = articleService.getArticlesPage(pageRequest);
        Predicate<Article> contain = (Article item) -> item.getUser().getUserID() == id;
        List<Article> articles = pageResult
                .stream()
                .filter(contain)
                .collect(toList());
        return new PageImpl<>(articles, pageRequest, pageResult.getTotalElements());
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

        article.get().setTitle(articleDTO.getTitle());
        article.get().setArticleText(articleDTO.getArticleText());

        return ResponseEntity.ok(articleService.modifyArticle(article.get()));
    }
}

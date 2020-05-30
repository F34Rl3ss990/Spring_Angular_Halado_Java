package com.bd.mzs.article.controller.dto;

import com.bd.mzs.article.entity.Article;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.util.Set;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({"hibernate_lazy_initializer", "handler"})
public class UserDTO {

    @NonNull
    @Length(min = 3)
    private String first_name;
    @NonNull
    @Length(min = 3)
    private String last_name;
    private Integer user_id;
    private Set<Article> articles;


}

package com.bd.mzs.article.controller.dto;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class ArticleDTO {
    @NonNull
    @Length(min = 5)
    private String title;
    private Date date_of_create;
    private Date date_of_modify;
    @NonNull
    @Length(min = 10)
    private String article_text;
    @NonNull
    private int user_id;

}

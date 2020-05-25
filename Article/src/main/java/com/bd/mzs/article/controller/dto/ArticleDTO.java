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
    @Length(min=5)
    private String title;
    private Date dateOfCreate;
    private Date dateOfModify;
    @NonNull
    @Length(min=10)
    private String articleText;
    @NonNull
    private int userID;

}

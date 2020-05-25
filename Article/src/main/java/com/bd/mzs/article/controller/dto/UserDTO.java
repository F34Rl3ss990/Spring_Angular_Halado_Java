package com.bd.mzs.article.controller.dto;

import jdk.jfr.Label;
import lombok.*;
import org.hibernate.validator.constraints.Length;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    @NonNull
    @Length(min=3)
    private String firstName;
    @NonNull
    @Length(min=3)
    private String lastName;

}

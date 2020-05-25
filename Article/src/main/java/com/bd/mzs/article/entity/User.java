package com.bd.mzs.article.entity;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "user_id", nullable = false)
    private Integer userID;

    @NonNull
    @Length(min=3)
    @Column (name= "first_name")
    private String firstName;

    @NonNull
    @Length(min=3)
    @Column(name = "last_name")
    private String lastName;

}

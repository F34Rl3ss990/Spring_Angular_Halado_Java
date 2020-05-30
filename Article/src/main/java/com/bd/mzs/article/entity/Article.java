package com.bd.mzs.article.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "article")
@EntityListeners(AuditingEntityListener.class)
public class Article implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id")
    private Integer article_id;

    @Column(name = "title")
    @NonNull
    @Length(min = 5)
    private String title;

    @Column(name = "date_of_create")
    @CreationTimestamp
    private Date date_of_create;

    @Column(name = "date_of_modify")
    @UpdateTimestamp
    private Date date_of_modify;

    @Column(name = "article")
    @NonNull
    @Length(min = 10)
    private String article_text;

    @NonNull
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;



}

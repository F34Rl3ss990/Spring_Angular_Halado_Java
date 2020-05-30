import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ArticleService} from "../article.service";
import {ActivatedRoute, Router} from "@angular/router";
import {Location} from "@angular/common";

@Component({
  selector: 'app-article-modify',
  templateUrl: './article-modify.component.html',
  styleUrls: ['./article-modify.component.css']
})
export class ArticleModifyComponent implements OnInit {
  angForm: FormGroup;
  article_id: Number;
  articles: any = {};

  constructor(private fb: FormBuilder, private as: ArticleService,
              private router: Router, private route: ActivatedRoute,
              private _location: Location) {
    this.createForm();
  }

  createForm() {
    this.angForm = this.fb.group({
      title: ['', [Validators.required, Validators.minLength(5)]],
      article_text: ['', [Validators.required, Validators.minLength(10)]],
    });
  }

  ngOnInit(): void {

    this.route.params.subscribe(params => {
      this.article_id = params.id;
    });
    this.route.params.subscribe(params => {
      this.as.getArticlesById(params.id).subscribe(res => {
        this.articles = res;
      });
    });

  }

  modifyArticle(title, article) {
    this.as.modifyArticle(title, article, this.article_id);
    this.router.navigate(['successful-article-modify', this.article_id]);
  }

  backClicked() {
    this._location.back();
  }

}

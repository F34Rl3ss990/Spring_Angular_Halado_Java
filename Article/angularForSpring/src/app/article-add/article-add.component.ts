import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {ArticleService} from "../article.service";
import {Location} from '@angular/common';

@Component({
  selector: 'app-article-add',
  templateUrl: './article-add.component.html',
  styleUrls: ['./article-add.component.css']
})
export class ArticleAddComponent implements OnInit {

  angForm: FormGroup;
  userId: Number;
  article: any = {};

  constructor(private fb: FormBuilder, private as: ArticleService,
              private router: Router, private route: ActivatedRoute,
              private _location: Location) {
    this.createForm();
  }

  createForm() {
    this.angForm = this.fb.group({
      title: ['', [Validators.required, Validators.minLength(5)]],
      articleText: ['', [Validators.required, Validators.minLength(10)]],
    });
  }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.userId = params.id;
    });
  }

  addArticle(title, article) {
    this.as.addArticle(title, article, this.userId).subscribe(res => {
      this.article = res;
      this.router.navigate(['successful-article-add/', this.article.articleId]);
    });
  }

  backClicked() {
    this._location.back();
  }

}

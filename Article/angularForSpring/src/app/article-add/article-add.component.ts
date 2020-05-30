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
  user_id: Number;
  article: any = {};

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
      this.user_id = params.id;
    });
  }

  addArticle(title, article) {
    this.as.addArticle(title, article, this.user_id).subscribe(res => {
      this.article = res;
      this.router.navigate(['successful-article-add/', this.user_id]);
    });
  }

  backClicked() {
    this._location.back();
  }

}

import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {ArticleService} from "../article.service";

@Component({
  selector: 'app-successfull-article-add',
  templateUrl: './successfull-article-add.component.html',
  styleUrls: ['./successfull-article-add.component.css']
})
export class SuccessfullArticleAddComponent implements OnInit {

  article: any = {};

  constructor(private router: Router,  private route: ActivatedRoute,
              private as: ArticleService) { }

  ngOnInit(): void {
    this.route.params.subscribe(params=> {
      this.as.getArticlesById(params.id).subscribe(res =>{
        this.article = res;
      });
      setTimeout(() => {
        this.router.navigate(['articles-get-by-user/', params.id]);
      }, 5000);  //5s
    });
  }
}

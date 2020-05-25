import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {UserService} from "../user.service";
import {ArticleService} from "../article.service";

@Component({
  selector: 'app-successfull-article-modify',
  templateUrl: './successfull-article-modify.component.html',
  styleUrls: ['./successfull-article-modify.component.css']
})
export class SuccessfullArticleModifyComponent implements OnInit {

  constructor(private router: Router,  private route: ActivatedRoute,
              private as: ArticleService) { }

  article: any = {};

  ngOnInit(): void {
    this.route.params.subscribe(params=> {
      this.as.getArticlesById(params.id).subscribe(res =>{
        this.article = res;
      });
      setTimeout(() => {
        this.router.navigate(['articles-get']);
      }, 5000);  //5s
    });
  }
}

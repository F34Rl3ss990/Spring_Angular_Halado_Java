import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {ArticleService} from "../article.service";

@Component({
  selector: 'app-successful-article-modify',
  templateUrl: './successful-article-modify.component.html',
  styleUrls: ['./successful-article-modify.component.css']
})
export class SuccessfulArticleModifyComponent implements OnInit {

  constructor(private router: Router, private route: ActivatedRoute,
              private as: ArticleService) {
  }

  article: any = {};

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.as.getArticlesById(params.id).subscribe(res => {
        this.article = res;
      });
      setTimeout(() => {
        this.router.navigate(['articles-get-by-user/', this.article.user.userID]);
      }, 5000);  //5s
    });
  }
}

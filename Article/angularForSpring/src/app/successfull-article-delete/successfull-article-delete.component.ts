import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {UserService} from "../user.service";
import {ArticleService} from "../article.service";

@Component({
  selector: 'app-successfull-article-delete',
  templateUrl: './successfull-article-delete.component.html',
  styleUrls: ['./successfull-article-delete.component.css']
})
export class SuccessfullArticleDeleteComponent implements OnInit {

  articleId: number;

  constructor(private router: Router,  private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.route.params.subscribe(params=> {
      this.articleId= params.id;
      setTimeout(() => {
        this.router.navigate(['articles-get-by-user/', params.id]);
      }, 5000);  //5s
    });

  }
}

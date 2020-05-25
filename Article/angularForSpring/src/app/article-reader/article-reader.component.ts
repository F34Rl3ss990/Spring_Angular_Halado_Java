import {Component, OnInit} from '@angular/core';
import {ArticleService} from "../article.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-article-reader',
  templateUrl: './article-reader.component.html',
  styleUrls: ['./article-reader.component.css']
})
export class ArticleReaderComponent implements OnInit {

  article: any = {};

  constructor(private as: ArticleService,
              private router: Router, private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.as.getArticlesById(params.id).subscribe(res => {
        this.article = res;
      });
    });
  }

}

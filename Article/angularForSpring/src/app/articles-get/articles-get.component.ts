import {Component, OnInit} from '@angular/core';
import {Article} from "../../models/article";
import {ArticleService} from "../article.service";
import {PageEvent} from "@angular/material/paginator";

@Component({
  selector: 'app-articles-get',
  templateUrl: './articles-get.component.html',
  styleUrls: ['./articles-get.component.css']
})
export class ArticlesGetComponent implements OnInit {

  totalElements: number = 0;
  loading: boolean;
  searchText: string;
  articles: Article[];

  constructor(private as: ArticleService) {
  }

  ngOnInit(): void {
    this.getList({page: "0", size: "10"});
  }

  private getList(req) {
    this.loading = true;
    this.as.getArticles(req).subscribe(data => {
      this.articles = data['content'];
      this.totalElements = data['totalElements'];
      this.loading = false;
    }, error => {
      this.loading = false;
    });
  }

  nextPage(event: PageEvent) {
    const req = {};
    req['page'] = event.pageIndex.toString();
    req['size'] = event.pageSize.toString();
    this.getList(req);
  }

}

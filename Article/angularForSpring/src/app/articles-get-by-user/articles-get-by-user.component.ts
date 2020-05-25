import {Component, OnInit} from '@angular/core';
import {Article} from "../../models/article";
import {ArticleService} from "../article.service";
import {ActivatedRoute, Router} from "@angular/router";
import {PageEvent} from "@angular/material/paginator";
import {UserService} from "../user.service";

@Component({
  selector: 'app-articles-get-by-user',
  templateUrl: './articles-get-by-user.component.html',
  styleUrls: ['./articles-get-by-user.component.css']
})
export class ArticlesGetByUserComponent implements OnInit {

  totalElements: number = 0;
  loading: boolean;
  searchText: string;
  articles: Article[];
  user: any = {};

  constructor(private as: ArticleService, private us: UserService,
              private router: Router, private route: ActivatedRoute) {
  }

  ngOnInit(): void {

    this.route.params.subscribe(params => {
      this.us.getUserById(params.id).subscribe(res => {
        this.user = res;
        console.log(res)
        this.getList({page: "0", size: "10", id: this.user.userID});
      });
    });
    console.log(this.user)

  }

  private getList(req) {
    this.loading = true;
    this.as.getArticlesByUserId(req).subscribe(data => {
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

  deleteArticle(id) {
    this.as.deleteArticle(id).subscribe(res => {
    });
    this.router.navigate(['successful-article-delete', id]);
  }

}

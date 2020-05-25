import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-successful-article-delete',
  templateUrl: './successful-article-delete.component.html',
  styleUrls: ['./successful-article-delete.component.css']
})
export class SuccessfulArticleDeleteComponent implements OnInit {

  articleId: number;

  constructor(private router: Router, private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.articleId = params.id;
      setTimeout(() => {
        this.router.navigate(['articles-get-by-user/', params.id]);
      }, 5000);  //5s
    });

  }
}

import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-successful-article-delete',
  templateUrl: './successful-article-delete.component.html',
  styleUrls: ['./successful-article-delete.component.css']
})
export class SuccessfulArticleDeleteComponent implements OnInit {

  article_id: number;

  constructor(private router: Router, private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.article_id = params.id;
      setTimeout(() => {
        this.router.navigate(['users-get/',]);
      }, 5000);  //5s
    });

  }
}

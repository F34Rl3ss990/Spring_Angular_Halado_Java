import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {UserService} from "../user.service";

@Component({
  selector: 'app-successfull-user-delete',
  templateUrl: './successfull-user-delete.component.html',
  styleUrls: ['./successfull-user-delete.component.css']
})
export class SuccessfullUserDeleteComponent implements OnInit {

  userId: number;

  constructor(private router: Router,  private route: ActivatedRoute,
              private us: UserService) { }

  ngOnInit(): void {
    this.route.params.subscribe(params=> {
        this.userId= params.id;
      });
      setTimeout(() => {
        this.router.navigate(['users-get']);
      }, 5000);  //5s
    }
}

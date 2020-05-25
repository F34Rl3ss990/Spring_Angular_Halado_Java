import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {UserService} from "../user.service";

@Component({
  selector: 'app-successfull-user-add',
  templateUrl: './successfull-user-add.component.html',
  styleUrls: ['./successfull-user-add.component.css']
})
export class SuccessfullUserAddComponent implements OnInit {

  user: any = {};

  constructor(private router: Router,  private route: ActivatedRoute,
              private us: UserService) { }

  ngOnInit(): void {
    this.route.params.subscribe(params=> {
      this.us.getUserById(params.id).subscribe(res =>{
        this.user = res;
      });
      setTimeout(() => {
        this.router.navigate(['users-get']);
      }, 5000);  //5s
    });
  }
}

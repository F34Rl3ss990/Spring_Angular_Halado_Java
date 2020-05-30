import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {UserService} from "../user.service";

@Component({
  selector: 'app-successful-user-delete',
  templateUrl: './successful-user-delete.component.html',
  styleUrls: ['./successful-user-delete.component.css']
})
export class SuccessfulUserDeleteComponent implements OnInit {

  user_id: number;

  constructor(private router: Router, private route: ActivatedRoute){
  }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.user_id = params.id;
    });
    setTimeout(() => {
      this.router.navigate(['users-get']);
    }, 5000);  //5s
  }
}

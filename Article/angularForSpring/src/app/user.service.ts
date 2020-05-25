import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  uri = 'http://localhost:8080/'

  constructor(private http: HttpClient) {
  }

  addUser(firstName, lastName) {
    const obj = {
      firstName: firstName,
      lastName: lastName
    }
    return this.http.post(`${this.uri}addUser`, obj);
  }

  getUserById(req) {
    return this.http.get(`${this.uri}getUserById/${req}`)
  }

  getUsers(req) {
    const params = req;
    return this.http.get(`${this.uri}getUsers`, {params});
  }

  deleteUser(id) {
    console.log(id)
    return this
      .http
      .delete(`${this.uri}deleteUser/${id}`);
  }

}

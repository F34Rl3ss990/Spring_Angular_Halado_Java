import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class ArticleService {

  uri = 'http://localhost:8080/'

  constructor(private http: HttpClient) {
  }

  addArticle(title, article, userID) {
    const obj = {
      title: title,
      article_text: article,
      user_id: userID
    }
    return this.http.post(`${this.uri}addArticle`, obj);
  }

  getArticles(req) {
    const params = req;
    return this.http.get(`${this.uri}getArticles`, {params});
  }

  getArticlesByUserId(req) {
    const params = req;
    return this.http.get(`${this.uri}getArticlesByUserID`, {params});
  }

  getArticlesById(id) {
    return this.http.get(`${this.uri}openArticle/${id}`);
  }

  deleteArticle(id) {
    return this
      .http
      .delete(`${this.uri}deleteArticle/${id}`);
  }

  modifyArticle(title, article, id) {
    const obj = {
      title: title,
      article_text: article,
      article_id: id
    }
    this.http.put(`${this.uri}modifyArticle/${id}`, obj).subscribe(
      res => console.log('Done'));
  }
}

import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {ArticleAddComponent} from "./article-add/article-add.component";
import {ArticleModifyComponent} from "./article-modify/article-modify.component";
import {ArticlesGetComponent} from "./articles-get/articles-get.component";
import {ArticlesGetByUserComponent} from "./articles-get-by-user/articles-get-by-user.component";
import {OpenerComponent} from "./opener/opener.component";
import {UserAddComponent} from "./user-add/user-add.component";
import {UsersGetComponent} from "./users-get/users-get.component";
import {ArticleReaderComponent} from "./article-reader/article-reader.component";
import {SuccessfullArticleAddComponent} from "./successfull-article-add/successfull-article-add.component";
import {SuccessfullArticleDeleteComponent} from "./successfull-article-delete/successfull-article-delete.component";
import {SuccessfullArticleModifyComponent} from "./successfull-article-modify/successfull-article-modify.component";
import {SuccessfullUserAddComponent} from "./successfull-user-add/successfull-user-add.component";
import {SuccessfullUserDeleteComponent} from "./successfull-user-delete/successfull-user-delete.component";


const routes: Routes = [
  {path: 'article-add/:id', component: ArticleAddComponent},
  {path: 'article-modify/:id', component: ArticleModifyComponent},
  {path: 'articles-get', component: ArticlesGetComponent},
  {path: 'articles-get-by-user/:id', component: ArticlesGetByUserComponent},
  {path: '', component: OpenerComponent},
  {path: 'user-add', component: UserAddComponent},
  {path: 'users-get', component: UsersGetComponent},
  {path: 'article-reader/:id', component: ArticleReaderComponent},
  {path: 'successful-article-add/:id', component: SuccessfullArticleAddComponent},
  {path: 'successful-article-delete', component: SuccessfullArticleDeleteComponent},
  {path: 'successful-article-modify', component: SuccessfullArticleModifyComponent},
  {path: 'successful-user-add/:id', component: SuccessfullUserAddComponent},
  {path: 'successful-user-delete', component: SuccessfullUserDeleteComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

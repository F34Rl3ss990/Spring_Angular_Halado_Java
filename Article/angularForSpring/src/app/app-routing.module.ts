import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {ArticleAddComponent} from "./article-add/article-add.component";
import {ArticleModifyComponent} from "./article-modify/article-modify.component";
import {ArticlesGetComponent} from "./articles-get/articles-get.component";
import {ArticlesGetByUserComponent} from "./articles-get-by-user/articles-get-by-user.component";
import {OpenerComponent} from "./opener/opener.component";
import {UserAddComponent} from "./user-add/user-add.component";
import {UsersGetComponent} from "./users-get/users-get.component";
import {ArticleReaderComponent} from "./article-reader/article-reader.component";
import {SuccessfulArticleAddComponent} from "./successful-article-add/successful-article-add.component";
import {SuccessfulArticleDeleteComponent} from "./successful-article-delete/successful-article-delete.component";
import {SuccessfulArticleModifyComponent} from "./successful-article-modify/successful-article-modify.component";
import {SuccessfulUserAddComponent} from "./successful-user-add/successful-user-add.component";
import {SuccessfulUserDeleteComponent} from "./successful-user-delete/successful-user-delete.component";


const routes: Routes = [
  {path: 'article-add/:id', component: ArticleAddComponent},
  {path: 'article-modify/:id', component: ArticleModifyComponent},
  {path: 'articles-get', component: ArticlesGetComponent},
  {path: 'articles-get-by-user/:id', component: ArticlesGetByUserComponent},
  {path: '', component: OpenerComponent},
  {path: 'user-add', component: UserAddComponent},
  {path: 'users-get', component: UsersGetComponent},
  {path: 'article-reader/:id', component: ArticleReaderComponent},
  {path: 'successful-article-add/:id', component: SuccessfulArticleAddComponent},
  {path: 'successful-article-delete/:id', component: SuccessfulArticleDeleteComponent},
  {path: 'successful-article-modify/:id', component: SuccessfulArticleModifyComponent},
  {path: 'successful-user-add/:id', component: SuccessfulUserAddComponent},
  {path: 'successful-user-delete/:id', component: SuccessfulUserDeleteComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}

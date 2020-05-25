import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';

import {SlimLoadingBarModule, SlimLoadingBarService} from 'ng2-slim-loading-bar';
import {HttpClientModule} from '@angular/common/http';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {UserService} from "./user.service";
import {ArticleService} from "./article.service";
import {UserAddComponent} from './user-add/user-add.component';
import {ArticleAddComponent} from './article-add/article-add.component';
import {ArticlesGetComponent} from './articles-get/articles-get.component';
import {ArticlesGetByUserComponent} from './articles-get-by-user/articles-get-by-user.component';
import {UsersGetComponent} from './users-get/users-get.component';
import {OpenerComponent} from './opener/opener.component';
import {ArticleModifyComponent} from './article-modify/article-modify.component';
import {FilterPipe} from "./filter.pipe";
import {MatPaginatorModule} from "@angular/material/paginator";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {ArticleReaderComponent} from './article-reader/article-reader.component';
import {SuccessfulArticleDeleteComponent} from './successful-article-delete/successful-article-delete.component';
import {SuccessfulUserDeleteComponent} from './successful-user-delete/successful-user-delete.component';
import {SuccessfulUserAddComponent} from './successful-user-add/successful-user-add.component';
import {SuccessfulArticleAddComponent} from './successful-article-add/successful-article-add.component';
import {SuccessfulArticleModifyComponent} from './successful-article-modify/successful-article-modify.component';
import {MDBBootstrapModule} from "angular-bootstrap-md";

@NgModule({
  declarations: [
    AppComponent,
    UserAddComponent,
    ArticleAddComponent,
    ArticlesGetComponent,
    ArticlesGetByUserComponent,
    UsersGetComponent,
    OpenerComponent,
    ArticleModifyComponent,
    FilterPipe,
    ArticleReaderComponent,
    SuccessfulArticleDeleteComponent,
    SuccessfulUserDeleteComponent,
    SuccessfulUserAddComponent,
    SuccessfulArticleAddComponent,
    SuccessfulArticleModifyComponent
  ],
  exports: [AppComponent],
  imports: [
    SlimLoadingBarModule,
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    NgbModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    MatPaginatorModule,
    MDBBootstrapModule.forRoot()
  ],
  providers: [SlimLoadingBarService, UserService, ArticleService],
  bootstrap: [AppComponent]
})
export class AppModule {
}

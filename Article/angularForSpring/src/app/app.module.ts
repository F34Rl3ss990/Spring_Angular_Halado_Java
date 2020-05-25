import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';

import {SlimLoadingBarModule, SlimLoadingBarService} from 'ng2-slim-loading-bar';
import {HttpClientModule} from '@angular/common/http';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {UserService} from "./user.service";
import {ArticleService} from "./article.service";
import { UserAddComponent } from './user-add/user-add.component';
import { ArticleAddComponent } from './article-add/article-add.component';
import { ArticlesGetComponent } from './articles-get/articles-get.component';
import { ArticlesGetByUserComponent } from './articles-get-by-user/articles-get-by-user.component';
import { UsersGetComponent } from './users-get/users-get.component';
import { OpenerComponent } from './opener/opener.component';
import { ArticleModifyComponent } from './article-modify/article-modify.component';
import {FilterPipe} from "./filter.pipe";
import {MatPaginatorModule} from "@angular/material/paginator";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import { ArticleReaderComponent } from './article-reader/article-reader.component';
import { SuccessfullArticleDeleteComponent } from './successfull-article-delete/successfull-article-delete.component';
import { SuccessfullUserDeleteComponent } from './successfull-user-delete/successfull-user-delete.component';
import { SuccessfullUserAddComponent } from './successfull-user-add/successfull-user-add.component';
import { SuccessfullArticleAddComponent } from './successfull-article-add/successfull-article-add.component';
import { SuccessfullArticleModifyComponent } from './successfull-article-modify/successfull-article-modify.component';

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
    SuccessfullArticleDeleteComponent,
    SuccessfullUserDeleteComponent,
    SuccessfullUserAddComponent,
    SuccessfullArticleAddComponent,
    SuccessfullArticleModifyComponent
  ],
  exports:[AppComponent],
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
  ],
  providers: [SlimLoadingBarService, UserService, ArticleService],
  bootstrap: [AppComponent]
})
export class AppModule { }

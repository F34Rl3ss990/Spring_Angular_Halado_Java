export interface Tree{
  first_name: String;
  last_name: String;
  user_id: Number;
  articles: Article[]

}

export interface Article {
  article_id: Number;
  title: String;
  date_of_create: String;
  date_of_modify: String;
  article_text: String;
  user: User[];
}
export interface User{
  first_name: String;
  last_name: String;
  user_id: Number;
}

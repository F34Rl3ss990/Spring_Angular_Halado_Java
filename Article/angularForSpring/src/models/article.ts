export interface Article{
  content: article[];
  totalElements: number;
}

export interface article {
  article_id: Number;
  title: String;
  date_of_create: Date;
  date_of_modify: Date;
  article_text: String;
  user_id: Number;
}


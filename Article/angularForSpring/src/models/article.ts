export interface Article{
  content: article[];
  totalElements: number;
}

export interface article {
  articleId: Number;
  title: String;
  dateOfCreate: Date;
  dateOfModify: Date;
  articleText: String;
  userId: Number;
}


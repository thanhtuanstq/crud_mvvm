package hns.vn.spring_crud.services;

import hns.vn.spring_crud.entity.Article;
import java.util.List;


public interface ArticleService {

	Article addArticle(Article article);

	List<Article> getArticles();
	
	Article updateArticle(Article article);
	
	void deleteArticle(Article article);
}

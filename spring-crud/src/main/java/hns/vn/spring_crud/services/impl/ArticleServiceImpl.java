package hns.vn.spring_crud.services.impl;

import hns.vn.spring_crud.entity.Article;
import hns.vn.spring_crud.services.ArticleService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

@Service("articleService")
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	ArticleDao aDao;
	
	public Article addArticle(Article article) {
		return aDao.save(article);
	}

	public List<Article> getArticles() {
		return aDao.queryAll();
	}
	
	public Article updateArticle(Article article) {
		return aDao.update(article);
	}

	public void deleteArticle(Article article) {
		aDao.delete(article);
	}

}

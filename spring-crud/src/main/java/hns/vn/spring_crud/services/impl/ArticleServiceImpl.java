package hns.vn.spring_crud.services.impl;

import hns.vn.spring_crud.dao.ArticleDao;
import hns.vn.spring_crud.entity.Article;
import hns.vn.spring_crud.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<Article> searchArticle(String keyword, int topicId) {
        return aDao.search(keyword,topicId);
    }

    @Override
    public List<Article> searchArticle(String keyword) {
        return aDao.search(keyword);
    }

}

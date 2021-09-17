package hns.vn.spring_crud.services.impl;

import hns.vn.spring_crud.entity.Article;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public class ArticleDao {

	@PersistenceContext
	private EntityManager em;

	@Transactional(readOnly = true)
	public List<Article> queryAll() {
		Query query = em.createQuery("SELECT l FROM Article l");
		List<Article> result = query.getResultList();
		return result;
	}

	@Transactional(readOnly = true)
	public Article get(Integer id) {
		return em.find(Article.class, id);
	}

	@Transactional
	public Article save(Article article) {
		em.persist(article);
		em.flush();
		return article;
	}
	
	@Transactional
	public Article update(Article article) {
		return em.merge(article);
	}

	@Transactional
	public void delete(Article article) {
		Article r = get(article.getId());
		if(r != null) {
			em.remove(r);
		}
	}

}

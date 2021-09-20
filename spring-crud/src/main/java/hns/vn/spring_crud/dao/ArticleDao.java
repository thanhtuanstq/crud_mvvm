package hns.vn.spring_crud.dao;

import hns.vn.spring_crud.entity.Article;
import hns.vn.spring_crud.entity.Topic;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;


@Repository
public class ArticleDao {

    @PersistenceContext
    private EntityManager em;

    @Transactional(readOnly = true)
    public List<Article> queryAll() {
        Query query = em.createQuery("SELECT l FROM Article l inner join Topic t " +
                "on l.topic.id = t.id where t.deleted = false");
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
        if (r != null) {
            em.remove(r);
        }
    }

    @Transactional(readOnly = true)
    public List<Article> search(String keyword, int topicId) {
       Query qr = em.createQuery("select a from Article a where a.title like :key and a.topic.id = :topicId");
        keyword = "%" + keyword + "%";
        qr.setParameter("key", keyword);
        qr.setParameter("topicId", topicId);
        List<Article> rs = qr.getResultList();

        return rs;
    }

    @Transactional(readOnly = true)
    public List<Article> search(String keyword ) {
        Query qr = em.createQuery("select a from Article a where a.title like :key");
        keyword = "%" + keyword + "%";
        qr.setParameter("key", keyword);
        List<Article> rs = qr.getResultList();

        return rs;
    }

}

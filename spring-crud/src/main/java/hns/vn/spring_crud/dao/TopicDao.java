package hns.vn.spring_crud.dao;

import hns.vn.spring_crud.entity.Topic;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;


@Repository
public class TopicDao {

    @PersistenceContext
    private EntityManager em;

    @Transactional(readOnly = true)
    public List<Topic> queryAll() {
        Query query = em.createQuery("SELECT t FROM Topic t where t.deleted = false");
        List<Topic> result = query.getResultList();
        return result;
    }

    @Transactional(readOnly = true)
    public Topic get(Integer id) {
        return em.find(Topic.class, id);
    }

    @Transactional
    public Topic save(Topic topic) {
        em.persist(topic);
        em.flush();
        return topic;
    }


    @Transactional
    public Topic update(Topic topic) {
        System.out.println("===========TopicDao =========: em = " + em + " | topic = " + topic);
        return em.merge(topic);
    }

    @Transactional
    public void delete(Topic topic) {
        Topic r = get(topic.getId());
        if (r != null) {
            topic.setDeleted(true);
            em.merge(topic);
        }
    }

    @Transactional(readOnly = true)
    public List<Topic> search(String keyword) {
        Query qr = em.createQuery("select t from Topic t where t.name like :key");
        keyword = "%" + keyword + "%";
        qr.setParameter("key", keyword);
        List<Topic> rs = qr.getResultList();

        return rs;
    }
}

package hns.vn.spring_crud.services.impl;

import hns.vn.spring_crud.entity.Topic;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public class TopicDao {

	@PersistenceContext
	private EntityManager em;

	@Transactional(readOnly = true)
	public List<Topic> queryAll() {
		Query query = em.createQuery("SELECT l FROM Topic l");
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
		if(r != null) {
			em.remove(r);
		}
	}

}

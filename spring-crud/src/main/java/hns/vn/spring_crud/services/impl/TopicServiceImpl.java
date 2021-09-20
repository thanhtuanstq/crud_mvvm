package hns.vn.spring_crud.services.impl;

import hns.vn.spring_crud.dao.TopicDao;
import hns.vn.spring_crud.entity.Topic;
import hns.vn.spring_crud.services.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("topicService")
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class TopicServiceImpl implements TopicService {

    @Autowired
    TopicDao dao;

    public Topic addTopic(Topic topic) {
        return dao.save(topic);
    }

    public List<Topic> getTopics() {
        return dao.queryAll();
    }

    public Topic updateTopic(Topic topic) {
        return dao.update(topic);
    }

    public void deleteTopic(Topic topic) {
        dao.delete(topic);
    }

    public List<Topic> searchTopic(String keyword) {
        return dao.search(keyword);
    }
}

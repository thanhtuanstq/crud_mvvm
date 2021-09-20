package hns.vn.spring_crud.services;

import hns.vn.spring_crud.entity.Topic;

import java.util.List;


public interface TopicService {

    Topic addTopic(Topic topic);

    List<Topic> getTopics();

    Topic updateTopic(Topic topic);

    void deleteTopic(Topic topic);

    List<Topic> searchTopic(String keyword);
}

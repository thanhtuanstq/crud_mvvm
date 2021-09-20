package hns.vn.spring_crud.mvvm.topic;

import hns.vn.spring_crud.entity.Topic;
import hns.vn.spring_crud.services.TopicService;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.lang.Strings;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;

import java.util.List;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class TopicIndexVM {

    @WireVariable
    private TopicService topicService;

    private ListModelList<Topic> topicListModel;
    private String name;
    private String description;
    private Boolean status;
    private Topic selectedTopic;
    private String keyword;
    private Boolean deleted;

    @Init
    public void init() {
        List<Topic> topicList = topicService.getTopics();
        topicListModel = new ListModelList<Topic>(topicList);
    }

    public ListModel<Topic> getTopicListModel() {
        return topicListModel;
    }

    public void setTopicListModel(ListModelList<Topic> topicListModel) {
        this.topicListModel = topicListModel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Topic getSelectedTopic() {
        return selectedTopic;
    }

    public void setSelectedTopic(Topic selectedTopic) {
        this.selectedTopic = selectedTopic;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    @Command
    public void addTopic() {
        if (Strings.isBlank(name)) {
            Clients.showNotification("Topic name is blank, please input topic!");
        } else {
            Topic topic = new Topic(name, description, status, deleted);
            topic.setDeleted(false);
            topic = topicService.addTopic(topic);
            topicListModel.add(topic);

            Clients.showNotification("Create topic success!");

            Executions.sendRedirect("index-topic.zul");
        }
    }

    @Command
    public void updateTopic(@BindingParam("topic") Topic topic) {
        System.out.println("============ MyViewModel updateTopic(...) ===========");
        System.out.println("============ MyViewModel topic = " + topic);

        Sessions.getCurrent().setAttribute("updateTopic", topic);

        Executions.sendRedirect("update-topic.zul");
    }

    @Command
    public void deleteTopic(@BindingParam("topic") Topic topic) {
        System.out.println("============ TopicIndexVM deleteTopic(...) ===========");

        Messagebox.show("Are you sure to delete?", "Confirm Dialog", Messagebox.OK | Messagebox.CANCEL,
            Messagebox.QUESTION, new org.zkoss.zk.ui.event.EventListener() {
            @Override
            public void onEvent(Event evt) throws InterruptedException {
                if (evt.getName().equals("onOK")) {
                    System.out.println("on OK -------");
                    topicService.deleteTopic(topic);
                    topicListModel.remove(topic);
                } else {
                    System.out.println("on CANCEL -------");
                }
            }
        });
    }

    @Command
    public void viewTopic(@BindingParam("topic") Topic topic) {
        System.out.println("============ MyViewModel viewTopic(...) ===========");
        System.out.println("============ MyViewModel topic = " + topic);

        Sessions.getCurrent().setAttribute("viewTopic", topic);

        Executions.sendRedirect("view-topic.zul");
    }

    @Command
    @NotifyChange("topicListModel")
    public void searchTopic() {
        System.out.println("============ MyViewModel searchTopic(...) ===========");
        System.out.println("============ MyViewModel keyword = " + keyword);
        List<Topic> topicList = topicService.searchTopic(keyword);

        topicListModel = new ListModelList<>(topicList);
    }
}

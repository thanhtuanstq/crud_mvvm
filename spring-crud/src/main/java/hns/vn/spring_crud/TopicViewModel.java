package hns.vn.spring_crud;

import hns.vn.spring_crud.entity.Topic;
import hns.vn.spring_crud.services.TopicService;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.lang.Strings;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;

import java.util.List;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class TopicViewModel {

	@WireVariable
	private TopicService myService;
	private ListModelList<Topic> topicListModel;
	private String name;
	private String description;
	private Boolean status;
	private Topic selectedTopic;
	
	@Init
	public void init() {
		List<Topic> topicList = myService.getTopics();
		topicListModel = new ListModelList<Topic>(topicList);
	}

	public ListModel<Topic> getTopicListModel() {
		return topicListModel;
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

	public void setStatus(Boolean status) {
	    this.status = status; }
	  
	public Boolean getStatus() { 
	    return status; }

	@Command
	public void addTopic() {
		if(Strings.isBlank(name)) {
			Clients.showNotification("Topic name is blank, please input topic!");
		} else {
		Topic topic = new Topic(name,description,status);
		topic = myService.addTopic(topic);
		topicListModel.add(topic);
		
		Clients.showNotification("Create topic success!");
		
		Executions.sendRedirect("index-topic.zul");
		}	
	}
	
	@Command 
	public void updateTopic(@BindingParam("topic") Topic topic){
		System.out.println("============ MyViewModel updateTopic(...) ===========");
		System.out.println("============ MyViewModel topic = " + topic);

		Sessions.getCurrent().setAttribute("updateTopic", topic);

		Executions.sendRedirect("update-topic.zul");
	}

	@Command
	public void deleteTopic(@BindingParam("topic") Topic topic) {
		myService.deleteTopic(topic);
		topicListModel.remove(topic);
	}

	@Command
	public void viewTopic(@BindingParam("topic") Topic topic) {
		System.out.println("============ MyViewModel viewTopic(...) ===========");
		System.out.println("============ MyViewModel topic = " + topic);

		Sessions.getCurrent().setAttribute("viewTopic", topic);

		Executions.sendRedirect("view-topic.zul");
	}
}

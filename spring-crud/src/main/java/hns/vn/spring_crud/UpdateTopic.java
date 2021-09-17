package hns.vn.spring_crud;

import hns.vn.spring_crud.entity.Topic;
import hns.vn.spring_crud.services.TopicService;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class UpdateTopic {

    @WireVariable
    private TopicService myService;

    Topic topic;

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    @Init
    public void init(@ContextParam(ContextType.VIEW) Component view) {
        System.out.println("============ UpdateTopicVM init(...) ===========");
        //Selectors.wireComponents(view, this, false);
        this.topic = (Topic) Sessions.getCurrent().getAttribute("updateTopic");
    }

    @Command
    public void updateTopic(){
        System.out.println("============ UpdateTopicVM updateTopic(...) ===========");
        System.out.println("============ UpdateTopicVM topic = " + topic);
        System.out.println("============ UpdateTopicVM myService = " + (myService == null));

        myService.updateTopic(topic);

        Executions.sendRedirect("index-topic.zul");
    }
}



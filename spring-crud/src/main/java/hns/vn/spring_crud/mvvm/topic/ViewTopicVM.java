package hns.vn.spring_crud.mvvm.topic;

import hns.vn.spring_crud.entity.Topic;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.annotation.Command;


public class ViewTopicVM {


    Topic topic;

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    @Init
    public void init(@ContextParam(ContextType.VIEW) Component view) {
        System.out.println("============ ViewTopicVM init(...) ===========");
        this.topic = (Topic) Sessions.getCurrent().getAttribute("viewTopic");
    }

    @Command
    public void closeThis() {
        Executions.sendRedirect("index-topic.zul");
    }
}



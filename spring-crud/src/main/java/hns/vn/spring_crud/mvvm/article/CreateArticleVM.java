package hns.vn.spring_crud.mvvm.article;

import hns.vn.spring_crud.entity.Article;
import hns.vn.spring_crud.entity.Topic;
import hns.vn.spring_crud.services.ArticleService;
import hns.vn.spring_crud.services.TopicService;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.lang.Strings;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;

import java.util.List;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class CreateArticleVM {

    Article article;

    List<Topic> topicList;

    Topic selectedTopic;

    @WireVariable
    private ArticleService articleService;

    @WireVariable
    private TopicService topicService;

    public Article getArticle() {
        return article;
    }

    public Topic getSelectedTopic() {
        return selectedTopic;
    }

    public void setSelectedTopic(Topic selectedTopic) {
        this.selectedTopic = selectedTopic;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public List<Topic> getTopicList() {
        return topicList;
    }

    public void setTopicList(List<Topic> topicList) {
        this.topicList = topicList;
    }

    @Init
    public void init(@ContextParam(ContextType.VIEW) Component view) {
        System.out.println("============ CreateTopicVM init(...) ===========");
        this.article = new Article();
        this.topicList = topicService.getTopics();
    }

    @Command
    public void createArticle() {
        System.out.println("============ CreateTopicVM createArticle(...) ===========");

        if (Strings.isBlank(this.article.getTitle())) {
            Clients.showNotification("Title name is blank, please input title!");
        } else {
            this.article.setTopic(this.selectedTopic);
            articleService.addArticle(article);

            Clients.showNotification("Create article success!");

            Executions.sendRedirect("index-article.zul");
        }
    }
}



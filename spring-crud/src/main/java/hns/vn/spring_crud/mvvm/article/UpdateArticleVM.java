package hns.vn.spring_crud.mvvm.article;

import hns.vn.spring_crud.entity.Article;
import hns.vn.spring_crud.entity.Topic;
import hns.vn.spring_crud.services.ArticleService;
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

import java.util.List;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class UpdateArticleVM {

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

    public void setArticle(Article article) {
        this.article = article;
    }

    public List<Topic> getTopicList() {
        return topicList;
    }

    public void setTopicList(List<Topic> topicList) {
        this.topicList = topicList;
    }

    public Topic getSelectedTopic() {
        return selectedTopic;
    }

    public void setSelectedTopic(Topic selectedTopic) {
        this.selectedTopic = selectedTopic;
    }

    @Init
    public void init(@ContextParam(ContextType.VIEW) Component view) {
        System.out.println("============ UpdateTopicVM init(...) ===========");

        this.topicList = topicService.getTopics();
        this.article = (Article) Sessions.getCurrent().getAttribute("updateArticle");
        this.selectedTopic = this.article.getTopic();
    }

    @Command
    public void updateArticle() {
        System.out.println("============ UpdateTopicVM updateTopic(...) ===========");
        System.out.println("============ UpdateTopicVM topic = " + article);
        System.out.println("============ UpdateTopicVM selectedTopic = " + selectedTopic.getName());

        this.article.setTopic(this.selectedTopic);
        articleService.updateArticle(article);

        Executions.sendRedirect("index-article.zul");
    }
}



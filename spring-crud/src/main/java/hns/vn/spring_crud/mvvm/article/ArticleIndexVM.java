package hns.vn.spring_crud.mvvm.article;

import hns.vn.spring_crud.entity.Article;
import hns.vn.spring_crud.entity.Topic;
import hns.vn.spring_crud.services.ArticleService;
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
public class ArticleIndexVM {

    @WireVariable
    private ArticleService articleService;

    @WireVariable
    private TopicService topicService;

    private ListModelList<Article> articleListModel;
    private String title;
    private String description;
    private String content;
    private Boolean publish;
    private String keyword;
    List<Topic> topicList;
    Topic selectedTopic;

    @Init
    public void init() {
        List<Article> articleList = articleService.getArticles();
        articleListModel = new ListModelList<Article>(articleList);

        this.topicList = topicService.getTopics();
    }

    public ListModel<Article> getArticleListModel() {
        return articleListModel;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getPublish() {
        return publish;
    }

    public void setPublish(Boolean publish) {
        this.publish = publish;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
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

    @Command
    public void addArticle() {

            Executions.sendRedirect("index-article.zul");

    }

    @Command
    public void updateArticle(@BindingParam("article") Article article) {
        Sessions.getCurrent().setAttribute("updateArticle", article);

        Executions.sendRedirect("update-article.zul");
    }

    @Command
    public void deleteArticle(@BindingParam("article") Article article) {

        Messagebox.show("Are you sure to delete?", "Confirm Dialog", Messagebox.OK | Messagebox.CANCEL,
            Messagebox.QUESTION, new org.zkoss.zk.ui.event.EventListener() {
            @Override
            public void onEvent(Event evt) throws InterruptedException {
                if (evt.getName().equals("onOK")) {
                    System.out.println("on OK -------");
                    articleService.deleteArticle(article);
                    articleListModel.remove(article);
                } else {
                    System.out.println("on CANCEL -------");
                }
            }
        });
    }

    @Command
    public void viewArticle(@BindingParam("article") Article article) {
        Sessions.getCurrent().setAttribute("viewArticle", article);

        Executions.sendRedirect("view-article.zul");
    }

    @Command
    @NotifyChange("articleListModel")
    public void searchArticle( ) {
        System.out.println("============ ArticleViewModel searchArticle(...) ===========");
        System.out.println("============ ArticleViewModel keyword = " + keyword);

        if(selectedTopic == null) {
            List<Article> articleList = articleService.searchArticle(keyword);
            articleListModel = new ListModelList<>(articleList);
        } else {
            List<Article> articleList = articleService.searchArticle(keyword,selectedTopic.getId());
            articleListModel = new ListModelList<>(articleList);
        }
    }

}

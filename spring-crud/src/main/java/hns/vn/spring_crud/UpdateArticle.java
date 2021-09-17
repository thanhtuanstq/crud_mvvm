package hns.vn.spring_crud;

import hns.vn.spring_crud.entity.Article;
import hns.vn.spring_crud.services.ArticleService;
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
public class UpdateArticle {

    @WireVariable
    private ArticleService articleService;

    Article article;

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    @Init
    public void init(@ContextParam(ContextType.VIEW) Component view) {
        System.out.println("============ UpdateTopicVM init(...) ===========");
        this.article = (Article) Sessions.getCurrent().getAttribute("updateArticle");
    }

    @Command
    public void updateArticle(){
        System.out.println("============ UpdateTopicVM updateTopic(...) ===========");
        System.out.println("============ UpdateTopicVM topic = " + article);
        System.out.println("============ UpdateTopicVM myService = " + (articleService == null));

        articleService.updateArticle(article);

        Executions.sendRedirect("index-article.zul");
    }
}



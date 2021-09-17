package hns.vn.spring_crud;

import hns.vn.spring_crud.entity.Article;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.annotation.Command;


public class ViewArticle {


	Article article;

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    @Init
    public void init(@ContextParam(ContextType.VIEW) Component view) {
        System.out.println("============ ViewTopicVM init(...) ===========");
        this.article = (Article) Sessions.getCurrent().getAttribute("viewArticle");
    }

    @Command
    public void closeThis() {
        Executions.sendRedirect("index-article.zul");
    }
}



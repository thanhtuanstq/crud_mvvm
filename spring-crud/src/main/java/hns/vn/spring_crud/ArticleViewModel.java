package hns.vn.spring_crud;

import hns.vn.spring_crud.entity.Article;
import hns.vn.spring_crud.services.ArticleService;
import java.util.List;
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

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class ArticleViewModel {

	@WireVariable
	private ArticleService articleService;
	private ListModelList<Article> articleListModel;
	private String title;
	private String description;
	private String content;

	@Init
	public void init() {
		List<Article> articleList = articleService.getArticles();
		articleListModel = new ListModelList<Article>(articleList);
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
	
	
	  public void setContent(String content) { 
		  this.content = content; }
	 
	  public String getContent() { 
		  return content; }
	

	@Command
	public void addArticle() {
		if(Strings.isBlank(title)) {
			Clients.showNotification("Title name is blank, please input title!");
		} else {
			Article article = new Article(title,description,content);
		article = articleService.addArticle(article);
		articleListModel.add(article);
		
		Clients.showNotification("Create article success!");
		
		Executions.sendRedirect("index-article.zul");
		}
		
	}
	
	@Command 
	public void updateArticle(@BindingParam("article") Article article){	
		Sessions.getCurrent().setAttribute("updateArticle", article);

		Executions.sendRedirect("update-article.zul");
	}

	@Command
	public void deleteArticle(@BindingParam("article") Article article) {
		articleService.deleteArticle(article);
		articleListModel.remove(article);
	}
	
	@Command
	public void viewArticle(@BindingParam("article") Article article) {
		Sessions.getCurrent().setAttribute("viewArticle", article);

		Executions.sendRedirect("view-article.zul");
	}

}

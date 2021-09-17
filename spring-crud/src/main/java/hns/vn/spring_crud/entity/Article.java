package hns.vn.spring_crud.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "article")
public class Article implements Serializable, Cloneable {
	private static final long serialVersionUID = 1L;

	private String title;
	private String description;
	private String content;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;

	public Article() {
	}

	public Article(String title) {
		this.title = title;
	}

	public Article(String title, String description, String content) {
		this.title = title;
		this.description = description;
		this.content = content;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
	 

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Article other = (Article) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}

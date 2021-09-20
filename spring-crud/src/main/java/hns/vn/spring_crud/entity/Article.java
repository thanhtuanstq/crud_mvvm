package hns.vn.spring_crud.entity;

import org.zkoss.bind.annotation.Command;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "article")
public class Article implements Serializable, Cloneable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    private String title;
    private String description;
    private String content;
    private Boolean publish;

    @ManyToOne
    @JoinColumn(name="topic_id", nullable=false)
    private Topic topic;

    public Article() {
    }

    public Article(String title) {
        this.title = title;
    }

    public Article(String title, String description, String content, Boolean publish) {
        this.title = title;
        this.description = description;
        this.content = content;
        this.publish = publish;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
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

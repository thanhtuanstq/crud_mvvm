package hns.vn.spring_crud.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "topic")
public class Topic implements Serializable, Cloneable {
	private static final long serialVersionUID = 1L;

	private String name;
	private String description;
	private Boolean status;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;

	public Topic() {
	}

	public Topic(String name) {
		this.name = name;
	}

	public Topic(String name, String description, Boolean status) {
		this.name = name;
		this.description = description;
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
	 public void setStatus(Boolean status) { 
		 this.status = status; }
	 
	  public Boolean getStatus() { 
		  return status; }
	 

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
		Topic other = (Topic) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Topic{" +
				"name='" + name + '\'' +
				", description='" + description + '\'' +
				", status=" + status +
				", id=" + id +
				'}';
	}
}

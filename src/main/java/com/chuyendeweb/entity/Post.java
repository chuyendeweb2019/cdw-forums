package com.chuyendeweb.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.chuyendeweb.util.TimeUtil;

@Entity
@Table(name="t_post")
public class Post implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
	private String body;
	
	@Column(name="date_created")
	private Timestamp dateCreated;
	
    @ManyToMany
    @JoinTable(name = "t_category",
    	joinColumns = @JoinColumn(name = "category_id"),
    	inverseJoinColumns = @JoinColumn(name = "id"))
	@Column(name="category_id")
	private Collection<Category> category;
	
    @ManyToMany
    @JoinTable(name = "t_user",
	joinColumns = @JoinColumn(name = "user_id"),
	inverseJoinColumns = @JoinColumn(name = "id"))
	@Column(name="user_id")
	private Collection<User> user;
	
	@Column(name="comment_count")
	private Long commentCount;
	
	@Column(name="hit_count")
	private Long hitCount;

	public Post() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Timestamp getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Timestamp dateCreated) {
		this.dateCreated = dateCreated;
	}
	@JoinTable(name = "t_category",
	    	joinColumns = @JoinColumn(name = "category_id"),
	    	inverseJoinColumns = @JoinColumn(name = "id"))
	public Long getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(Long commentCount) {
		this.commentCount = commentCount;
	}

	public Long getHitCount() {
		return hitCount;
	}

	public void setHitCount(Long hitCount) {
		this.hitCount = hitCount;
	}
	
	

	public Collection<Category> getCategory() {
		return category;
	}

	public void setCategory(Collection<Category> category) {
		this.category = category;
	}

	public Collection<User> getUser() {
		return user;
	}

	public void setUser(Collection<User> user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", title=" + title + ", body=" + body + ", dateCreated=" + dateCreated + ", category="
				+ category + ", user=" + user + ", commentCount=" + commentCount + ", hitCount=" + hitCount + "]";
	}

	public String numDaysAgo() {
		return TimeUtil.numDaysAgo(this.getDateCreated());
	}

	public String dateFormat() {
		return TimeUtil.dateFormat(this.getDateCreated());
	}

}

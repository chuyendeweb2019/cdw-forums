package vn.cdw.cdwforums.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_post")
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String title;
	private String body;
	@Column(name = "date_created")
	private Date dateCreated;

	@OneToOne
	@JoinColumn(name = "user_id")
	private User userId;

	@OneToOne
	@JoinColumn(name = "category_id")
	private Category categoryId;
	@Column(name = "comment_count")
	private int commentCount;
	@Column(name = "hit_count")
	private int hitCount;

	public Post() {
	}

	public Post(int id, String title, String body, Date dateCreated, User userId, Category categoryId, int commentCount,
			int hitCount) {
		super();
		this.id = id;
		this.title = title;
		this.body = body;
		this.dateCreated = dateCreated;
		this.userId = userId;
		this.categoryId = categoryId;
		this.commentCount = commentCount;
		this.hitCount = hitCount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public User getUserId() {
		return userId;
	}

	public void setUserId(User userId) {
		this.userId = userId;
	}

	public Category getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Category categoryId) {
		this.categoryId = categoryId;
	}

	public int getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}

	public int getHitCount() {
		return hitCount;
	}

	public void setHitCount(int hitCount) {
		this.hitCount = hitCount;
	}
	
}

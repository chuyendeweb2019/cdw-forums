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
@Table(name = "t_comment")
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String body;
	@OneToOne
	@JoinColumn(name = "post_id")
	private Post postId;
	@OneToOne
	@JoinColumn(name = "user_id")
	private User userId;
	@Column(name = "date_created")
	private Date dateCreated;
public Comment() {
}
public Comment(int id, String body, Post postId, User userId, Date dateCreated) {
	super();
	this.id = id;
	this.body = body;
	this.postId = postId;
	this.userId = userId;
	this.dateCreated = dateCreated;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getBody() {
	return body;
}
public void setBody(String body) {
	this.body = body;
}
public Post getPostId() {
	return postId;
}
public void setPostId(Post postId) {
	this.postId = postId;
}
public User getUserId() {
	return userId;
}
public void setUserId(User userId) {
	this.userId = userId;
}
public Date getDateCreated() {
	return dateCreated;
}
public void setDateCreated(Date dateCreated) {
	this.dateCreated = dateCreated;
}

}

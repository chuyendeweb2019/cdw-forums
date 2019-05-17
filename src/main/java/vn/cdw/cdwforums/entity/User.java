package vn.cdw.cdwforums.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "t_user")
public class User implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "userId")
	private List<Post> posts;

	public List<Post> getPost() {
		return posts;
	}

	public void setPost(List<Post> posts) {
		this.posts = posts;
	}

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "userId")

	private List<EducationPost> educationPost;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "userId")

	private List<Comment> comment;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "userId")

	private List<VerificationToken> verificationToken;

	@Column(name = "username")
	private String userName;
	@Column(name = "password")
	private String passWord;
	private String email;
	private int activated;
	@Column(name = "date_created")
	private Date dateCreated;
	@Column(name = "avatar_location")
	private String avatarLocation;
	private String bio;

	@ManyToMany
	private Set<Role> roles;

	public User() {
	}

	public User(int id, List<Post> posts, List<EducationPost> educationPost, List<Comment> comment,
			List<VerificationToken> verificationToken, String userName, String passWord, String email, int activated,
			Date dateCreated, String avatarLocation, String bio, Set<Role> roles) {
		super();
		this.id = id;
		this.posts = posts;
		this.educationPost = educationPost;
		this.comment = comment;
		this.verificationToken = verificationToken;
		this.userName = userName;
		this.passWord = passWord;
		this.email = email;
		this.activated = activated;
		this.dateCreated = dateCreated;
		this.avatarLocation = avatarLocation;
		this.bio = bio;
		this.roles = roles;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getActivated() {
		return activated;
	}

	public void setActivated(int activated) {
		this.activated = activated;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getAvatarLocation() {
		return avatarLocation;
	}

	public void setAvatarLocation(String avatarLocation) {
		this.avatarLocation = avatarLocation;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public List<EducationPost> getEducationPost() {
		return educationPost;
	}

	public void setEducationPost(List<EducationPost> educationPost) {
		this.educationPost = educationPost;
	}

	public List<Comment> getComment() {
		return comment;
	}

	public void setComment(List<Comment> comment) {
		this.comment = comment;
	}

	public List<VerificationToken> getVerificationToken() {
		return verificationToken;
	}

	public void setVerificationToken(List<VerificationToken> verificationToken) {
		this.verificationToken = verificationToken;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

}

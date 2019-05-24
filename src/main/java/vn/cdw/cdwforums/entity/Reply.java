package vn.cdw.cdwforums.entity;


import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import vn.cdw.cdwforums.util.ForumConstants;

@Entity
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Size(min = ForumConstants.TEXT_LENGTH_MIN, max = ForumConstants.TEXT_LENGTH_MAX)

    private String text;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfPublication;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfChange;

    @ManyToOne
    @JoinColumn(name = "CHANGED_USER_ID")
    private User changedUser;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne
    @JoinColumn(name = "TOPIC_ID")
    private Topic topic;

    @ManyToOne
    @JoinColumn(name = "REPLY_ID")
    private Reply replyTo;

    @OneToMany(mappedBy = "replyTo", cascade = CascadeType.ALL)
    private Set<Reply> replies = new HashSet<>();

    public boolean getIsChanged() {
        return Objects.nonNull(dateOfChange);
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getDateOfPublication() {
		return dateOfPublication;
	}

	public void setDateOfPublication(Date dateOfPublication) {
		this.dateOfPublication = dateOfPublication;
	}

	public Date getDateOfChange() {
		return dateOfChange;
	}

	public void setDateOfChange(Date dateOfChange) {
		this.dateOfChange = dateOfChange;
	}

	public User getChangedUser() {
		return changedUser;
	}

	public void setChangedUser(User changedUser) {
		this.changedUser = changedUser;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}

	public Reply getReplyTo() {
		return replyTo;
	}

	public void setReplyTo(Reply replyTo) {
		this.replyTo = replyTo;
	}

	public Set<Reply> getReplies() {
		return replies;
	}

	public void setReplies(Set<Reply> replies) {
		this.replies = replies;
	}

	public Reply() {
		super();
	}
    
    // get set
    
    
}
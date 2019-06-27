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

import com.fasterxml.jackson.annotation.JsonIgnore;

import vn.cdw.cdwforums.util.ForumConstants;

@Entity
public class Section {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Size(min = ForumConstants.TITLE_LENGTH_MIN, max = ForumConstants.TITLE_LENGTH_MAX)
    private String title;

    @Size(min = ForumConstants.TEXT_LENGTH_MIN, max = ForumConstants.TEXT_LENGTH_MAX)
    private String text;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfPublication;


    @OneToMany(mappedBy = "section", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Topic> topics;

    @ManyToOne
    @JoinColumn(name = "PARENT_ID")    
    @JsonIgnore
    private Section parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Section> subsections = new HashSet<>();

    public boolean getIsParent() {
        return (Objects.nonNull(subsections) && subsections.size() > 0);
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

	public Set<Topic> getTopics() {
		return topics;
	}

	public void setTopics(Set<Topic> topics) {
		this.topics = topics;
	}

	public Section getParent() {
		return parent;
	}

	public void setParent(Section parent) {
		this.parent = parent;
	}

	public Set<Section> getSubsections() {
		return subsections;
	}

	public void setSubsections(Set<Section> subsections) {
		this.subsections = subsections;
	}

	public Section() {
		super();
	}
    
    
}

package vn.cdw.cdwforums.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Photo {
	
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @JsonIgnore
    @Lob
    private byte[] photo;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    public Photo(byte[] photo, User user) {
        this.photo = photo;
        this.user = user;
    }
    
	public Photo() {
		super();
	}

	
	public Photo(Long id, byte[] photo, User user) {
		super();
		this.id = id;
		this.photo = photo;
		this.user = user;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
    
    
}

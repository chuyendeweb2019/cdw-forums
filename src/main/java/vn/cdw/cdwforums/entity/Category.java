package vn.cdw.cdwforums.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "T_CATEGORY")
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int weight;
	private String name;
	@Column(name = "display_name")
	private String displayName;
	@Column(name = "username")
	private String userName;
	@Column(name = "date_created")
	private Date dateCreate;
	
	
	public Category(int id, int weight, String name, String displayName, String userName, Date dateCreate) {
		super();
		this.id = id;
		this.weight = weight;
		this.name = name;
		this.displayName = displayName;
		this.userName = userName;
		this.dateCreate = dateCreate;
	}
	public Category() {
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Date getDateCreate() {
		return dateCreate;
	}
	public void setDateCreate(Date dateCreate) {
		this.dateCreate = dateCreate;
	}
	

}
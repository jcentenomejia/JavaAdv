package fr.epita.iamcore.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="IDENTITIES")
public class Identity {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="IDENTITY_ID")
	private long id;
	
	@Column(name="IDENTITY_UID")
	private String uid;
	
	@Column(name="IDENTITY_DISPLAYNAME")
	private String displayname;
	
	@Column(name="IDENTITY_EMAIL")
	private String email;
	
	@Column(name="IDENTITY_BIRTHDATE")
	private String birthDate;
	
	@Column(name="PASSWORD")
	private String password;
	
	@Column(name="USER_TYPE")
	private String userType;
	
	public Identity(){
		//Default
	}
	
	//constructor using fields
	public Identity(String uid, String displayname, String email, String birthDate, String password, String userType) {
		
		this.uid = uid;
		this.displayname = displayname;
		this.email = email;
		this.birthDate = birthDate;
		this.password = password;
		this.userType = userType;
	}

	//getters and setters for fields
	
	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getDisplayname() {
		return displayname;
	}

	public void setDisplayname(String displayname) {
		this.displayname = displayname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	
	public void setPassword(String password){
		this.password = password;
	}
	
	public String getPassword(){
		return this.password;
	}
	
	public void setUserType(String userType){
		this.userType = userType;
	}
	
	public String getUserType(){
		return this.userType;
	}
	
	@Override
	public String toString() {
		return "Identity [uid=" + uid + ", displayname=" + displayname + ", email=" + email + ", bithdate: " + birthDate + "]";
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id){
		this.id = id;
	}
}

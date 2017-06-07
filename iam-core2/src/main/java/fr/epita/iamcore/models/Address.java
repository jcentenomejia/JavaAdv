package fr.epita.iamcore.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="ADDRESS")
public class Address {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ADDRESS_ID")
	private long addressId;
	
	@Column(name="ADDRESS_DESC")
	private String addressDesc;
	
	@Column(name="ADDRESS_CITY")
	private String city;
	
	@Column(name="ADDRESS_ZIPCODE")
	private String zipcode;
	
	@Column(name="ADDRESS_COUNTRY")
	private String country;
	
	@ManyToOne(cascade=CascadeType.ALL)
	private Identity identity;
	
	public Address(long addressId, String addressDesc, String city, String zipcode, String country, Identity identity) {
		
		this.addressId = addressId;
		this.addressDesc = addressDesc;
		this.city = city;
		this.zipcode = zipcode;
		this.country = country;
		this.identity = identity;
	}
	
	public Address() {
		//Default constructor
	}
		
	public long getAddressId() {
		return addressId;
	}

	public String getAddressDesc() {
		return addressDesc;
	}

	public void setAddressDesc(String addressDesc) {
		this.addressDesc = addressDesc;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Identity getIdentity() {
		return identity;
	}

	public void setIdentity(Identity idenity) {
		this.identity = idenity;
	}

	@Override
	public String toString() {
		return "Address [addressId=" + addressId + ", addressDesc=" + addressDesc + ", city=" + city + ", zipcode="
				+ zipcode + ", country=" + country + ", identity=" + identity + "]";
	}
}

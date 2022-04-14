package fr.mns.jee.projet.model;

import java.io.Serializable;


public class City implements Serializable{

	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private String country;
	private Integer postCode;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public Integer getPostCode() {
		return postCode;
	}
	public void setPostCode(Integer postCode) {
		this.postCode = postCode;
	}
}

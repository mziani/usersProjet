package fr.mns.jee.projet.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "address")
public class Address implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false, unique=true)
	private Long id;

	@Column(name = "number")
	private Integer number;

	@Column(name = "street")
	private String street;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idcity")
	private City city;

	public Long getId() {
			return id;
		}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public City getCity() {
		return city;
	}
	public void setCity(City city) {
		this.city = city;
	}

}

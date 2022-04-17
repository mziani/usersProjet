package fr.mns.jee.projet.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "city")
public class City implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false, unique=true)
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "postcode")
	private Integer postCode;

	@ManyToOne
	@JoinColumn(name = "idregion")
	private Region region;

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
	public Integer getPostCode() {
		return postCode;
	}
	public void setPostCode(Integer postCode) {
			this.postCode = postCode;
		}
	public Region getRegion() { return region; }
	public void setRegion(Region region) { this.region = region; }
}

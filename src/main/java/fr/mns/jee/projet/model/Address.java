package fr.mns.jee.projet.model;

import java.io.Serializable;



public class Address implements Serializable{


		private static final long serialVersionUID = 1L;
		private Long id;
		private Integer number;
		private String street;
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

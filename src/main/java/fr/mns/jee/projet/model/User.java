package fr.mns.jee.projet.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.*;

@Entity
@Table(name="users")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false, unique = true, updatable = false)
	private Long id;
	
	@Column(name = "firstname")
	private String firstName;

	@Column(name = "lastname")
	private String lastName;

	@Column(name = "birthdate")
	private LocalDate birthDate;

	@Column(name = "phone_number")
	private String phoneNumber;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "gender")
	private Gender gender;

	@ManyToOne
	@JoinColumn(name = "idaddress")
	private Address address;

	@Column(name = "username",unique = true)
	private String username;

	@Column(name = "password")
	private String password;

	@Column(name = "email",unique = true)
	private String email;

	@Column(name = "enabled")
	private Boolean isEnabled = true;

	@Enumerated(EnumType.STRING)
	@ElementCollection(targetClass = Role.class)
	@CollectionTable(name = "user_role")
	@Column(name="role")
	private Set<Role> roles = new HashSet<>();

	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }
	public String getFirstName() { return firstName; }
	public void setFirstName(String firstName) { this.firstName = firstName; }
	public String getLastName() { return lastName; }
	public void setLastName(String lastName) { this.lastName = lastName; }
	public LocalDate getBirthDate() { return birthDate; }
	public void setBirthDate(LocalDate birthDate) { this.birthDate = birthDate; }
	public String getPhoneNumber() { return phoneNumber; }
	public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
	public Gender getGender() { return gender; }
	public void setGender(Gender gender) { this.gender = gender; }
	public Address getAddress() { return address; }
	public void setAddress(Address address) { this.address = address; }
	public String getUsername() { return username; }
	public void setUsername(String username) { this.username = username; }
	public String getPassword() { return password; }
	public void setPassword(String password) { this.password = password; }
	public String getEmail() { return email; }
	public void setEmail(String email) { this.email = email; }
	public Boolean getEnabled() { return isEnabled; }
	public void setEnabled(Boolean enabled) { isEnabled = enabled; }
	public Set<Role> getRoles() { return this.roles; }
	public void setRoles(Set<Role> roles) { this.roles = roles; }
}

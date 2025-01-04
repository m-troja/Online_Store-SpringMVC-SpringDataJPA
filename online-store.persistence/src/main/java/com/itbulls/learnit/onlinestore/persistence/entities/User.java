package com.itbulls.learnit.onlinestore.persistence.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import java.math.BigDecimal;
import java.util.Set;

@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "email", unique = true, length = 50)
	private String email;
	
	  @ManyToMany(fetch = FetchType.EAGER)
	    @JoinTable( 
	        name = "users_roles", 
	        joinColumns = @JoinColumn(
	          name = "user_id", referencedColumnName = "id"), 
	        inverseJoinColumns = @JoinColumn(
	          name = "role_id", referencedColumnName = "id")) 	
	  private Set<Role> roles;
	
	@Column(name = "money")
	private BigDecimal money;
	
	@Column(name = "credit_card")
	private String creditCard;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "partner_code", unique = true, length = 50)
	private String partnerCode;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "referrer_user_id")
	private User referrerUser;
	
	@Transient
    private String repeatPassword; // Nowe pole

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

	public Integer getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Set<Role> getRoles() {
		return  roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	public BigDecimal getMoney() {
		return money;
	}
	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	public String getCreditCard() {
		return creditCard;
	}
	public void setCreditCard(String creditCard) {
		this.creditCard = creditCard;
	}
	public String getPassword() {
		return this.password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setPartnerCode(String partnerCode) {
		this.partnerCode = partnerCode;
	}
	public void setReferrerUser(User user) {
		this.referrerUser = user;
	}
	public User getReferrerUser() {
		return this.referrerUser;
	}
	public String getPartnerCode() {
		return this.partnerCode;
	}
	@Override
	public String toString() {
		return "UserDto [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				 + ", money=" + money + ", creditCard=" + creditCard + ", password=" + password
				+ ", partnerCode=" + partnerCode + ", referrerUser=" + referrerUser + "]";
	}
	
	
	
}

package com.royesta.webapp.entity.user;

import com.royesta.webapp.entity.enums.Role;
import com.royesta.webapp.entity.AbstractEntity;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Collection;

/**
 * @author Roy Royesta (royesta lab)
 */
@Entity
public class User extends AbstractEntity<Long> implements UserDetails {
	
	private static final long serialVersionUID = 1L;
		
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Size(min = 6, max = 100)
	@Column(nullable = false, length = 100, unique = true)
	private String email;
	
	@Size(min = 5, max = 50)
	@Pattern(regexp = "^[a-z0-9]*$", message= "Only small letters and numbers allowed")
	@Column(nullable = false, length=50, unique = true)
	private String username;
	
	@Size(min = 6, max = 50)
	@Column(nullable = false, length = 50)
	private String password;
	
	@Column(nullable = false)
	private boolean enabled;
	
	@Column(nullable = false)
	private boolean locked;
	
	@NotEmpty
	@ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
	@CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
	@Column(name = "role", nullable = false)
	private Collection<Role> roles;
	
	/**
	 * Getter and setter 
	 */
	 
	 @Override
	 public void setId(Long id) {
		 this.id = id;
	 }
	 
	 @Override
	 public Long getId() {
		 return this.id;
	 }
	 
	 public void setUsername(String username) {
		 this.username = username;
	 }
	 
	 public void setPassword(String password) {
		 this.password = password;
	 }
	 
	 public String getEmail() {
		 return this.email;
	 }
	 
	 public void setEmail (String email) {
		 this.email = email;
	 }
	 
	 public void setEnabled(boolean enabled) {
		 this.enabled = enabled;
	 }
	 
	 public boolean isLocked() {
		 return this.locked;
	 }
	 
	 public void setLocked(boolean locked) {
		 this.locked = locked;
	 }
	 
	 public Collection<Role> getRoles() {
		 return this.roles;
	 }
	 
	 public void setRoles(Collection<Role> roles) {
		 this.roles = roles;
	 }
	 
	 /**
	  * Spring Security UserDetails requred methods
	  */
	 @Override
	 public Collection<? extends GrantedAuthority> getAuthorities() {
		 return getRoles();
	 }
	 
	 @Override
	 public String getUsername () {
		 return this.username;
	 }
	 
	 @Override
	 public String getPassword() {
		 return this.password;
	 }
	 
	 @Override
	 public boolean isAccountNonLocked() {
		 return !isLocked();
	 }
	 
	 @Override
	 public boolean isEnabled() {
		 return this.enabled;
	 }
	 
	 @Override
	 public boolean isAccountNonExpired() {
		 return true;	// Not implemented
	 }
	 
	 @Override
	 public boolean isCredentialsNonExpired() {
		 return true;	// Not implemented
	 }
	 
	 @Override
	 public String toString() {
		 return getUsername() + ", " + getEmail();
	 }
	
}

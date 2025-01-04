package com.itbulls.learnit.onlinestore.web.security;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.itbulls.learnit.onlinestore.core.facades.UserFacade;
import com.itbulls.learnit.onlinestore.persistence.entities.Privilege;
import com.itbulls.learnit.onlinestore.persistence.entities.Role;
import com.itbulls.learnit.onlinestore.persistence.entities.User;
@Transactional
public class DefaultUserDetailsService implements UserDetailsService 
{
	
	@Autowired
	private UserFacade userRepository;
	
	
	Logger LOGGER = LogManager.getLogger(DefaultUserDetailsService.class);
	
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException 
	{
		User user = userRepository.getUserByEmail(email);
		LOGGER.info("=== In DefaultUserDetailsService loadUserByUsername ===");
		LOGGER.info("=== DefaultUserDetailsService user.getRoles().toString = "  + user.getRoles().toString());

		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
				user.isEnabled(), true, true, true, getAuthorities(user.getRoles()));
	}
	
	private Collection<? extends GrantedAuthority> getAuthorities(Set<Role> roles) 
	{
		return getGrantedAuthorities(getPrivileges(roles));
	}
	
	private List<String> getPrivileges(Set<Role> roles) 
	{
		List<String> privileges = new ArrayList<>();
		List<Privilege> collection = new ArrayList<>();
		for (Role role : roles) {
			privileges.add(role.getName());
			collection.addAll(role.getPrivileges());
		}
		for (Privilege item : collection) {
			privileges.add(item.getName());
		}
		return privileges;
	}
	
	private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) 
	{
		List<GrantedAuthority> authorities = new ArrayList<>();
		for (String privilege : privileges) {
			authorities.add(new SimpleGrantedAuthority(privilege));
		}
		return authorities;
	}
}
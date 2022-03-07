package com.clouddestinations.engg.assessment.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import com.clouddestinations.engg.assessment.models.Role;
import com.clouddestinations.engg.assessment.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService  {

    @Autowired
    private UserRepository repo;
    
    
    /*@Autowired
    private EncoderService encoder;
    
	public String saveUser(com.clouddestinations.engg.assessment.models.User user) {
		String passwd= user.getPassword();
		String encodedPassword = encoder.getPasswordEncoder().encode(passwd);
		user.setPassword(encodedPassword);
		user = repo.save(user);
		return user.getEmployeeId();
	}*/

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.clouddestinations.engg.assessment.models.User user = repo.findByEmployeeId(username);
        if (user != null) {
            return new User(user.getEmployeeId(), user.getPassword(), buildSimpleGrantedAuthorities(user.getRoles()));
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }
    private static List<SimpleGrantedAuthority> buildSimpleGrantedAuthorities(final Set<Role> roles) {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }
    
}

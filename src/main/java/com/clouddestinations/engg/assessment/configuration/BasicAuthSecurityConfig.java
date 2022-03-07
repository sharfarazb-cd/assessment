package com.clouddestinations.engg.assessment.configuration;

import com.clouddestinations.engg.assessment.models.Role;
import com.clouddestinations.engg.assessment.services.EncoderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;


@Configuration
@EnableWebSecurity
public class BasicAuthSecurityConfig extends WebSecurityConfigurerAdapter{
    @Autowired
    private UserDetailsService uds;

    @Autowired
    private EncoderService encoder;


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(uds).passwordEncoder(encoder.getPasswordEncoder());
    }
    

    /*public BCryptPasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder(10);
    }*/


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Disable CSRF
        http.csrf().disable()
                // Only admin can perform HTTP delete operation
                .authorizeRequests().antMatchers(HttpMethod.DELETE).hasRole(Role.ADMIN)
                // any authenticated user can perform all other operations
                .antMatchers("/api/**").hasAnyRole(Role.ADMIN, Role.MANAGER).and().httpBasic()
                // Permit all other request without authentication
                .and().authorizeRequests().anyRequest().permitAll()
                // We don't need sessions to be created.
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

   
 
    
}

package com.codecool.codecoolquiz.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/sign-in").permitAll() // allowed by anyone
                .antMatchers("/sign-up").permitAll() // allowed by anyone
                .antMatchers("/categories/**").authenticated() // allowed only when signed in
                .antMatchers("/customquizzes/**").authenticated() // allowed only when signed in
                .antMatchers(HttpMethod.GET,"/questions/**").authenticated() // allowed only when signed in
                .antMatchers(HttpMethod.POST,"/questions/**").authenticated() // allowed only when signed in
                .antMatchers(HttpMethod.PUT,"/questions/**").hasRole("ADMIN") // allowed only when signed in
                .antMatchers("/types/**").authenticated() // allowed only when signed in
                .anyRequest().denyAll(); // anything else is denied
    }
}

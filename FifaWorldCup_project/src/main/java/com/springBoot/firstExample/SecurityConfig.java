package com.springBoot.firstExample;

import java.util.LinkedHashMap;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.DelegatingAuthenticationEntryPoint;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) 
      throws Exception {
        //PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        auth.inMemoryAuthentication()
          .withUser("admin").password(passwordEncoder().encode("1234")).roles("USER","ADMIN").and().
          withUser("user").password(passwordEncoder().encode("1234")).roles("USER");
    }
    
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http.formLogin().
    		defaultSuccessUrl("/fifaWorldCup", true)
    		.loginPage("/login").and().csrf();
    	
        http.authorizeRequests()
        	.antMatchers("/fifa/{id}")
        	.hasRole("ADMIN")
        	.and()
        	.csrf()
        	.and()
        	.exceptionHandling().accessDeniedPage("/fifa");
    }
}
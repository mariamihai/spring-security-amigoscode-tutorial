package com.amigoscode.spring.security.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import static com.amigoscode.spring.security.security.ApplicationUserRole.*;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()).and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "index", "/css/*", "/js/*").permitAll()
                .antMatchers("/api/**").hasRole(STUDENT.name())
                .anyRequest()
                .authenticated()
            .and()
                .httpBasic(); // Basic Auth to be used
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails annaUser = User.builder()
                    .username("anna")
                    .password(passwordEncoder.encode("pass"))
//                    .roles(STUDENT.name()) // ROLE_STUDENT
                    .authorities(STUDENT.getGrantedAuthorities())
                .build();

        UserDetails steveUser = User.builder()
                    .username("steve")
                    .password(passwordEncoder.encode("pass012"))
    //                .roles(ADMIN.name()) // ROLE_ADMIN
                    .authorities(ADMIN.getGrantedAuthorities())
                .build();

        UserDetails tomUser = User.builder()
                    .username("tom")
                    .password(passwordEncoder.encode("pass012"))
//                    .roles(ADMINTRAINEE.name()) // ROLE_ADMINTRAINEE
                    .authorities(ADMINTRAINEE.getGrantedAuthorities())
                .build();

        return new InMemoryUserDetailsManager(annaUser, steveUser, tomUser);
    }
}

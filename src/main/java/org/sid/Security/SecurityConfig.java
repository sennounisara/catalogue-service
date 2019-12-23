package org.sid.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.beans.beancontext.BeanContextChild;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.formLogin();
        //Pour te dire au spring securit n'est pas imporant d'utliser les sessions
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests().antMatchers("/categories/**").hasAnyAuthority("ADMIN");
        http.authorizeRequests().antMatchers("/produits/**").hasAnyAuthority("USER");
        http.authorizeRequests().anyRequest().authenticated();
        http.addFilterBefore(new JWTAuthentificationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        BCryptPasswordEncoder bCryptPasswordEncoder = getBCPC();
        auth.inMemoryAuthentication().withUser("admin")
                .password(bCryptPasswordEncoder.encode("123456"))
                .roles("ADMIN");
    }

    @Bean
    public BCryptPasswordEncoder getBCPC(){
        return new BCryptPasswordEncoder();
    }
}

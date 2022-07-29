package org.zerock.club.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  // @Override
  // protected void configure(AuthenticationManagerBuilder auth) throws Exception
  // {
  // auth.inMemoryAuthentication().withUser("user1").password("$2a$10$ONIxnaGMHcB77ztvVUwQiuAjEDkGGs7S4qfeCQ15BNg99MxB.s2OS").roles("USER");
  // }

  // @Bean
  // public SecurityFilterChain filterChain(HttpSecurity http, Jwt jwt,
  // TokenService tokenService) throws Exception {
  // http
  // .authorizeRequests()
  // .antMatchers("/sample/all").permitAll();
  // return http.build();
  // }

  // configure를 하기전에는 모든 것이 다 차단
  // 이것을 적용하는 순간 여기에 적용된 항목들만 security 적용됨
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
        .antMatchers("/sample/all").permitAll()
        .antMatchers("/sample/member").hasRole("USER");
    http.formLogin();
    http.csrf().disable();
    http.logout();
    http.oauth2Login(); //http://localhost:8080/club/login/oauth2/code/google
  }

}

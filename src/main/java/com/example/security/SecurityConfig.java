package com.example.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig {
  @Autowired
  private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
  @Bean
  protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests(authz -> authz
        .requestMatchers("/css/**", "/js/**", "/img/**", "/svg/**").permitAll()
        .requestMatchers("/").permitAll()
        .requestMatchers("/toRegister").permitAll()
        .requestMatchers("/register").permitAll()
        .requestMatchers("/login").permitAll()
        .requestMatchers("/shopping-cart").permitAll()
        .requestMatchers("/shopping-cart/**").permitAll()
        .requestMatchers("/").permitAll()
        .requestMatchers("/show-item-detail").permitAll()
        .requestMatchers("/get-item-info/**").permitAll()
        .requestMatchers("/error/**").permitAll()
        .anyRequest().authenticated()
    ).formLogin(login -> login
        .loginPage("/login")
        .loginProcessingUrl("/login")
        .failureUrl("/login?error=true")
        .defaultSuccessUrl("/", false)
        .usernameParameter("email")
        .passwordParameter("password")
    ).logout(logout -> logout
        .logoutRequestMatcher(new AntPathRequestMatcher("/logout**"))
        .logoutSuccessUrl("/")
        .invalidateHttpSession(true)
        .deleteCookies("JSESSIONID")
    ).csrf(csrf -> csrf
        .ignoringRequestMatchers(new AntPathRequestMatcher("/get-user/**"))
        .ignoringRequestMatchers(new AntPathRequestMatcher("/get-item-info/**"))
        .ignoringRequestMatchers(new AntPathRequestMatcher("/bookmark/**"))
        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
    );

    return http.build();
  }

  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public TaskExecutor taskExecutor(){
    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    executor.setCorePoolSize(10);
    return executor;
  }
}
package com.example.config;

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

/**
 * 設定ファイル
 * @author rui.inoue
 */
@Configuration
public class ApplicationConfig {

  // 認証成功時のハンドラ
  @Autowired
  private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

  @Bean
  public TaskExecutor taskExecutor(){
    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    executor.setCorePoolSize(10);
    return executor;
  }

  /**
   * 認証設定
   * @param http アプリケーションのセキュリティを構成するためのオブジェクト{@link HttpSecurity}
   * @return 構成済みの{@link SecurityFilterChain}インスタンス
   * @throws Exception 構成中にエラーが発生した場合
   */
  @Bean
  protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests(authz -> authz
        .requestMatchers("/css/**", "/js/**", "/img/**", "/svg/**", "/docs/**").permitAll()
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
        .successHandler(customAuthenticationSuccessHandler)
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
    ).sessionManagement(session -> session
        .sessionFixation().none()
    );

    return http.build();
  }

  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
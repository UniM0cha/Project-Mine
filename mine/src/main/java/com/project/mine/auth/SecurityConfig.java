package com.project.mine.auth;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/lib/**");
    }

    // HttpSecurity를 통해 HTTP 요청에 대한 보안을 설정할 수 있음
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .anyRequest().authenticated();

        http.oauth2Login()
                .loginPage("/auth/login")
                .permitAll()
                .userInfoEndpoint();

        http.logout()
                .logoutUrl("/auth/logout")
                .logoutSuccessUrl("/auth/login")
                .permitAll();

        // csrf와 사용하는 경우에 무조건 post 요청을 통해 logout 해야한다.
        http.csrf().disable();

        http.exceptionHandling()
                .accessDeniedPage("/auth/denied");
    }

}

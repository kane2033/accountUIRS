package com.uirs.account.config;

import com.uirs.account.security.jwt.JwtConfigure;
import com.uirs.account.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenProvider jwtTokenProvider;

    private static final String ADMIN_ENDPOINT = "/api/admin/**";
    private static final String LOGIN_ENDPOINT = "/api/auth/login";
    private static final String REGISTER_ENDPOINT = "/api/reg/**";

    @Autowired
    public SecurityConfig(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable() //отключение защиты от csrf (хакерской атаки)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) //сессии не создаются
                .and()
                .authorizeRequests() //настройка авторизации запросов
                .antMatchers(LOGIN_ENDPOINT, REGISTER_ENDPOINT).permitAll() //логин доступен только не авторизованным
                .antMatchers(ADMIN_ENDPOINT).hasRole("ADMIN") //разрешен только админам
                .anyRequest().authenticated() //все остальные запросы должны быть аунтефицированы (любая роль)
                .and()
                .apply(new JwtConfigure(jwtTokenProvider)); //проверка каждого запроса на сервер
    }
}

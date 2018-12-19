package home.udemy.rest.security;

import home.udemy.rest.service.UserService;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@EnableWebSecurity(debug = true)
@Component
public class WebSecurity extends WebSecurityConfigurerAdapter {
    private final UserService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    public WebSecurity(UserService userDetailsService, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //если не отключить, post запросы могут не доходить
                .csrf().disable()
                .authorizeRequests()
                //разрешаем только регистрацию
                .antMatchers(HttpMethod.POST, SecurityConstants.SIGN_UP_URL).permitAll()
                .anyRequest().authenticated()
                .and()
                //фильтр в который мы отправляем логин, пароль. UsernamePasswordAuthenticationFilter
                .addFilter(getAthenticationFilter())
                //фильтр который ловит все все запросы BasicAuthenticationFilter
                .addFilter(new AuthorizationFilter(authenticationManager()))
                // если не отключить сессию, после логирования и авторизации пользователь закешится и может проходить без токена
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    public AuthenticationFilter getAthenticationFilter() throws Exception {
        final AuthenticationFilter filter = new AuthenticationFilter(authenticationManager());
        filter.setFilterProcessesUrl("/users/login");
        return filter;
    }
}

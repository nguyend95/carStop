package eu.epptec.carStop.config;

import eu.epptec.carStop.security.CustomAuthenticationProvider;
import eu.epptec.carStop.security.jwt.AuthEntryPointJwt;
import eu.epptec.carStop.security.jwt.AuthTokenFilter;
import eu.epptec.carStop.utils.JwtUtils;
import eu.epptec.carStop.security.service.UserDetailsServiceImpl;
import eu.epptec.carStop.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@ComponentScan("eu.epptec.carStop.security.service")
@ComponentScan(basePackages = "eu.epptec.carStop.utils")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    final private UserDetailsServiceImpl userDetailsService;
    final private JwtUtils jwtUtils;
//    final private AuthEntryPointJwt unauthorizedHandler;
//    final private UserService userService;

    public WebSecurityConfig(UserDetailsServiceImpl userDetailsService, JwtUtils jwtUtils
//            , AuthEntryPointJwt unauthorizedHandler,
//                             UserService userService
    ){
        this.userDetailsService = userDetailsService;
        this.jwtUtils = jwtUtils;
//        this.unauthorizedHandler = unauthorizedHandler;
//        this.userService = userService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
                .exceptionHandling()
//                .authenticationEntryPoint(unauthorizedHandler)
            .and()
                .addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
//                .antMatchers("/test/**").permitAll()
                .antMatchers("/auth/login", "/api/signup", "/console/**").permitAll()
                .anyRequest().authenticated()
            .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
            .and()
                .csrf().disable()
                .httpBasic().disable()
                .logout().disable()
                .headers().frameOptions().disable()
        ;
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

//    @Bean
//    public CustomAuthenticationProvider authProvider() {
//        return new CustomAuthenticationProvider(userService, encoder());
//    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(this.encoder());
//        auth.authenticationProvider(authProvider());
    }

    @Bean
    AuthenticationEntryPoint forbiddenEntryPoint() {
        return new HttpStatusEntryPoint(HttpStatus.FORBIDDEN);
    }

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter(jwtUtils, userDetailsService);
    }
}

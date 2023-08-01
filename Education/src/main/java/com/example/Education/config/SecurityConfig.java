package com.example.Education.config;

import com.example.Education.dto.CODE;
import com.example.Education.dto.Response;
import com.example.Education.filtter.CustomAuthorizationFilter;
import com.example.Education.service.auth.CustomUserDetailsService;
import com.example.Education.service.auth.JWTUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    public static final String LOGIN_PATH = "/api/v2/auth/login";
    public static final String TEACHER_PATH = "/api/v2/teacher/**";
    private static final String SWAGGER_UI_HTML_PAGE = "/swagger-ui.html";
    private static final String SWAGGER_UI_PATH = "/swagger-ui/**";
    private static final String DOCS_PATH = "/v3/api-docs/**";

    private static final List<String> ALLOWED_METHODS = Arrays.asList("GET", "PUT", "POST", "DELETE", "OPTIONS", "PATCH");
    private static final List<String> ALLOWED_HEADERS = Arrays.asList("authorization", "Content-Type",
            "Authorization");
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private JWTUtil jwtUtil;
    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint((request, response, e) -> accessDenied(response))
                .and()
                .httpBasic().disable()
                .formLogin().disable()
                .cors().configurationSource(request -> getCorsConfiguration())
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(SWAGGER_UI_PATH).permitAll()
                .antMatchers(SWAGGER_UI_HTML_PAGE).permitAll()
                .antMatchers(DOCS_PATH).permitAll()
                .antMatchers(LOGIN_PATH).permitAll()
                .antMatchers(TEACHER_PATH).hasAuthority("teacher")
                .anyRequest().authenticated();

        http.addFilterBefore(new CustomAuthorizationFilter(userDetailsService, jwtUtil), UsernamePasswordAuthenticationFilter.class);
    }

    private CorsConfiguration getCorsConfiguration() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedHeaders(ALLOWED_HEADERS);
        corsConfiguration.setAllowedMethods(ALLOWED_METHODS);
        corsConfiguration.setAllowedOriginPatterns(Collections.singletonList("*"));
        corsConfiguration.setAllowCredentials(true);
        return corsConfiguration;
    }

    private void accessDenied(HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.getWriter().write(objectMapper.writeValueAsString(Response.builder()
                .code(CODE.FORBIDDEN.getId())
                .message("Access denied")
                .success(false)
                .build()));
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}

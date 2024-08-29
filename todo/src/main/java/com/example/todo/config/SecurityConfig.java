/**package com.example.todo.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Value("${spring.cors.origin.allowed.url")
	private String allowedUrl;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.csrf(csrf -> csrf.disable())
			.cors(cors -> cors.configurationSource(corsConfigurationSource()))
			.authorizeHttpRequests(authz -> authz
					.requestMatchers("/api/**").permitAll()
			);
		return http.build();
	}
	
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		
		System.out.print(allowedUrl);
		
		var corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowedOrigins(List.of(allowedUrl));
		corsConfiguration.setAllowedMethods(List.of("GET" , "POST" , "PUT" , "DELETE"));
		corsConfiguration.setAllowedHeaders(List.of("Authorization" , "Content-Type"));
		corsConfiguration.setAllowCredentials(true);
		
		var source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**",corsConfiguration);
		return source;
	}
}
**/
package BookInventoryManage.example.inventory.Security;

import java.util.List;

import BookInventoryManage.example.inventory.Enums.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class BISecurityConfiguration {
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("*"));
        configuration.setAllowedMethods(List.of("*"));
        configuration.setAllowedHeaders(List.of("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public BISecurityFilter biSecurityFilter() {
        return new BISecurityFilter();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authorize -> authorize
//                        guest
                        .requestMatchers(HttpMethod.GET, "/book/**", "/category/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/account/**", "/auth/**").permitAll()
//                        user
                        .requestMatchers("/auth/**", "/review/**").authenticated()
//                        admin
                        .requestMatchers("/author/**", "/category/**", "/account/**", "/book/**").hasAuthority(BIAuthority.of(Role.ADMIN).getAuthority())

//                        .anyRequest().permitAll()
                ))
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .addFilterBefore(biSecurityFilter(), AuthorizationFilter.class);
        return http.build();
    }
}

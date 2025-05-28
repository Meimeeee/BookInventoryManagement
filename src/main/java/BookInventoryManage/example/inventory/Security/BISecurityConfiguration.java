package BookInventoryManage.example.inventory.Security;

import BookInventoryManage.example.inventory.Enums.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

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
//                        .requestMatchers(HttpMethod.GET, "/book/**", "/category/**").permitAll()
                        .requestMatchers( "/account/login", "/auth/login").permitAll()
//                        user
//                        .requestMatchers("/auth/**", "/review/**").authenticated()
////                        admin
//                        .requestMatchers("/author/**", "/category/**", "/account/**", "/book/**").hasAuthority(BIAuthority.of(Role.ADMIN).getAuthority())
//                        .anyRequest().hasRole("ADMIN")

//                        .anyRequest().permitAll()
                ))
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .addFilterBefore(biSecurityFilter(), AuthenticationFilter.class);
        return http.build();
    }
}

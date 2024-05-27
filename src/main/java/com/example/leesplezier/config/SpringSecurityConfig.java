package com.example.leesplezier.config;

import com.example.leesplezier.security.JwtRequestFilter;
import com.example.leesplezier.security.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    public final CustomUserDetailsService customUserDetailsService;
    private final JwtRequestFilter jwtRequestFilter;
    private final PasswordEncoder passwordEncoder;


    public SpringSecurityConfig(CustomUserDetailsService customUserDetailsService, JwtRequestFilter jwtRequestFilter, PasswordEncoder passwordEncoder) {
        this.customUserDetailsService = customUserDetailsService;
        this.passwordEncoder = passwordEncoder;
        this.jwtRequestFilter = jwtRequestFilter;
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        var auth = new DaoAuthenticationProvider();
        auth.setPasswordEncoder(passwordEncoder);
        auth.setUserDetailsService(customUserDetailsService);
        return new ProviderManager(auth);
    }

    // Authorization with jwt
    @Bean
    protected SecurityFilterChain filter(HttpSecurity http) throws Exception {

        //JWT token authentication
        http
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(auth ->
                        auth
                                // Wanneer je deze uncomment, staat je hele security open. Je hebt dan alleen nog een jwt nodig.
                                      .requestMatchers("/**").permitAll()

                                .requestMatchers(HttpMethod.POST, "/users/create").permitAll()
                                .requestMatchers(HttpMethod.GET, "/users/**").hasAnyRole("ADMIN", "VOLUNTEER", "PARENT")

                                .requestMatchers(HttpMethod.GET, "/locations").permitAll()
                                .requestMatchers(HttpMethod.DELETE, "/locations/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.POST, "/locations").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/locations").hasRole("ADMIN")

                                .requestMatchers(HttpMethod.GET, "/concepts/**").permitAll()
                                .requestMatchers(HttpMethod.GET, "/concepts").permitAll()
                                .requestMatchers(HttpMethod.DELETE, "/concepts").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.POST, "/concepts").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/concepts").hasRole("ADMIN")

                                .requestMatchers(HttpMethod.GET, "/books/**").permitAll()
                                .requestMatchers(HttpMethod.DELETE, "/books").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.POST, "/books").permitAll()
                                .requestMatchers(HttpMethod.PUT, "/books").hasRole("ADMIN")

                                .requestMatchers(HttpMethod.GET, "/schedules").permitAll()
                                .requestMatchers(HttpMethod.POST, "/schedules").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/schedules").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/schedules/**").hasRole("ADMIN")

                                .requestMatchers(HttpMethod.GET, "/children/**").hasAnyRole("VOLUNTEER", "ADMIN")
                                .requestMatchers(HttpMethod.POST, "/children/create/**").hasAnyRole("PARENT", "ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/children/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/children/**").hasAnyRole("ADMIN", "PARENT")

                                .requestMatchers(HttpMethod.GET, "/sessions").hasAnyRole("ADMIN", "VOLUNTEER", "PARENT")
                                .requestMatchers(HttpMethod.POST, "/sessions/create").hasAnyRole("ADMIN", "VOLUNTEER")
                                .requestMatchers(HttpMethod.DELETE, "/sessions/**").hasAnyRole("ADMIN", "VOLUNTEER")
                                .requestMatchers(HttpMethod.PUT, "/sessions").hasAnyRole("ADMIN", "VOLUNTEER")

                                .requestMatchers("/authenticated").authenticated()
                                .requestMatchers("/authenticate").permitAll()/*alleen dit punt mag toegankelijk zijn voor niet ingelogde gebruikers*/
                                .anyRequest().denyAll() /*Deze voeg je altijd als laatste toe, om een default beveiliging te hebben voor eventuele vergeten endpoints of endpoints die je later toevoegd. */
                )
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}

package pe.edu.idat.appcafetime.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import pe.edu.idat.appcafetime.service.DetalleUsuarioService;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final DetalleUsuarioService detalleUsuarioService;
    private final PasswordEncoder passwordEncoder;

    @Bean
    public SecurityFilterChain config(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth ->
                auth.requestMatchers("/auth/login",
                        "/auth/registrar",
                        "/auth/guardarUsuario",
                        "/api/admin",
                        "/resources/**",
                        "/static/**",
                        "/styles/**",
                        "/scripts/**").permitAll()
                        .requestMatchers("/admin/**",
                                "/productos/nuevo",
                                "/productos/editar/**",
                                "/productos/eliminar/**",
                                "/pedido",
                                "/pedido/*/estado").hasRole("ADMIN")
                        .requestMatchers("/cliente/**",
                                "/pedido/**").hasRole("CLIENTE")
                        .requestMatchers("/productos/**")
                            .hasAnyRole("ADMIN","CLIENTE")
                        .anyRequest().authenticated()
        ).formLogin(login ->
                login.loginPage("/auth/login")
                        .loginProcessingUrl("/auth/login")
                        .defaultSuccessUrl("/auth/login-success", true)
                        .failureUrl("/auth/login?error=true")
                        .usernameParameter("username")
                        .passwordParameter("password")
        ).logout(logout ->
                logout.logoutUrl("/logout")
                        .logoutSuccessUrl("/auth/login?logout")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll()
        ).csrf(csrf ->
                csrf.ignoringRequestMatchers("/api/**")
        ).authenticationProvider(authenticationProvider());
        return http.build();
    };

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider dao = new DaoAuthenticationProvider(detalleUsuarioService);
        dao.setPasswordEncoder(passwordEncoder);
        return dao;
    }

}

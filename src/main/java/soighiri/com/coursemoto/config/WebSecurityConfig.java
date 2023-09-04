package soighiri.com.coursemoto.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    // Déclaration d'un bean pour le hachage des mots de passe
    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Définition de la configuration de sécurité pour les requêtes HTTP
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity

                // Désactivation de la protection CSRF (Cross-Site Request Forgery)
                .csrf(csrf -> csrf.disable())

                // Configuration pour la page de connexion
                .formLogin(form -> form
                        .loginPage("/login") // Page de connexion
                        .permitAll() // Accessible sans authentification
                )
                .logout(logout -> logout
                        .logoutUrl("/logout") // URL de déconnexion
                        .permitAll() // Accessible sans authentification
                )

                // Configuration pour les autorisations d'accès aux différentes URL
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/home","/visiteur", "/inscription", "/css/**", "/img/**", "/favicon.co","/webjars/**").permitAll()
                        .requestMatchers(HttpMethod.POST,("/insciption")).permitAll()

                        //Interdir la page si l'utilisateur n'est pas admin
                        .requestMatchers("/superAdmin/**").hasAnyAuthority("superAdmin")
                        //
                        .requestMatchers("/admin/**").hasAnyAuthority("superAdmin","admin")
                        .anyRequest().authenticated()
                )
                .build();


    }
}

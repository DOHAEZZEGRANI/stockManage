package ma.xproce.stockmanag.Config;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf((csrf) -> csrf.disable())
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/ajouter","/details","/ajoutterOnce", "/ajouterOnce","/detailsfour", "/ajouterproduit","/ajouterCustomer",
                                "/deleteProduit", "/editProduit").authenticated()
                        .requestMatchers("/indexpage","/indexCustomer","/about","/liststock","/error", "/register","/listfour","/", "/webjars/**").permitAll())
                .formLogin(formLogin -> formLogin
                        .loginPage("/login")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                )
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails adminmanager = User.withUsername("adminmanager")
                .password("12345")
                .roles("managetable", "delete", "update")
                //.roles("admin")
                .build();
        UserDetails admin = User.withUsername("admin")
                .password("12345")
                //.authorities("admin")
                .roles("admin")
                .build();
        UserDetails user = User.withUsername("user")
                .password("12345")
                .roles("user")
                .build();
        UserDetails Customer = User.withUsername("Customer")
                .password("12345")
                .roles("user")
                .build();
        return new InMemoryUserDetailsManager(adminmanager,Customer, admin, user);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }


}



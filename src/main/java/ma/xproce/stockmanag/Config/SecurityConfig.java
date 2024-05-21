package ma.xproce.stockmanag.Config;



import ma.xproce.stockmanag.service.AdminUserDetailsService;
import ma.xproce.stockmanag.service.CustomerUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private CustomerUserDetailsService customUserDetailsService;
    @Autowired
    private AdminUserDetailsService adminUserDetailsService;
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf((csrf) -> csrf.disable())
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/ajouterco","deleteco","/validation","/editCommande","/panier","/stockdetails","/ajouterr","/addOnce","/details","/modifierCustomer","/detailsCustomer","/ajoutterOnce", "/ajouterOOnce","/detailsfour", "/ajouterfournisseur","/ajouterstock","/ajouterCustomer",
                                "/deleteProduit","/editCustomer","/editfournisseur","/deleteCustomer", "/editProduit").authenticated()
                        .requestMatchers("/indexpage","/admin/register","/chooselogin","/AdminRegister","/indexcommande","/indexCustomer","/register","/Dashboard","/about","/ajouter","/home","/liststock","/error", "/Register","/ajouterOnce","/listfour","/","/home","/ajouterproduit", "/webjars/**").permitAll())



                        .formLogin(form -> form
                                .loginPage("/login")
                                .defaultSuccessUrl("/indexpage", true)
                                .successHandler((request, response, authentication) -> {
                                    String redirectUrl = authentication.getAuthorities().stream()
                                            .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN")) ? "/home" : "/home";
                                    response.sendRedirect(redirectUrl);
                                })
                                .permitAll()
                        )
                        .logout(logout -> logout
                                .logoutUrl("/logout")
                                .logoutSuccessUrl("/login")
                                .invalidateHttpSession(true)
                                .clearAuthentication(true)
                                .deleteCookies("JSESSIONID")
                        );

                return http.build();
            }

            @Autowired
            public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
                auth.userDetailsService(customUserDetailsService);
                auth.userDetailsService(adminUserDetailsService);

            }

            @Bean
            public PasswordEncoder passwordEncoder() {
                return NoOpPasswordEncoder.getInstance();
            }
        }


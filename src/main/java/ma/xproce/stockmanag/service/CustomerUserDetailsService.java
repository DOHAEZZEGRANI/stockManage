package ma.xproce.stockmanag.service;

import ma.xproce.stockmanag.dao.entities.Customer;
import ma.xproce.stockmanag.dao.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomerUserDetailsService implements UserDetailsService {
    @Autowired
    private CustomerService cm;

    @Override
    public UserDetails loadUserByUsername(String nomUtilisateur) throws UsernameNotFoundException {
        Customer customer = cm.findByUsername(nomUtilisateur);
        if (customer == null) {
            throw new UsernameNotFoundException("User not found with username: " + nomUtilisateur);
        }
        return org.springframework.security.core.userdetails.User
                .withUsername(customer.getUsername())
                .password(customer.getPassword())
                .roles("USER")
                .build();
    }

}

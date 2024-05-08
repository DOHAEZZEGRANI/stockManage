package ma.xproce.stockmanag.service;

import ma.xproce.stockmanag.dao.entities.Customer;
import ma.xproce.stockmanag.dao.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
@Service
public class CustomerManager implements  CustomerService{
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;



    @Override
    public Customer findByEmail(String email) {
        return customerRepository.findByEmail(email);
    }

    @Override
    public Customer findById(Integer id) {
        return customerRepository.findById(id).get();
    }

    @Override
    public void updateCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public void deleteCustomer(Integer id) {
        customerRepository.deleteById(id);
    }


    @Override
    public boolean registerUser(Customer customer) {
        // Your logic to save the customer to the database
        // Return true if registration is successful, false otherwise
        try {
            customerRepository.save(customer);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public Customer loginUser(String username, String password) {
        // Trouver le client par son nom d'utilisateur (username)
        Customer customer = customerRepository.findByUsername(username);
        if (customer == null) {
            throw new UsernameNotFoundException("Nom d'utilisateur incorrect.");
        }

        // Vérifier le mot de passe en utilisant le passwordEncoder
        if (!passwordEncoder.matches(password, customer.getPassword())) {
            throw new IllegalArgumentException("Mot de passe incorrect.");
        }

        // Authentification réussie, retourner le client
        return customer;
    }
}
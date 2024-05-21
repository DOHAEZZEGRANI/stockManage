package ma.xproce.stockmanag.service;

import ma.xproce.stockmanag.dao.entities.Customer;
import ma.xproce.stockmanag.dao.entities.Produit;
import ma.xproce.stockmanag.dao.repositories.CustomerRepository;
import ma.xproce.stockmanag.dao.repositories.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
@Service
public class CustomerManager implements  CustomerService {
    @Autowired
    private CustomerRepository customerRepository;


    @Override
    public Boolean DeleteById(Integer id) {
        try {
            customerRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public Customer addcustomer(Customer customer) {
        try {
            return customerRepository.save(customer);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<Customer> getAllcustomer() {
        try {
            return customerRepository.findAll();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Page<Customer> searchcustomers(String keyword, int page, int taille) {
        return customerRepository.findByUsernameContains(keyword, (Pageable) PageRequest.of(page, taille));
    }

    @Override
    public List<Customer> getByKeyword(String keyword) {
        return null;
    }

    @Override
    public Customer getcustomerById(Integer id) {
        return customerRepository.findById(id).get();
    }

    @Override
    public Customer updatecustomer(Customer customer) {
        try {
            return customerRepository.save(customer);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Boolean deletecustomer(Integer id) {
        try {
            customerRepository.deleteById(id);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    @Override
    public Customer findByUsername(String username) {
        return customerRepository.findCustomerByUsername(username);
    }



}
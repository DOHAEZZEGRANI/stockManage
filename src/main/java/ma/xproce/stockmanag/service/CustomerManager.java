package ma.xproce.stockmanag.service;

import ma.xproce.stockmanag.dao.entities.Customer;
import ma.xproce.stockmanag.dao.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
@Service
public class CustomerManager implements  CustomerService{
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer creerCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer obtenirCustomerParId(Integer id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Client introuvable"));
    }

    @Override
    public List<Customer> obtenirTousLesCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public void supprimerCustomer(Integer id) {
        customerRepository.deleteById(id);
    }
}

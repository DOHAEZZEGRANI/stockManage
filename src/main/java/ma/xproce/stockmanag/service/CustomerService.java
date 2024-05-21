package ma.xproce.stockmanag.service;

import ma.xproce.stockmanag.dao.entities.Customer;
import ma.xproce.stockmanag.dao.entities.Produit;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CustomerService {
    public Boolean DeleteById(Integer id);
    public Customer addcustomer(Customer customer);
    public List<Customer> getAllcustomer();
    public Page<Customer> searchcustomers(String keyword, int page, int taille);
    public List<Customer> getByKeyword(String keyword);
    public Customer getcustomerById(Integer id);
    public Customer updatecustomer(Customer customer);

    public Boolean deletecustomer(Integer id);

    Customer findByUsername(String username);
}

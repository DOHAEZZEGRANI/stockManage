package ma.xproce.stockmanag.service;

import ma.xproce.stockmanag.dao.entities.Customer;

import java.util.List;

public interface CustomerService {
    boolean registerUser(Customer customer);
    Customer loginUser(String username, String password);
    Customer findByEmail(String email);
    Customer findById(Integer id);
    void updateCustomer(Customer customer);
    void deleteCustomer(Integer id);
}

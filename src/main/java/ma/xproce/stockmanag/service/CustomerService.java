package ma.xproce.stockmanag.service;

import ma.xproce.stockmanag.dao.entities.Customer;

import java.util.List;

public interface CustomerService {
    Customer creerCustomer(Customer customer);
    Customer obtenirCustomerParId(Integer id);
    List<Customer> obtenirTousLesCustomers();
    void supprimerCustomer(Integer id);
}

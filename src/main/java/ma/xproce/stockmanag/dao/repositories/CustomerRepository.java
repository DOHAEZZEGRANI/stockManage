package ma.xproce.stockmanag.dao.repositories;

import ma.xproce.stockmanag.dao.entities.Customer;
import ma.xproce.stockmanag.dao.entities.Produit;
import ma.xproce.stockmanag.dao.entities.Stock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    Customer findByUsername(String username);
    Page<Customer> findByDescriptionContains(String keyword, Pageable pageable);

}


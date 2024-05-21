package ma.xproce.stockmanag.dao.repositories;

import jakarta.transaction.Transactional;
import ma.xproce.stockmanag.dao.entities.Customer;
import ma.xproce.stockmanag.dao.entities.Produit;
import ma.xproce.stockmanag.dao.entities.Stock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
@Transactional
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
   public Customer findCustomerByUsername(String username);
    Page<Customer> findByUsernameContains(String keyword, Pageable pageable);

}


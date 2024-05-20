package ma.xproce.stockmanag.dao.repositories;

import ma.xproce.stockmanag.dao.entities.Commande;
import ma.xproce.stockmanag.dao.entities.Customer;
import ma.xproce.stockmanag.dao.entities.Stock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommandeRepository extends JpaRepository<Commande,Integer> {
    Page<Commande> findByDescriptionContains(String keyword, Pageable pageable);
}

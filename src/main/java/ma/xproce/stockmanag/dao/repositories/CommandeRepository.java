package ma.xproce.stockmanag.dao.repositories;

import ma.xproce.stockmanag.dao.entities.Commande;
import ma.xproce.stockmanag.dao.entities.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommandeRepository extends JpaRepository<Commande,Integer> {
}

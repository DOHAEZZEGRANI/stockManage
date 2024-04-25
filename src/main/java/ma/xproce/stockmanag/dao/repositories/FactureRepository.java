package ma.xproce.stockmanag.dao.repositories;

import ma.xproce.stockmanag.dao.entities.Facture;
import ma.xproce.stockmanag.dao.entities.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FactureRepository extends JpaRepository<Facture,Integer> {
}

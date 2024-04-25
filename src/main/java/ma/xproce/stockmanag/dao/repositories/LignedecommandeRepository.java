package ma.xproce.stockmanag.dao.repositories;

import ma.xproce.stockmanag.dao.entities.Lignedecommande;
import ma.xproce.stockmanag.dao.entities.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LignedecommandeRepository extends JpaRepository<Lignedecommande,Integer> {
}

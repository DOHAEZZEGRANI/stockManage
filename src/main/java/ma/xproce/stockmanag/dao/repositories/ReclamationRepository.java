package ma.xproce.stockmanag.dao.repositories;

import ma.xproce.stockmanag.dao.entities.Reclamation;
import ma.xproce.stockmanag.dao.entities.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReclamationRepository extends JpaRepository<Reclamation,Integer> {
}

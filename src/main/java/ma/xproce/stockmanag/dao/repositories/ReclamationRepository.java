package ma.xproce.stockmanag.dao.repositories;

import jakarta.transaction.Transactional;
import ma.xproce.stockmanag.dao.entities.Fournisseur;
import ma.xproce.stockmanag.dao.entities.Reclamation;
import ma.xproce.stockmanag.dao.entities.Stock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
@Transactional
public interface ReclamationRepository extends JpaRepository<Reclamation,Integer> {
    Page<Reclamation> findByDescriptionContains(String keyword, Pageable pageable);
}

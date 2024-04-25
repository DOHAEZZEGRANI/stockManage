package ma.xproce.stockmanag.dao.repositories;

import jakarta.transaction.Transactional;
import ma.xproce.stockmanag.dao.entities.Fournisseur;
import ma.xproce.stockmanag.dao.entities.Produit;
import ma.xproce.stockmanag.dao.entities.Stock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
@Transactional
public interface FournisseurRepository extends JpaRepository<Fournisseur,Integer> {
    Page<Fournisseur> findByDescriptionContains(String keyword, Pageable pageable);
}

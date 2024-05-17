package ma.xproce.stockmanag.service;

import ma.xproce.stockmanag.dao.entities.Fournisseur;
import ma.xproce.stockmanag.dao.entities.Reclamation;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ReclamationService {
    public Boolean DeleteById(Integer id);
    public Reclamation addReclamation(Reclamation reclamation);
    public List<Reclamation> getAllReclamation();
    public Page<Reclamation> searchReclamation(String keyword, int page, int taille);
    public List<Reclamation> getByKeyword(String keyword);
    public Reclamation getReclamationById(Integer id);
    public Reclamation updateReclamation(Reclamation reclamation);
    public Boolean deleteReclamation(Integer id);
}

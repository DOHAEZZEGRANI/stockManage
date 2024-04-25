package ma.xproce.stockmanag.service;

import ma.xproce.stockmanag.dao.entities.Fournisseur;
import ma.xproce.stockmanag.dao.entities.Produit;
import org.springframework.data.domain.Page;

import java.util.List;

public interface FournisseurService {
    public Boolean DeleteById(Integer id);
    public Fournisseur addFournisseur(Fournisseur fournisseur);
    public List<Fournisseur> getAllFournisseur();
    public Page<Fournisseur> searchFournisseur(String keyword, int page, int taille);
    public List<Fournisseur> getByKeyword(String keyword);
    public Fournisseur getFournisseurById(Integer id);
    public Fournisseur updateFournisseur(Fournisseur fournisseur);
    public Boolean deleteFournisseur(Integer id);
}

package ma.xproce.stockmanag.service;

import ma.xproce.stockmanag.dao.entities.Produit;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProduitService {


    public Boolean DeleteById(Integer id);
    public Produit addProduit(Produit produit);
    public List<Produit> getAllProduits();
    public Page<Produit> searchProduits(String keyword, int page, int taille);
    public List<Produit> getByKeyword(String keyword);
    public Produit getProduitById(Integer id);
    public Produit updateProduit(Produit produit);
    public Boolean deleteProduit(Integer id);

}
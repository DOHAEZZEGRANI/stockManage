package ma.xproce.stockmanag.service;

import ma.xproce.stockmanag.dao.entities.Produit;
import ma.xproce.stockmanag.dao.repositories.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
@Service
public class ProduitManager implements ProduitService{
    @Autowired
    private ProduitRepository produitRepository;


    @Override
    public Boolean DeleteById(Integer id) {
        try {
            produitRepository.deleteById(id);
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }
    @Override
    public Produit addProduit(Produit produit) {
        try {
            return produitRepository.save(produit);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
    @Override
    public List<Produit> getAllProduits() {
        try {
            return produitRepository.findAll();
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Page<Produit> searchProduits(String keyword, int page, int taille) {
        return produitRepository.findByDescriptionContains(keyword, (Pageable)PageRequest.of(page, taille));
    }

    @Override
    public List<Produit> getByKeyword(String keyword) {
        return null;
    }

    @Override
    public Produit getProduitById(Integer id) {
        return produitRepository.findById(id).get();
    }

    @Override
    public Produit updateProduit(Produit produit) {
        try {
            return produitRepository.save(produit);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Boolean deleteProduit(Integer id) {
        try {
            produitRepository.deleteById(id);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }


}
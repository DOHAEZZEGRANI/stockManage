package ma.xproce.stockmanag.service;

import ma.xproce.stockmanag.dao.entities.Fournisseur;
import ma.xproce.stockmanag.dao.entities.Produit;
import ma.xproce.stockmanag.dao.repositories.FournisseurRepository;
import ma.xproce.stockmanag.dao.repositories.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
@Service
public class FournisseurManager implements FournisseurService {
    @Autowired
    private FournisseurRepository fournisseurRepository;


    @Override
    public Boolean DeleteById(Integer id) {
        try {
            fournisseurRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public Fournisseur addFournisseur(Fournisseur fournisseur) {
        try {
            return fournisseurRepository.save(fournisseur);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<Fournisseur> getAllFournisseur() {
        try {
            return fournisseurRepository.findAll();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Page<Fournisseur> searchFournisseur(String keyword, int page, int taille) {
        return fournisseurRepository.findByDescriptionContains(keyword, (Pageable) PageRequest.of(page, taille));
    }

    @Override
    public List<Fournisseur> getByKeyword(String keyword) {
        return null;
    }

    @Override
    public Fournisseur getFournisseurById(Integer id) {
        return fournisseurRepository.findById(id).get();
    }

    @Override
    public Fournisseur updateFournisseur(Fournisseur fournisseur) {
        try {
            return fournisseurRepository.save(fournisseur);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Boolean deleteFournisseur(Integer id) {
        try {
            fournisseurRepository.deleteById(id);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }
}

package ma.xproce.stockmanag.service;

import ma.xproce.stockmanag.dao.entities.Fournisseur;
import ma.xproce.stockmanag.dao.entities.Reclamation;
import ma.xproce.stockmanag.dao.repositories.FournisseurRepository;
import ma.xproce.stockmanag.dao.repositories.ReclamationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
@Service
public class ReclamationManager implements ReclamationService{
    @Autowired
    private ReclamationRepository reclamationRepository;


    @Override
    public Boolean DeleteById(Integer id) {
        try {
            reclamationRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public Reclamation addReclamation(Reclamation reclamation) {
        try {
            return reclamationRepository.save(reclamation);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<Reclamation> getAllReclamation() {
        try {
            return reclamationRepository.findAll();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Page<Reclamation> searchReclamation(String keyword, int page, int taille) {
        return reclamationRepository.findByDescriptionContains(keyword, (Pageable) PageRequest.of(page, taille));
    }

    @Override
    public List<Reclamation> getByKeyword(String keyword) {
        return null;
    }

    @Override
    public Reclamation getReclamationById(Integer id) {
        return reclamationRepository.findById(id).get();
    }

    @Override
    public Reclamation updateReclamation(Reclamation reclamation) {
        try {
            return reclamationRepository.save(reclamation);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Boolean deleteReclamation(Integer id) {
        try {
            reclamationRepository.deleteById(id);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }
}
